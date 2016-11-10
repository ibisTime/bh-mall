package com.std.user.ao;

public interface ISmsOutAO {
    /**
     * 发送短信验证码
     * @param mobile
     * @param bizType
     * @return 
     * @create: 2016年10月17日 下午7:27:05 xieyj
     * @history:
     */
    public void sendCaptcha(String mobile, String bizType);

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
