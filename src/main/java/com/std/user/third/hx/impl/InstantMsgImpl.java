/**
 * @Title InstantMsg.java 
 * @Package com.std.user.third.hx.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年12月16日 上午10:12:04 
 * @version V1.0   
 */
package com.std.user.third.hx.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.user.bo.ICPasswordBO;
import com.std.user.enums.ECPwdType;
import com.std.user.exception.BizException;
import com.std.user.http.JsonUtils;
import com.std.user.third.hx.domain.UserReq;
import com.std.user.util.HttpsUtil;

/** 
 * 即时通信
 * @author: xieyj 
 * @since: 2016年12月16日 上午10:12:04 
 * @history:
 */
@Component
public class InstantMsgImpl {
    protected static final Logger logger = LoggerFactory
        .getLogger(InstantMsgImpl.class);

    @Autowired
    private ICPasswordBO cPasswordBO;

    public void doRegisterUser(String username, String password,
            String systemCode) {
        UserReq req = new UserReq();
        req.setUsername(username);
        req.setPassword(password);
        Map<String, String> resultMap = cPasswordBO.queryCPasswordList(
            ECPwdType.HX.getCode(), null, null, systemCode);
        String org_name = resultMap.get("org_name");
        String app_name = resultMap.get("app_name");
        String postUrl = "https://a1.easemob.com/" + org_name + "/" + app_name
                + "/users";
        try {
            HttpsUtil.post(postUrl, JsonUtils.object2Json(req), "UTF-8");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException("xn000000", "环信即时通信注册失败");
        }
    }
}
