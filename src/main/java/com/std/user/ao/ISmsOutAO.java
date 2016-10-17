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
}
