package com.xn.sdhh.util.wechat;

import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.xn.sdhh.common.DateUtil;

/**
 * 订单模板
 * {
 *    touser ： 接收者openId
 *    template_id ： 模板编号
 *    data ： {
 *      first ： {
 *          "value" : 内容
 *          "value" ： 字体颜色
 *      }
 *      first ： {
 *          "value" : 内容
 *          "value" ： 字体颜色
 *      }
 *    }
 * }
 * @author: nyc 
 * @since: 2018年9月6日 下午2:19:23 
 * @history:
 */
public class WxOrderMessageUtil {

    //
    private static final String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    // 字体颜色
    private static final String color = "#173177";

    public static JSONObject OrderTemplate(String openId, String userName,
            String productName, Long amount, String templateId,
            String accessToken) {
        // 根据模板格式拼装数据
        Map<String, Object> data = new LinkedHashMap<String, Object>();

        LinkedHashMap<String, String> first = new LinkedHashMap<String, String>();
        first.put("value", userName + "，您已成功下单！");
        first.put("color", color);
        data.put("first", first);

        LinkedHashMap<String, String> keyword1 = new LinkedHashMap<String, String>();
        keyword1.put("value", productName);
        keyword1.put("color", color);
        data.put("keyword1", keyword1);

        LinkedHashMap<String, String> keyword2 = new LinkedHashMap<String, String>();
        keyword1.put("value", amount + "元");
        keyword1.put("color", color);
        data.put("keyword2", keyword2);

        LinkedHashMap<String, String> keyword3 = new LinkedHashMap<String, String>();
        keyword3.put("value", DateUtil.getToDay());
        keyword3.put("color", color);
        data.put("keyword3", keyword3);

        LinkedHashMap<String, String> remark = new LinkedHashMap<String, String>();
        remark.put("value", DateUtil.getToDay());
        remark.put("color", color);
        data.put("remark", remark);

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("touser", openId);
        map.put("template_id", templateId);
        map.put("url", url + accessToken);
        map.put("data", data);

        // JSONObject.pare
        return null;
    }
}
