package com.std.user.ao;

import java.util.List;

import com.std.user.bo.base.Paginable;
import com.std.user.domain.CBanner;

public interface ICBannerAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCBanner(CBanner data);

    public int dropCBanner(String code);

    public int editCBanner(CBanner data);

    public Paginable<CBanner> queryCBannerPage(int start, int limit,
            CBanner condition);

    public List<CBanner> queryCBannerList(CBanner condition);

    public CBanner getCBanner(String code);

}
