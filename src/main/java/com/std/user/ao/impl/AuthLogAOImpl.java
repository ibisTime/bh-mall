package com.std.user.ao.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.user.ao.IAuthLogAO;
import com.std.user.bo.IAuthLogBO;
import com.std.user.bo.IUserBO;
import com.std.user.bo.base.Paginable;
import com.std.user.common.DateUtil;
import com.std.user.domain.AuthLog;
import com.std.user.domain.User;
import com.std.user.enums.EAuthStatus;
import com.std.user.enums.EAuthType;
import com.std.user.enums.EBoolean;
import com.std.user.exception.BizException;

@Service
public class AuthLogAOImpl implements IAuthLogAO {

    @Autowired
    private IAuthLogBO authLogBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public void applyStudentAuth(String xuexinPic, String applyUser) {
        User user = userBO.getCheckUser(applyUser);
        AuthLog data = authLogBO.getAuthLog(applyUser, EAuthType.STUDENT);
        if (data != null) {
            if (EAuthStatus.APPROVE_YES.getCode().equals(data.getStatus())) {
                throw new BizException("xn0000", "学生认证已通过，无需重复认证");
            } else {
                authLogBO.reApplyStudentAuth(data, xuexinPic, applyUser);
            }
        } else {
            authLogBO.applyStudentAuth(xuexinPic, user);
        }
    }

    @Override
    @Transactional
    public void approveStudentAuth(String code, String approveResult,
            String approveUser, String gradDatetime, String remark) {
        AuthLog data = authLogBO.getAuthLog(code);
        if (EAuthStatus.APPROVE_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "学生认证已通过，不能重复审核");
        }
        if (EBoolean.YES.getCode().equals(approveResult)) {
            if (StringUtils.isBlank(gradDatetime)) {
                throw new BizException("xn0000", "请填写毕业时间");
            }
            authLogBO.approveAuthPass(code, approveUser, gradDatetime, remark);
            Date gradDate = DateUtil.getFrontDate(gradDatetime, false);
            userBO.refreshGradDatetime(data.getApplyUser(), gradDate);
        } else {
            authLogBO.approveAuthNoPass(code, approveUser, remark);
        }
    }

    @Override
    public Paginable<AuthLog> queryAuthLogPage(int start, int limit,
            AuthLog condition) {
        return authLogBO.getPaginable(start, limit, condition);
    }

    @Override
    public AuthLog getAuthLog(String code) {
        return authLogBO.getAuthLog(code);
    }

    @Override
    public AuthLog getAuthLog(String userId, String type) {
        EAuthType eAuthType = EAuthType.getMap().get(type);
        if (null == eAuthType) {
            throw new BizException("xn0000", "类型错误");
        }
        return authLogBO.getAuthLog(userId, eAuthType);
    }
}
