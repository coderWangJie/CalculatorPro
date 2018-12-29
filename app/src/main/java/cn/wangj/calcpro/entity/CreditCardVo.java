package cn.wangj.calcpro.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 *
 */
public class CreditCardVo implements Serializable {

    private String bankName; // 发卡行
    private String alias; // 别名
    private String cardNO; // 卡号
    private int billDay;  // 账单日
    private int deadLineDay;  // 最后还款日

    public JSONObject getCreditCard2Storage() {
        JSONObject json = new JSONObject();
        json.put("", "");
        return json;
    }
}