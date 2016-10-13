package com.std.user.bo;

import java.util.List;

import com.std.user.bo.base.IPaginableBO;
import com.std.user.domain.CBanner;

public interface ICBannerBO extends IPaginableBO<CBanner> {

    public boolean isCBannerExist(String code);

    public String saveCBanner(CBanner data);

    public int removeCBanner(String code);

    public int refreshCBanner(CBanner data);

    public List<CBanner> queryCBannerList(CBanner condition);

    public CBanner getCBanner(String code);

}
