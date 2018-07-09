package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.AgencyLog;
import com.bh.mall.domain.User;

public interface IAgencyLogBO extends IPaginableBO<AgencyLog> {

    public List<AgencyLog> queryAgencyLogList(AgencyLog condition);

    public AgencyLog getAgencyLog(String code);

    public String saveAgencyLog(User data, String toUser);

    public String acceptIntention(AgencyLog log, String status);

    public String ignore(User data);

    public String cancelImpower(User data);

    public String approveImpower(AgencyLog log, User user);

    public String approveCanenl(User data, String status);

    public String upgradeLevel(User data, String payPdf);

    public String approveUpgrade(User data, String status);

    public List<AgencyLog> queryAgencyLogPage(int start, int limit,
            AgencyLog condition);

    public String toApply(User dbUser, String payPdf, String status);

    public String refreshHighUser(User data);

}
