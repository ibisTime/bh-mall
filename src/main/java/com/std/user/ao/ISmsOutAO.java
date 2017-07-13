package com.std.user.ao;

public interface ISmsOutAO {
    /**
     * 发送短信验证码
     * @param mobile
     * @param bizType
     * @param companyCode
     * @param systemCode 
     * @create: 2016年12月15日 上午7:28:43 xieyj
     * @history:
     */
    public void sendCaptcha(String mobile, String bizType, String companyCode,
            String systemCode);

    /**
     * 发送内容
     * @param tokenId
     * @param userId
     * @param content 
     * @create: 2016年11月10日 上午10:30:53 xieyj
     * @history:
     */
    public void sendContent(String tokenId, String userId, String content);
}
