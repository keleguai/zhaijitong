package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.Comment;
import cn.edu.neu.School_Jobs.util.MyMapper;
import cn.edu.neu.School_Jobs.vo.CommentWithUserInfo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface CommentMapper extends MyMapper<Comment> {
    List<CommentWithUserInfo> findUserInfoWithCommentByField(JSONObject jsonObject);
    int findNoReadCount(JSONObject jsonObject);

    List<CommentWithUserInfo> findFriendChat(JSONObject jsonObject);
}
