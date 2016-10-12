package com.std.user.ao;

import java.util.List;

import com.std.user.bo.base.Paginable;
import com.std.user.domain.CMenu;

public interface ICMenuAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCMenu(CMenu data);

    public int dropCMenu(String code);

    public int editCMenu(CMenu data);

    public int editCMenuStatus(String code);

    public Paginable<CMenu> queryCMenuPage(int start, int limit, CMenu condition);

    public List<CMenu> queryCMenuList(CMenu condition);

    public CMenu getCMenu(String code);

}
