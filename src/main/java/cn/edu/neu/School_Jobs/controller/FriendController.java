package cn.edu.neu.School_Jobs.controller;
import cn.edu.neu.School_Jobs.model.Friend;
import cn.edu.neu.School_Jobs.service.FriendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import java.util.List;
import cn.edu.neu.School_Jobs.util.CommonUtil;
import cn.edu.neu.School_Jobs.conf.exception.CommonJsonException;

/**
 *
 * Created by zzc on 2019/06/13.
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendService friendService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public JSONObject findFriend(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

    PageHelper.startPage(pageNum, pageSize);
    List<Friend> list = friendService.findAll();
    PageInfo pageInfo = new PageInfo(list);

    return CommonUtil.successJson(pageInfo);
    }

    @PostMapping("/add")
    public JSONObject addFriend(@RequestBody JSONObject requestJson) {
        //try{
            //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
          //  return e.getResultJson();
        //}

        Friend friend = JSONObject.toJavaObject(requestJson,Friend.class);
        friendService.save(friend);

        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateFriend(@RequestBody JSONObject requestJson) {
        //try{
          //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        Friend friend = JSONObject.toJavaObject(requestJson,Friend.class);
        friendService.update(friend);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteFriend(@PathVariable(value = "id") int id) {

        friendService.deleteById(id);

        return CommonUtil.successJson();
    }


}
