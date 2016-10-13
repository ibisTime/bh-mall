package com.std.user.ao;

import java.util.List;

import com.std.user.bo.base.Paginable;
import com.std.user.domain.URead;

public interface IUReadAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addURead(URead data);

    public int editURead(URead data);

    public Paginable<URead> queryUReadPage(int start, int limit, URead condition);

    public List<URead> queryUReadList(URead condition);

    public URead getURead(Integer id);

}
