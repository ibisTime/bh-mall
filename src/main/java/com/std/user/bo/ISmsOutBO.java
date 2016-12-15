package com.std.user.bo;

public interface ISmsOutBO {
    /**
     * 发送验证码
     * @param mobile
     * @param bizType
     * @param kind
     * @param systemCode 
     * @create: 2016年12月15日 上午7:29:42 xieyj
     * @history:
     */
    public void sendCaptcha(String mobile, String bizType, String kind,
            String systemCode);

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

    /**
     * @param mobile
     * @param content
     * @param bizType
     * @param companyCode 
     * @create: 2016年11月25日 上午9:25:54 xieyj
     * @history:
     */
    public void sendSmsOut(String mobile, String content, String bizType,
            String companyCode);

    // public void sendSms(String systemCode,String )
}
