package com.std.user.bo;

import java.util.List;

import com.std.user.bo.base.IPaginableBO;
import com.std.user.domain.CMenu;

public interface ICMenuBO extends IPaginableBO<CMenu> {

    public boolean isCMenuExist(String code);

    public String saveCMenu(CMenu data);

    public int removeCMenu(String code);

    public int refreshCMenu(CMenu data);

    public int refreshCMenuStatus(CMenu data);

    public List<CMenu> queryCMenuList(CMenu condition);

    public CMenu getCMenu(String code);

}
