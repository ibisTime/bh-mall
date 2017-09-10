package com.std.user.ao;

import com.std.user.bo.base.Paginable;
import com.std.user.domain.AuthLog;

public interface IAuthLogAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void applyStudentAuth(String xuexinPic, String applyUser);

    public void approveStudentAuth(String code, String approveResult,
            String approveUser, String gradDatetime, String remark);

    public Paginable<AuthLog> queryAuthLogPage(int start, int limit,
            AuthLog condition);

    public AuthLog getAuthLog(String code);

}
