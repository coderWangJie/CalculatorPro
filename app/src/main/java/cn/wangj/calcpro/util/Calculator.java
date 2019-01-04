package cn.wangj.calcpro.util;

import java.util.Calendar;

public final class Calculator {

    public Calculator() {
    }

    /**
     * 计算信用卡账单日到最后还款日之间的天数，不同月份稍有不同
     *
     * @param billDay  账单日
     * @param deadLine 最后还款日
     * @param givenDay 账单日所在月份的任意一个日期点，主要用于获取当月最后一天的日期
     * @return 指定月份账单日到最后还款日之间的天数
     */
    private static int countDaysFromBillDayToDeadLine(int billDay, int deadLine, Calendar givenDay) {
        int daysBetween;
        /*
         * 出账日到最后还款日之间不会超过1个月：
         * （1）如果最后还款日>出账日可以判定为在同一个月；
         * （2）否则最后还款日在下一个月；
         */
        if (billDay < deadLine) {
            daysBetween = deadLine - billDay;
        } else {
            int max = givenDay.getActualMaximum(Calendar.DAY_OF_MONTH);
            daysBetween = max - billDay + deadLine;
        }
        return daysBetween;
    }

    /**
     * 计算信用卡划款日到该款项对应的最后还款日之间的天数，即免息用款天数
     *
     * @param billDay     信用卡每月账单日
     * @param deadLineDay 每月最后还款日
     * @param payment     划款日期
     * @return 划款日到该笔款最后还款日的天数
     */
    public static int countDaysFromPaymentToDeadLine(int billDay, int deadLineDay, Calendar payment) {
        int daysToDeadLine; // 划款日到该款项对应的最后还款日之间的间隔天数

        // 划款日
        int payDay = payment.get(Calendar.DAY_OF_MONTH);

        if (payDay < billDay) {
            /* 当前日期小于账单日，归入当月出账账单
             * 天数 = 账单日和划款日之间的天数 + 账单日到对应最后还款日之间的天数
             */
            daysToDeadLine = (billDay - payDay) + countDaysFromBillDayToDeadLine(billDay, deadLineDay, payment);
        } else {
            // 当前日期大于（或等于）账单日，归入下月出账账单
            int payDay2billDay = payment.getActualMaximum(Calendar.DAY_OF_MONTH) - payDay + billDay;
            payment.add(Calendar.MONTH, 1);  // 切到下个月，计算下次账单日到最后还款日的天数
            daysToDeadLine = payDay2billDay + countDaysFromBillDayToDeadLine(billDay, deadLineDay, payment);
        }
        return daysToDeadLine;
    }



}
