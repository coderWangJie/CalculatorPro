package cn.wangj.nets;

import com.alibaba.fastjson.JSONObject;

public class RequestVo {

    private String requestType;
    private JSONObject requestParam;

    /**
     * 默认构造函数私有化
     */
    private RequestVo() {
    }

    public RequestVo createJsonRequest() {
        requestType = "application/json; charset=utf-8";
        requestParam = new JSONObject();
        // TODO 日志打印
        return null;
    }

    public void addParam(String name, String value) {
        if (requestParam.put(name, value) != null) {
            // TODO 提示重复插值，将会被覆盖

        }
    }

    public String getRequestType() {
        return requestType;
    }
}
