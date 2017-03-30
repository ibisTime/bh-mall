package com.std.user.third.wechat.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.std.user.bo.ICPasswordBO;
import com.std.user.domain.CPassword;
import com.std.user.exception.BizException;
import com.std.user.util.HttpsUtil;

@Service
public class TokenSingleton {

    @Autowired
    protected ICPasswordBO cPasswordBO;

    // 缓存accessToken 的Map ,map中包含 一个accessToken 和 缓存的时间戳
    // 当然也可以分开成两个属性咯

    public final static String weixin_jssdk_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    private Map<String, String> map = new HashMap<>();

    private TokenSingleton() {
    }

    private static TokenSingleton single = null;

    // 静态工厂方法
    public static TokenSingleton getInstance() {
        if (single == null) {
            single = new TokenSingleton();
        }
        return single;
    }

    public Map<String, String> getMap(String systemCode, String companyCode) {

        String time = map.get(systemCode + "." + companyCode + ".time");
        String accessToken = map.get(systemCode + "." + companyCode
                + ".access_token");
        Long nowDate = new Date().getTime();

        if (accessToken != null && time != null
                && nowDate - Long.parseLong(time) < 3000 * 1000) {
            // result = accessToken;
            System.out.println("accessToken存在，且没有超时 ， 返回单例");
        } else {
            System.out.println("accessToken超时 ， 或者不存在 ， 重新获取");
            String access_token = getAccessToken(systemCode, companyCode);
            // "这里是直接调用微信的API去直接获取 accessToken 和Jsapi_ticket 获取";
            String jsapi_token = getJsapiToken(systemCode, companyCode);
            // "获取jsapi_token";
            map.put(systemCode + "." + companyCode + "time", nowDate + "");
            map.put(systemCode + "." + companyCode + "access_token",
                access_token);
            map.put(systemCode + "." + companyCode + "jsapi_token", jsapi_token);
            // result = access_token;
        }

        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public static TokenSingleton getSingle() {
        return single;
    }

    public static void setSingle(TokenSingleton single) {
        TokenSingleton.single = single;
    }

    public String getAccessToken(String systemCode, String companyCode) {
        String accessToken = null;

        return accessToken;
    }

    public String getJsapiToken(String systemCode, String companyCode) {
        String jsapi_ticket = null;
        String appId = "";
        String appSecret = "";
        CPassword condition = new CPassword();
        condition.setType("3");
        condition.setAccount("ACCESS_KEY");
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        List<CPassword> result = cPasswordBO.queryCPasswordList(condition);
        if (CollectionUtils.isEmpty(result)) {
            throw new BizException("XN000000", "微信公众号appId配置获取失败，请检查配置");
        }
        appId = result.get(0).getPassword();
        condition.setAccount("SECRET_KEY");
        result = cPasswordBO.queryCPasswordList(condition);
        if (CollectionUtils.isEmpty(result)) {
            throw new BizException("XN000000", "微信公众号appSecret配置获取失败，请检查配置");
        }
        appSecret = result.get(0).getPassword();
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?";
        String params = "grant_type=client_credential&appid=" + appId
                + "&secret=" + appSecret + "";
        String result1;
        try {
            result1 = new String(HttpsUtil.post(requestUrl, params, "utf-8"));
            String access_token = JSONObject.parseObject(result1).getString(
                "access_token");
            requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?";
            params = "access_token=" + access_token + "&type=jsapi";
            result1 = new String(HttpsUtil.post(requestUrl, params, "utf-8"));
            jsapi_ticket = JSONObject.parseObject(result1).getString("ticket");
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsapi_ticket;

    }
}
