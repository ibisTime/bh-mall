package com.std.user.ao;

import java.util.List;

import com.std.user.bo.base.Paginable;
import com.std.user.domain.CPassword;

public interface ICPasswordAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCPassword(CPassword data);

    public int dropCPassword(String code);

    public int editCPassword(CPassword data);

    public Paginable<CPassword> queryCPasswordPage(int start, int limit,
            CPassword condition);

    public List<CPassword> queryCPasswordList(CPassword condition);

    public CPassword getCPassword(String code);

}
