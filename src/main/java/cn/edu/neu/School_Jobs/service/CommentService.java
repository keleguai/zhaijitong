package cn.edu.neu.School_Jobs.service;
import cn.edu.neu.School_Jobs.model.Comment;
import cn.edu.neu.School_Jobs.util.Service;
import cn.edu.neu.School_Jobs.vo.CommentWithUserInfo;

import java.util.List;

/**
 *
 * Created by fzb on 2019/06/02.
 */
public interface CommentService extends Service<Comment> {
    List<CommentWithUserInfo> findUserInfoWithCommentByField(String fundId,String managerId,String rankId,String userId,String isRead);
    int findNoReadCount(String userId,String isRead);

    List<CommentWithUserInfo> findFriendChat(String userId, String friendId);

    List<Comment> findDayHappy(String userId, String day);
}
