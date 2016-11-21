package com.std.user.bo;

import java.util.List;

import com.std.user.bo.base.IPaginableBO;
import com.std.user.domain.CPassword;

public interface ICPasswordBO extends IPaginableBO<CPassword> {

    public boolean isCPasswordExist(String code);

    public String saveCPassword(String type, String account, String password,
            String remark, String companyCode);

    public int removeCPassword(String code);

    public int refreshCPassword(CPassword data);

    public List<CPassword> queryCPasswordList(CPassword condition);

    public CPassword getCPassword(String code);

}
