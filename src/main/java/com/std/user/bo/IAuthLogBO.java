package com.std.user.bo;

import java.util.List;

import com.std.user.bo.base.IPaginableBO;
import com.std.user.domain.AuthLog;
import com.std.user.enums.EAuthType;

public interface IAuthLogBO extends IPaginableBO<AuthLog> {

    public String applyStudentAuth(String xuexinPic, String applyUser);

    public void reApplyStudentAuth(String xuexinPic, String applyUser);

    public void saveZmAuth(String userId, String idNo, String realName,
            String companyCode, String systemCode);

    public int approveAuthPass(String code, String approveUser, String result,
            String remark);

    public int approveAuthNoPass(String code, String approveUser, String remark);

    public List<AuthLog> queryAuthLogList(AuthLog condition);

    public AuthLog getAuthLog(String applyUser, EAuthType type);

    public AuthLog getAuthLog(String code);

}
