package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.AgencyLog;
import com.bh.mall.domain.User;

public interface IAgencyLogBO extends IPaginableBO<AgencyLog> {

    public List<AgencyLog> queryAgencyLogList(AgencyLog condition);

    public AgencyLog getAgencyLog(String code);

    public String saveAgencyLog(String toUserId);

    public String pass(User data, String approver, String remark);

    public String ignore(User data);

    public String cancelImpower(User data);

    public String approveImpower(User data);

    public String approveCanenl(User data);

    public String upgradeLevel(User data, String payPdf);

    public String approveUpgrade(User data);

    public List<AgencyLog> queryAgencyLogPage(int start, int limit,
            AgencyLog condition);

}
