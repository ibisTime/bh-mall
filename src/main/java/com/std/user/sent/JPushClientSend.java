package com.std.user.sent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

public class JPushClientSend {
    protected static final Logger LOG = LoggerFactory
        .getLogger(JPushClient.class);

    public static boolean toSendPush(String appKey, String masterSecret,
            String noticeInfo) {
        boolean res = false;
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null,
            clientConfig);
        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_all_all_alert(noticeInfo);
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("responseCode:" + result.getResponseCode());
            LOG.info("Got result - " + result);
            if (200 == result.getResponseCode()) {
                res = true;
            }
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error(
                "Error response from JPush server. Should review and fix it. ",
                e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        } catch (Exception e) {
            LOG.info("Error Message: " + e.getMessage());
        }
        return res;
    }

    public static PushPayload buildPushObject_all_all_alert(String alert) {
        return PushPayload.alertAll(alert);
    }

    public static void main(String[] args) {
        System.out.println(toSendPush("e614d2a82d038160f707f1a8",
            "22d4796873b7f002537f30b6", "123"));
    }
}
