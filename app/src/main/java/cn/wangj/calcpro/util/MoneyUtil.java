package cn.wangj.calcpro.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 和货币金额先关的工具类
 * Created by WangJ on 2017/8/9.
 */
public class MoneyUtil {
    private static final String TAG = MoneyUtil.class.getSimpleName();

    /**
     * 将double类型的金额###,##0.00格式化，用于显示
     *
     * @param value 金额的double值
     * @return 格式化金额字符串（形如10,000.00）
     */
    public static String formatMoney2Show(double value) {
        BigDecimal amount = new BigDecimal(value);
        // #和0都是占位符，0作占位符时若遇空位则补0，#做占位符空位不处理
        DecimalFormat format = new DecimalFormat("###,##0.00");
        return format.format(amount);
    }

    /**
     * 将String显示的金额###,##0.00格式化，用于显示
     *
     * @param moneyStr 金额字符串
     * @return 格式化后的金额字符串，形如1,000.00，输入为空则返回"--"
     */
    public static String formatMoney2Show(String moneyStr) {
        if (StringUtil.isEmpty(moneyStr)) {
            Logger.e(TAG, "the parameter of method formatMoney2Show(String moneyStr) is null");
            return "--";
        }

        BigDecimal amount = new BigDecimal(moneyStr.replace(",", ""));
        // #和0都是占位符，0作占位符时若遇空位则补0，#做占位符空位不处理
        DecimalFormat format = new DecimalFormat("###,##0.00");
        return format.format(amount);
    }

    /**
     * 金额###0.00格式化，用于网络传输
     *
     * @param moneyStr 金额字符串
     * @return ####0.00格式化后的字符串
     */
    public static String formatMoney2Trans(String moneyStr) {
        if (StringUtil.isEmpty(moneyStr)) {
            Logger.e(TAG, "the parameter of method formatMoney2Trans(String moneyStr) is null");
            return "--";
        }

        BigDecimal amount = new BigDecimal(moneyStr.replace(",", ""));
        DecimalFormat format = new DecimalFormat("####0.00");
        return format.format(amount);
    }

    /**
     * 金额###0.00格式化，用于网络传输
     *
     * @param value 金额
     * @return ####0.00格式结果字符串
     */
    public static String formatMoney2Trans(double value) {
        BigDecimal amount = new BigDecimal(value);
        // #和0都是占位符，0作占位符时若遇空位则补0，#做占位符空位不处理
        DecimalFormat format = new DecimalFormat("####0.00");
        return format.format(amount);
    }

    /**
     * 获取金额字符串的double值
     *
     * @param stringStr 字符串
     * @return double值
     */
    public static double getMoneyValue(String stringStr) {
        if (StringUtil.isEmpty(stringStr)) {
            Logger.e(TAG, "the money String is null or ''");
            return 0;
        }

        stringStr = stringStr.replace(",", "");
        return Double.parseDouble(stringStr);
    }


