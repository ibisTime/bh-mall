package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.SYSRole;
import com.bh.mall.dto.req.XN627040Req;

/**
 * @author: Gejin 
 * @since: 2016年4月16日 下午9:18:16 
 * @history:
 */
public interface ISYSRoleAO {
    String DEFAULT_ORDER_COLUMN = "code";

    // 新增角色
    public String addSYSRole(XN627040Req req);

    // 删除角色
    public boolean dropSYSRole(String roleCode);

    // 修改角色
    public boolean editSYSRole(SYSRole data);

    // 列表查询
    public List<SYSRole> querySYSRoleList(SYSRole condition);

    // 分页查询
    public Paginable<SYSRole> querySYSRolePage(int start, int limit,
            SYSRole condition);

    // 详细查询
    public SYSRole getSYSRole(String code);

}
