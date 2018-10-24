/**
 * @Title OrderNoGenerater.java 
 * @Package com.ibis.account.core 
 * @Description 
 * @author miyb  
 * @date 2015-5-5 下午1:13:04 
 * @version V1.0   
 */
package com.xn.sdhh.core;

import java.util.Calendar;
import java.util.Random;

import com.xn.sdhh.common.DateUtil;

/** 
 * @author: miyb 
 * @since: 2015-5-5 下午1:13:04 
 * @history:
 */
public class OrderNoGenerater {

    /**
     * 产生毫秒级别主键序列
     * @param prefix
     * @return 
     * @create: 2015年9月28日 下午5:18:38 xieyj
     * @history:
     */
    public static String generate(String prefix) {
        int random = Math.abs(new Random().nextInt()) % 10000;
        String today = DateUtil.getToday(DateUtil.DATA_TIME_PATTERN_4);
        return prefix + today + String.valueOf(random);
    }

    /**
     * 生成条形码，开头可以为690-692
     * @return 
     * @create: 2018年7月2日 上午1:07:35 nyc
     * @history:
     */
    public static String generate() {
        Calendar calendar = Calendar.getInstance();
        Random rand = new Random();

        Long random = calendar.getTimeInMillis() / 10000 - rand.nextInt();
        if (random < 0) {
            random = -random;
        }

        String str = String.valueOf(rand.nextInt(900) + 100)
                + String.valueOf(random.toString());

        // 少于12位，随机补充
        int lack = 12 - str.length();
        if (lack > 0) {
            for (int i = 0; i < lack; i++) {
                str = str + String.valueOf(rand.nextInt(9));
            }
            // 多余12位，截取
        } else if (lack < 0) {
            str = str.substring(0, 12);
        }

        char[] array = str.toCharArray();

        // step1:奇数位的和
        Long even = 0L;
        for (int i = 0; i < array.length; i = i + 2) {
            even = even + Integer.parseInt(String.valueOf(array[i]));
        }
        // step2:偶数位的和
        Long uneven = 0L;
        for (int i = 1; i < array.length; i = i + 2) {
            uneven = uneven + Integer.parseInt(String.valueOf(array[i]));
        }
        // step3：偶数和×3+奇数和
        Long sum = even + uneven * 3;
        // step4：取step3和个位数的值
        Long unit = 0L;
        // 超过100
        if (sum >= 100) {
            unit = sum % 10;
        } else {
            unit = sum % 10;
        }

        // step5:10减去step4得到的个位数，如果结果==10，取0
        if (unit != 0) {
            unit = 10 - unit;
        } else {
            unit = 0L;
        }
        return str + unit.toString();
    }

    /**
     * 生成防伪/溯源码
     * @return 
     * @create: 2018年7月2日 上午1:08:31 nyc
     * @history:
     */

    public static String generateTrace() {
        Calendar calendar = Calendar.getInstance();
        Random rand = new Random();
        Long random = calendar.getTimeInMillis() / 100 - rand.nextInt();

        if (random < 0) {
            random = -random;
        }

        String str = String.valueOf(rand.nextInt(90) + 10)
                + String.valueOf(random.toString());
        // 少于12位，随机补充
        int lack = 12 - str.length();
        if (lack > 0) {
            for (int i = 0; i < lack; i++) {
                str = str + String.valueOf(rand.nextInt(9));
            }
            // 多余12位，截取
        } else if (lack < 0) {
            str = str.substring(0, 12);
        }
        return str;
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Random rand = new Random();
        Long random = calendar.getTimeInMillis() / 100 - rand.nextInt();

        if (random < 0) {
            random = -random;
        }

        String str = String.valueOf(rand.nextInt(90) + 10)
                + String.valueOf(random.toString());
        // 少于12位，随机补充
        int lack = 12 - str.length();
        if (lack > 0) {
            for (int i = 0; i < lack; i++) {
                str = str + String.valueOf(rand.nextInt(9));
            }
            // 多余12位，截取
        } else if (lack < 0) {
            str = str.substring(0, 12);
        }
        System.out.println(str);
    }

}