    /**
     * 去掉小数点后面的0
     */
    public static String returnDoubleRemoveZero(String rate) {
        if (rate.indexOf(".") > 0) {
            //正则表达
            rate = rate.replaceAll("0+?$", "");//去掉后面无用的零
            rate = rate.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return rate;
    }

    /**
     * 段内位置表示
     */
    private static final char[] CHINESE_MONEY_CONVERT_GROUP_IN = {'拾', '佰', '仟'};
    /**
     * 段名表示
     */
    private static final char[] CHINESE_MONEY_CONVERT_GROUP_OUT = {'万', '亿', '兆'};
    /**
     * 数字表示
     */
    private static final char[] CHINESE_MONEY_CONVERT_DIGIT = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
    /**
     * 单位表示
     */
    private static final String[] CHINESE_MONEY_UNIT = {"整", "分", "角", "圆"};

    /**
     * 显示中文大写金额
     *
     * @param money 带小数点的实际金额字符,不能含有","
     * @return String
     */
    public static String formatMoneyNum2Chinese(String money) {
        try {
            if (money == null
                    || money.equals("--")
                    || money.replace(" ", "").length() == 0) {
                return "";
            }
            int dotPostion = money.indexOf('.');
            if (money.length() == dotPostion + 1) {
                money = money.substring(0, dotPostion);
                dotPostion = -1;
            }

            int decimalPart = 0; // 小数部分
            String integerPart = ""; // 整数部分
            if (dotPostion > 0) {
                String temp_decimalPart = (money.substring(dotPostion + 1))
                        .trim();
                if (temp_decimalPart.length() == 1) {
                    decimalPart = Integer.parseInt(temp_decimalPart);
                    decimalPart *= 10;
                } else {
                    decimalPart = Integer.parseInt(temp_decimalPart);
                }
                integerPart = money.substring(0, dotPostion);
            } else if (dotPostion == 0) {
                integerPart = "0";
                String temp_decimalPart = (money.substring(1)).trim();
                if (temp_decimalPart.length() == 1) {

                    decimalPart = Integer.parseInt(temp_decimalPart);
                    decimalPart *= 10;
                } else {
                    decimalPart = Integer.parseInt(temp_decimalPart);
                }

            } else {
                integerPart = money;
            }
            StringBuffer prefix = new StringBuffer(); // 整数部分转化的结果
            StringBuffer suffix = new StringBuffer(); // 小数部分转化的结果
            // 处理小数点后面的数
            if (decimalPart == 0) { // 如果小数部分为0
                suffix.append(CHINESE_MONEY_UNIT[0]);
            } else {
                if (decimalPart < 10) {
                    // 转换分
                    if (!integerPart.equals("0")) {
                        suffix.append("零");
                    }
                    suffix.append(CHINESE_MONEY_CONVERT_DIGIT[decimalPart]);
                    suffix.append(CHINESE_MONEY_UNIT[1]);
                } else if (decimalPart % 10 == 0) {
                    // 转换角
                    suffix.append(CHINESE_MONEY_CONVERT_DIGIT[decimalPart / 10]);
                    suffix.append(CHINESE_MONEY_UNIT[2]);
                    /**
                     * @author wangcheng 金额大写
                     */
                    // suffix.append(CHINESE_MONEY_UNIT[0]);
                } else {
                    // 转换角分
                    suffix.append(CHINESE_MONEY_CONVERT_DIGIT[decimalPart / 10]);
                    suffix.append(CHINESE_MONEY_UNIT[2]);
                    suffix.append(CHINESE_MONEY_CONVERT_DIGIT[decimalPart % 10]);
                    suffix.append(CHINESE_MONEY_UNIT[1]);
                }
            }
            // 处理小数点前面的数
            char[] chDig = integerPart.toCharArray(); // 把整数部分转化成字符数组
            boolean zero = false; // 标志'0'表示出现过0
            byte zeroSerNum = 0; // 连续出现0的次数
            for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
                int idx = (chDig.length - i - 1) % 4; // 取段内位置
                int vidx = (chDig.length - i - 1) / 4; // 取段位置
                if (chDig[i] == '0') { // 如果当前字符是0
                    zeroSerNum++; // 连续0次数递增
                    if (!zero) { // 标志
                        // zero =
                        // CHINESE_MONEY_CONVERT_DIGIT[0];
                        zero = true;
                        if (idx == 0 && vidx > 0) {
                            prefix.append(CHINESE_MONEY_CONVERT_GROUP_OUT[vidx - 1]); // 段结束位置应该加上段名如万,亿
                        }
                        if (idx == 0 && vidx == 0 && chDig.length == 1
                                && decimalPart == 0) {
                            // 整数部分为零且小数部分也要为零
                            prefix.append(CHINESE_MONEY_CONVERT_DIGIT[0]);
                        }
                    } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                        prefix.append(CHINESE_MONEY_CONVERT_GROUP_OUT[vidx - 1]);
                        zero = false;
                    }
                    continue;
                }
                zeroSerNum = 0; // 连续0次数清零
                if (zero) { // 如果标志不为0,则加上,例如万,亿什么的
                    prefix.append(CHINESE_MONEY_CONVERT_DIGIT[0]);
                    zero = false;
                }
                prefix.append(CHINESE_MONEY_CONVERT_DIGIT[chDig[i] - '0']); // 转化该数字表示
                if (idx > 0) {
                    prefix.append(CHINESE_MONEY_CONVERT_GROUP_IN[idx - 1]);
                }
                if (idx == 0 && vidx > 0) {
                    prefix.append(CHINESE_MONEY_CONVERT_GROUP_OUT[vidx - 1]); // 段结束位置应该加上段名如万,亿
                }
            }
            if (prefix.length() > 0)
                prefix.append(CHINESE_MONEY_UNIT[3]); // 如果整数部分存在,则有圆的字样
            return prefix.append(suffix).toString(); // 返回正确表示
        } catch (Exception e) {
            Logger.e(TAG, e.getMessage());
            return "input error";
        }
    }
}
