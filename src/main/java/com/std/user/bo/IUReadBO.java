package com.std.user.bo;

import java.util.List;

import com.std.user.bo.base.IPaginableBO;
import com.std.user.domain.URead;

public interface IUReadBO extends IPaginableBO<URead> {

    public boolean isUReadExist(Integer id);

    public String saveURead(URead data);

    public int refreshURead(URead data);

    public List<URead> queryUReadList(URead condition);

    public URead getURead(Integer id);

}
