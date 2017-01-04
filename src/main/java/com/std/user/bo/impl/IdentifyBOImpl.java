package com.std.user.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.std.user.bo.IIdentifyBO;
import com.std.user.dto.req.XN798001Req;
import com.std.user.dto.req.XN798006Req;
import com.std.user.dto.res.XN798001Res;
import com.std.user.dto.res.XN798006Res;
import com.std.user.http.BizConnecter;
import com.std.user.http.JsonUtils;

@Component
public class IdentifyBOImpl implements IIdentifyBO {
    static Logger logger = Logger.getLogger(IdentifyBOImpl.class);

    @Override
    public void doIdentify(String userId, String realName, String idKind,
            String idNo) {
        if (StringUtils.isNotBlank(realName)) {
            // try {
            XN798001Req req = new XN798001Req();
            req.setSystemId("1");
            req.setUserId(userId);
            req.setRealName(realName);
            req.setIdKind(idKind);
            req.setIdNo(idNo);
            req.setRemark("来自雄牛账户体系的实名认证");
            BizConnecter.getBizData("798001", JsonUtils.object2Json(req),
                XN798001Res.class);
            // } catch (Exception e) {
            // logger.error("调用实名认证服务异常");
            // }
        }
    }

    /** 
     * @see com.std.user.bo.IIdentifyBO#doFourIdentify(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void doFourIdentify(String userId, String realName, String idKind,
            String idNo, String cardNo, String bindMobile) {
        if (StringUtils.isNotBlank(realName)) {
            // try {
            XN798006Req req = new XN798006Req();
            req.setSystemId("3");
            req.setUserId(userId);
            req.setRealName(realName);
            req.setIdKind(idKind);
            req.setIdNo(idNo);
            req.setCardNo(cardNo);
            req.setBindMobile(bindMobile);
            req.setRemark("来自正汇钱包实名认证");
            BizConnecter.getBizData("798006", JsonUtils.object2Json(req),
                XN798006Res.class);
            // } catch (Exception e) {
            // logger.error("调用实名认证服务异常");
            // }
        }

    }
}
