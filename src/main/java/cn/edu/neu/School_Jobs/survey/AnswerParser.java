package cn.edu.neu.School_Jobs.survey;

import com.alibaba.fastjson.JSONArray;

public class AnswerParser {
    private static final String ANSWER_DIVIDER = "<a/>";

    public static String answerJSONArrayToString(JSONArray answerArray) {
        StringBuilder answerString = new StringBuilder();
        for (int j = 0; j < answerArray.size(); j++) {
            String temp = answerArray.getString(j);
            // 过滤隔离符
            temp = temp.replace("<a/>", "");
            answerString.append(temp);
            if (j != answerArray.size() - 1) {
                answerString.append("<a/>");
            }
        }
        return answerString.toString();
    }

    public static JSONArray answerStringToJSONArray(String answerString) {
        return (JSONArray) JSONArray.toJSON(answerString.split(ANSWER_DIVIDER));
    }
}
