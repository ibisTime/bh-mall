package com.std.user.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.user.ao.ICompanyAO;
import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.common.PropertiesUtil;
import com.std.user.core.StringValidater;
import com.std.user.domain.Company;
import com.std.user.domain.User;
import com.std.user.dto.req.XN805901Req;
import com.std.user.dto.res.XN805901Res;
import com.std.user.enums.EBoolean;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 获取用户信息
 * @author: myb858 
 * @since: 2015年11月1日 下午2:56:28 
 * @history:
 */
public class XN805901 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN805901Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String userId = req.getUserId();
        User user = userAO.doGetUser(userId);
        XN805901Res res = new XN805901Res();
        if (user != null) {
            res.setUserId(userId);
            if (StringUtils.isNotBlank(user.getLoginName())) {
                res.setLoginName(user.getLoginName());
            } else {
                res.setLoginName(user.getMobile());
            }
            res.setMobile(user.getMobile());
            res.setOpenId(user.getOpenId());
            res.setPhoto(PropertiesUtil.getProperty("PHOTO_URL"));
            res.setStatus(user.getStatus());
            res.setLevel(user.getLevel());
            res.setKind(user.getKind());
            res.setUserReferee(user.getUserReferee());
            res.setRealName(user.getRealName());

            if (StringUtils.isNotBlank(user.getIdNo())) {
                res.setIdentityFlag(EBoolean.YES.getCode());
            } else {
                res.setIdentityFlag(EBoolean.NO.getCode());
            }
            if (StringUtils.isNotBlank(user.getTradePwdStrength())) {
                res.setTradepwdFlag(EBoolean.YES.getCode());
            } else {
                res.setTradepwdFlag(EBoolean.NO.getCode());
            }
            if (StringUtils.isNotBlank(user.getIdNo())) {
                res.setIdentityFlag(EBoolean.YES.getCode());
            } else {
                res.setIdentityFlag(EBoolean.NO.getCode());
            }
            res.setTotalFansNum(String.valueOf(user.getTotalFansNum()));
            res.setTotalFollowNum(String.valueOf(user.getTotalFollowNum()));
            if (user.getCompanyCode() == null) {
                Company company = companyAO.getCompanyByUserId(res.getUserId());
                if (company != null) {
                    user.setCompanyCode(company.getCode());
                }
            }
            res.setCompanyCode(user.getCompanyCode());
            res.setUserExt(user.getUserExt());
        }
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805901Req.class);
        StringValidater.validateBlank(req.getTokenId(), req.getUserId());
    }
}
