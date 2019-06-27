package cn.edu.neu.School_Jobs.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by fzb on 2019/6/11
 */
public interface RedisServer {
    String testFunction(String value);

    Boolean set(String key, String value, int time);

    JSONObject get(String key);

    Boolean lpush(String arrayName, String value);

    List<String> getL(String Name);
}
