package com.xn.sdhh.ao;

/**
 * 短信验证
 * @author: clockorange 
 * @since: Jul 31, 2018 5:49:43 PM 
 * @history:
 */

public interface ISmsOutAO {

    // 发送短信验证码
    public void sendCaptcha(String mobile, String bizType, String companyCode,
            String systemCode);

    // 发送内容
    public void sendContent(String tokenId, String userId, String content);

    // 发送内容
    public void sendContent(String tokenId, String mobile, String content,
            String companyCode, String systemCode);
}
