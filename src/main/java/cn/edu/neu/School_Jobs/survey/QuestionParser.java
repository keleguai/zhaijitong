package cn.edu.neu.School_Jobs.survey;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class QuestionParser {

    private static final String QUESTION_DIVIDER = "<q/>";

    private static final String QUESTION_TEXT_IDENTIFIER = "<!#txt>";
    private static final String QUESTION_SELECT_SINGLE_IDENTIFIER = "<!#sls>";
    private static final String QUESTION_CHECKBOX_MULTIPLE_IDENTIFIER = "<!#cbm>";

    private static final String QUESTION_CONTENT_DIVIDER = "<c/>";
    private static final String QUESTION_ITEM_DIVIDER = "<i/>";

    public static JSONArray questionStringToJSONArray(String questionString) {
        JSONArray questionArray = new JSONArray();
        try {
            String[] splitQuestions = questionString.split(QUESTION_DIVIDER);
            for (String singleQuestionString : splitQuestions) {
                JSONObject questionObject = new JSONObject();
                String contentPart = singleQuestionString.substring(7);

                if (singleQuestionString.startsWith(QUESTION_SELECT_SINGLE_IDENTIFIER)) {
                    // 单选
                    questionObject.put("type", "select");
                    String[] contentAndItems = contentPart.split(QUESTION_CONTENT_DIVIDER);
                    questionObject.put("content", contentAndItems[0]);
                    questionObject.put("item", contentAndItems[1].split(QUESTION_ITEM_DIVIDER));
                } else if (singleQuestionString.startsWith(QUESTION_CHECKBOX_MULTIPLE_IDENTIFIER)) {
                    // 多选
                    questionObject.put("type", "checkbox");
                    String[] contentAndItems = contentPart.split(QUESTION_CONTENT_DIVIDER);
                    questionObject.put("content", contentAndItems[0]);
                    questionObject.put("item", contentAndItems[1].split(QUESTION_ITEM_DIVIDER));
                } else if (singleQuestionString.startsWith(QUESTION_TEXT_IDENTIFIER)) {
                    // 文本
                    questionObject.put("type", "text");
                    questionObject.put("content", contentPart);
                } else {
                    // 没有标识符按照文本处理
                    questionObject.put("type", "text");
                    questionObject.put("content", contentPart);
                }
                questionArray.add(questionObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionArray;
    }

    public static String questionJSONArrayToString(JSONArray questionArray) {
        StringBuilder questionString = new StringBuilder();
        try {
            for (int i = 0; i < questionArray.size(); i++) {
                JSONObject questionObject = questionArray.getJSONObject(i);
                switch (questionObject.getString("type")) {
                    case "text":
                        questionString.append(QUESTION_TEXT_IDENTIFIER);
                        questionString.append(questionObject.getString("content"));
                        break;
                    case "select":
                        questionString.append(QUESTION_SELECT_SINGLE_IDENTIFIER);
                        questionString.append(questionObject.getString("content"));
                        JSONArray selectContentArray = questionObject.getJSONArray("item");
                        questionString.append(QUESTION_CONTENT_DIVIDER);
                        for (int j = 0; j < selectContentArray.size(); j++) {
                            // 每组的问题字符串
                            questionString.append(selectContentArray.get(j));
                            if (j != selectContentArray.size() - 1) {
                                questionString.append(QUESTION_ITEM_DIVIDER);
                            }
                        }
                        break;
                    case "checkbox":
                        questionString.append(QUESTION_CHECKBOX_MULTIPLE_IDENTIFIER);
                        questionString.append(questionObject.getString("content"));
                        JSONArray checkboxContentArray = questionObject.getJSONArray("item");
                        questionString.append(QUESTION_CONTENT_DIVIDER);
                        for (int j = 0; j < checkboxContentArray.size(); j++) {
                            // 每组的问题字符串
                            questionString.append(checkboxContentArray.get(j));
                            if (j != checkboxContentArray.size() - 1) {
                                questionString.append(QUESTION_ITEM_DIVIDER);
                            }
                        }
                        break;
                }
                if (i != questionArray.size() - 1) {
                    // 最后一位不加分隔符
                    questionString.append(QUESTION_DIVIDER);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionString.toString();
    }

}
