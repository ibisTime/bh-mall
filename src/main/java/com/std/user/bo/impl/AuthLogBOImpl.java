package com.std.user.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.user.bo.IAuthLogBO;
import com.std.user.bo.base.PaginableBOImpl;
import com.std.user.core.EGeneratePrefix;
import com.std.user.core.OrderNoGenerater;
import com.std.user.dao.IAuthLogDAO;
import com.std.user.domain.AuthLog;
import com.std.user.domain.User;
import com.std.user.enums.EAuthStatus;
import com.std.user.enums.EAuthType;
import com.std.user.enums.EIDKind;
import com.std.user.exception.BizException;

@Component
public class AuthLogBOImpl extends PaginableBOImpl<AuthLog> implements
        IAuthLogBO {

    @Autowired
    private IAuthLogDAO authLogDAO;

    @Override
    public String applyStudentAuth(String xuexinPic, User user) {
        String code = null;
        if (StringUtils.isNotBlank(xuexinPic)) {
            AuthLog data = new AuthLog();
            code = OrderNoGenerater.generate(EGeneratePrefix.Auth.getCode());
            data.setCode(code);
            data.setType(EAuthType.STUDENT.getCode());
            data.setAuthArg1(xuexinPic);
            data.setStatus(EAuthStatus.TO_APPROVE.getCode());
            data.setApplyUser(user.getUserId());
            data.setApplyDatetime(new Date());
            data.setCompanyCode(user.getCompanyCode());
            data.setSystemCode(user.getSystemCode());
            authLogDAO.insert(data);
        }
        return code;
    }

    @Override
    public void reApplyStudentAuth(AuthLog data, String xuexinPic,
            String applyUser) {
        if (data != null && StringUtils.isNotBlank(xuexinPic)) {
            data.setAuthArg1(xuexinPic);
            data.setStatus(EAuthStatus.TO_APPROVE.getCode());
            data.setApplyDatetime(new Date());
            data.setRemark("重新申请学生认证");
            authLogDAO.reApplyAuth(data);
        }
    }

    @Override
    public void saveZmAuth(String userId, String idNo, String realName,
            String companyCode, String systemCode) {
        EAuthType eAuthType = EAuthType.ZM_SCORE;
        AuthLog authLog = getAuthLog(userId, EAuthType.ZM_SCORE);
        if (null == authLog) {
            String code = OrderNoGenerater.generate(EGeneratePrefix.Auth
                .getCode());
            AuthLog data = new AuthLog();
            data.setCode(code);
            data.setType(eAuthType.getCode());
            data.setAuthArg1(EIDKind.IDCard.getCode());
            data.setAuthArg2(idNo);
            data.setAuthArg3(realName);
            data.setStatus(EAuthStatus.TO_APPROVE.getCode());
            data.setApplyUser(userId);
            data.setApplyDatetime(new Date());
            data.setRemark("用户芝麻授权");
            data.setCompanyCode(companyCode);
            data.setSystemCode(systemCode);
            authLogDAO.insert(data);
        } else {
            if (!EAuthStatus.APPROVE_YES.getCode().equals(authLog.getStatus())) {
                authLog.setAuthArg1(EIDKind.IDCard.getCode());
                authLog.setAuthArg2(idNo);
                authLog.setAuthArg3(realName);
                authLog.setStatus(EAuthStatus.TO_APPROVE.getCode());
                authLog.setApplyDatetime(new Date());
                authLog.setRemark("用户芝麻重新授权");
                authLogDAO.reApplyAuth(authLog);
            }
        }
    }

    @Override
    public int approveAuthPass(String code, String approveUser, String result,
            String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            AuthLog data = new AuthLog();
            data.setCode(code);
            data.setStatus(EAuthStatus.APPROVE_YES.getCode());
            data.setApproveUser(approveUser);
            data.setApproveDatetime(new Date());
            data.setResult(result);
            data.setRemark(remark);
            count = authLogDAO.approveAuth(data);
        }
        return count;
    }

    @Override
    public int approveAuthNoPass(String code, String approveUser,
            String result, String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            AuthLog data = new AuthLog();
            data.setCode(code);
            data.setStatus(EAuthStatus.APPROVE_NO.getCode());
            data.setApproveUser(approveUser);
            data.setApproveDatetime(new Date());
            data.setResult(result);
            data.setRemark(remark);
            count = authLogDAO.approveAuth(data);
        }
        return count;
    }

    @Override
    public List<AuthLog> queryAuthLogList(AuthLog condition) {
        return authLogDAO.selectList(condition);
    }

    @Override
    public AuthLog getAuthLog(String applyUser, EAuthType type) {
        AuthLog data = null;
        if (StringUtils.isNotBlank(applyUser)) {
            AuthLog condition = new AuthLog();
            condition.setApplyUser(applyUser);
            condition.setType(type.getCode());
            List<AuthLog> dataList = authLogDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(dataList)) {
                data = dataList.get(0);
            }
        }
        return data;
    }

    @Override
    public AuthLog getAuthLog(String code) {
        AuthLog data = null;
        if (StringUtils.isNotBlank(code)) {
            AuthLog condition = new AuthLog();
            condition.setCode(code);
            data = authLogDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "认证记录不存在");
            }
        }
        return data;
    }
}
