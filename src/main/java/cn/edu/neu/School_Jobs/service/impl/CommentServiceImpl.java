package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.CommentMapper;
import cn.edu.neu.School_Jobs.model.Comment;
import cn.edu.neu.School_Jobs.service.CommentService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import cn.edu.neu.School_Jobs.vo.CommentWithUserInfo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * Created by fzb on 2019/06/02.
 */
@Service
public class CommentServiceImpl extends AbstractService<Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentWithUserInfo> findUserInfoWithCommentByField(String fundId,String managerId,String rankId,String userId,String isRead){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fundId",fundId);
        jsonObject.put("managerId",managerId);
        jsonObject.put("rankId",rankId);
        jsonObject.put("userId",userId);
        jsonObject.put("isRead",isRead);
        return commentMapper.findUserInfoWithCommentByField(jsonObject);
    }

    @Override
    public int findNoReadCount(String userId,String isRead){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",userId);
        jsonObject.put("isRead",isRead);
        return commentMapper.findNoReadCount(jsonObject);
    }

    @Override
    public List<CommentWithUserInfo> findFriendChat(String userId, String friendId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("friendId", friendId);
        return commentMapper.findFriendChat(jsonObject);
    }

}
