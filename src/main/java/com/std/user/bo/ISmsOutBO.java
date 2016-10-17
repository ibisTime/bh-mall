package com.std.user.bo;

public interface ISmsOutBO {
    /**
     * 发送验证码
     * @param mobile
     * @param bizType
     * @return 
     * @create: 2016年10月17日 下午5:49:10 xieyj
     * @history:
     */
    public void sendCaptcha(String mobile, String bizType);

    /**
     * 验证用户验证码是否正确
     * @param mobile
     * @param captcha
     * @param bizType
     * @return 
     * @create: 2016年10月17日 下午5:47:21 xieyj
     * @history:
     */
    public void checkCaptcha(String mobile, String captcha, String bizType);

    /**
     * 发送指定内容短信
     * @param mobile
     * @param content
     * @param bizType
     * @return 
     * @create: 2016年10月17日 下午5:47:45 xieyj
     * @history:
     */
    public void sendSmsOut(String mobile, String content, String bizType);

}
