package cn.edu.neu.School_Jobs.controller;
import cn.edu.neu.School_Jobs.model.Friend;
import cn.edu.neu.School_Jobs.model.UserInfo;
import cn.edu.neu.School_Jobs.service.FriendService;
import cn.edu.neu.School_Jobs.service.UserInfoService;
import cn.edu.neu.School_Jobs.util.Jwt;
import cn.edu.neu.School_Jobs.util.constants.ErrorEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

import cn.edu.neu.School_Jobs.util.CommonUtil;
import cn.edu.neu.School_Jobs.conf.exception.CommonJsonException;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Created by zzc on 2019/06/13.
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendService friendService;
    @Autowired
    UserInfoService userInfoService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public JSONObject findFriend(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

    PageHelper.startPage(pageNum, pageSize);
    List<Friend> list = friendService.findAll();
    PageInfo pageInfo = new PageInfo(list);
    return CommonUtil.successJson(pageInfo);
    }

    @GetMapping("/is/friend/{friendId}")
    public JSONObject findIsFriend(HttpServletRequest request, @PathVariable(value = "friendId") String friendId) {
        JSONObject jsonObject = new JSONObject();
        if (friendService.findIsFriend(String.valueOf(Jwt.getUserId(request)), friendId) == 2) {
            jsonObject.put("isFriend", true);
            return CommonUtil.successJson(jsonObject);
        }
        jsonObject.put("isFriend", false);
        return CommonUtil.successJson(jsonObject);
    }

    @GetMapping("/me/{page}")
    public JSONObject findMyFriend(HttpServletRequest request, @PathVariable(value = "page") int page) {
        PageHelper.startPage(page, 5);
        int userId = Jwt.getUserId(request);
        List<UserInfo> userInfos = userInfoService.findFriendByUserId(String.valueOf(userId));
        PageInfo pageInfo = new PageInfo(userInfos);
        return CommonUtil.successJson(pageInfo);
    }

    @GetMapping("/me/add/friend")
    public JSONObject findAddingFriend(HttpServletRequest request) {
        int userId = Jwt.getUserId(request);
        List<Friend> myself = friendService.findReadyAgreeFriend(String.valueOf(userId), null);
        List<Friend> others = friendService.findReadyAgreeFriend(null, String.valueOf(userId));
        Set<Integer> hisFriendIds = new HashSet<>();
        List<Integer> notAgreeFriend = new ArrayList<>();
        if (others != null) {
            for (Friend friend : others) {
                hisFriendIds.add(friend.getUserId());
            }
        }
        if (myself != null) {
            for (Friend friend : myself) {
                if (!hisFriendIds.contains(friend.getFriendId())) {
                    notAgreeFriend.add(friend.getFriendId());
                }
            }
        }
        List<UserInfo> notAgreeFriendInfo = new ArrayList<>();
        for (int uid : notAgreeFriend) {
            notAgreeFriendInfo.add(userInfoService.findById(uid));
        }
        return CommonUtil.successJson(notAgreeFriendInfo);
    }

    @GetMapping("/me/agree/friend")
    public JSONObject findAgreeFriend(HttpServletRequest request) {
        int userId = Jwt.getUserId(request);
        List<Friend> myself = friendService.findReadyAgreeFriend(String.valueOf(userId), null);
        List<Friend> others = friendService.findReadyAgreeFriend(null, String.valueOf(userId));
        Set<Integer> myFriendIds = new HashSet<>();
        List<Integer> notAgreeFriend = new ArrayList<>();
        if (myself != null) {
            for (Friend friend : myself) {
                myFriendIds.add(friend.getFriendId());
            }
        }
        if (others != null) {
            for (Friend friend : others) {
                if (!myFriendIds.contains(friend.getUserId())) {
                    notAgreeFriend.add(friend.getUserId());
                }
            }
        }
        List<UserInfo> notAgreeFriendInfo = new ArrayList<>();
        for (int uid : notAgreeFriend) {
            notAgreeFriendInfo.add(userInfoService.findById(uid));
        }
        return CommonUtil.successJson(notAgreeFriendInfo);
    }

    @PostMapping("/add")
    public JSONObject addFriend(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        //try{
            //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
          //  return e.getResultJson();
        //}
        int userId = Jwt.getUserId(request);
        Friend friend = JSONObject.toJavaObject(requestJson,Friend.class);
        friend.setUserId(userId);
        friend.setFriendId(Integer.parseInt(requestJson.getString("friend_id")));
        friend.setInsertTime(new Date());
        if (friend.getFriendId().intValue() == (friend.getUserId().intValue())) {
            return CommonUtil.errorJson(ErrorEnum.E_790);
        }
        if (friendService.findHasSend(String.valueOf(userId), String.valueOf(friend.getFriendId())) == 0) {
            friendService.save(friend);
        } else {
            friendService.update(friend);
        }
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

    @GetMapping("/delete/{id}")
    public JSONObject deleteFriend(@PathVariable(value = "id") int id, HttpServletRequest request) {
        int userId = Jwt.getUserId(request);
        friendService.deleteFriendByUserIdAndFriendId(String.valueOf(userId), String.valueOf(id));
        return CommonUtil.successJson();
    }


}
