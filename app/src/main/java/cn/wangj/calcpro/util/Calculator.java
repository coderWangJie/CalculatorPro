package cn.wangj.calcpro.util;

import java.util.Calendar;

public class Calculator {

    /**
     * 计算信用卡账单日到最后还款日之间的天数
     * @param billDay 账单日
     * @param deadLine 最后还款日
     * @param today
     * @return
     */
    private static int countLong(int billDay, int deadLine, Calendar today) {
        int days = 0;
        if (billDay < deadLine) {
            days = deadLine - billDay;
        } else {
            days = 30 - billDay + deadLine;
        }
        return days;

    }

    public static void countDays(int billDay, int deadLineDay, Calendar today) {
        int dayCanUse;
        int day = today.get(Calendar.DAY_OF_MONTH);
        if (day < deadLineDay) {
            // 账单日之前刷卡
            dayCanUse = deadLineDay - day;
            dayCanUse = dayCanUse < 0 ? dayCanUse + 30 : dayCanUse;
        } else if (day > deadLineDay) {
            // 账单日之后刷卡
            dayCanUse = deadLineDay - 1;
        } else {
            dayCanUse = 999;
        }
    }


}
