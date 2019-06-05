package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.Comment;
import cn.edu.neu.School_Jobs.service.CommentService;
import cn.edu.neu.School_Jobs.util.CommonUtil;
import cn.edu.neu.School_Jobs.util.Jwt;
import cn.edu.neu.School_Jobs.util.constants.ErrorEnum;
import cn.edu.neu.School_Jobs.vo.CommentWithUserInfo;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by fzb on 2019/06/02.
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findComment(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Comment> list = commentService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }

    @GetMapping("/fund/{fundId}/{page}")
    public JSONObject getFundComment(@PathVariable(value = "fundId") String fundId, @PathVariable(value = "page") int page) {
        PageHelper.startPage(page, 5);
        List<CommentWithUserInfo> comments = commentService.findUserInfoWithCommentByField(fundId, null, null, null, null);
        PageInfo pageInfo = new PageInfo(comments);
        return CommonUtil.successJson(pageInfo);
    }

    @GetMapping("/manager/{managerId}/{page}")
    public JSONObject getManagerComment(@PathVariable(value = "managerId") String managerId, @PathVariable(value = "page") int page) {
        PageHelper.startPage(page, 5);
        List<CommentWithUserInfo> comments = commentService.findUserInfoWithCommentByField(null, managerId, null, null, null);
        PageInfo pageInfo = new PageInfo(comments);
        return CommonUtil.successJson(pageInfo);
    }

    @GetMapping("/rank/{rankId}/{page}")
    public JSONObject getRankComment(@PathVariable(value = "rankId") String rankId, @PathVariable(value = "page") int page) {
        PageHelper.startPage(page, 5);
        List<CommentWithUserInfo> comments = commentService.findUserInfoWithCommentByField(null, null, rankId, null, null);
        PageInfo pageInfo = new PageInfo(comments);
        return CommonUtil.successJson(pageInfo);
    }

    @GetMapping("/notification/not_read/me")
    public JSONObject getNoReadCount(HttpServletRequest request){
        int count = commentService.findNoReadCount(String.valueOf(Jwt.getUserId(request)),"0");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count",count);
        return CommonUtil.successJson(jsonObject);
    }

    @GetMapping("/notification/{page}")
    public JSONObject getNotification(@PathVariable(value = "page") int page, HttpServletRequest request) {
        PageHelper.startPage(page, 5);
        String type = request.getParameter("type");
        int userId = Jwt.getUserId(request);
        List<CommentWithUserInfo> comments = commentService.findUserInfoWithCommentByField(null, null, null, String.valueOf(userId), type);
        for (CommentWithUserInfo comment : comments) {
            if (comment.getReplyId() != null) {
                Comment firstComment = commentService.findById(comment.getReplyId());
                if (firstComment != null) {
                    comment.setFirstText(firstComment.getText());
                }
            }
        }
        int remain = page * 5 > comments.size() ? comments.size() : page;
        for (int i = (page - 1) * 5; i < remain; i++) {
            if(!comments.get(i).getRead()){
                comments.get(i).setRead(true);
                commentService.update(comments.get(i));
            }
        }
        PageInfo pageInfo = new PageInfo(comments);
        return CommonUtil.successJson(pageInfo);
    }

    @PostMapping("/add")
    public JSONObject addComment(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}
        try {
            Comment comment = JSONObject.toJavaObject(requestJson, Comment.class);
            comment.setUserId(Jwt.getUserId(request));
            comment.setInsertTime(new Date());
            commentService.save(comment);
        } catch (Exception e) {
            return CommonUtil.errorJson(ErrorEnum.E_788);
        }
        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateComment(@RequestBody JSONObject requestJson) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        Comment comment = JSONObject.toJavaObject(requestJson, Comment.class);
        commentService.update(comment);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteComment(@PathVariable(value = "id") int id) {

        commentService.deleteById(id);

        return CommonUtil.successJson();
    }


}
