package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.CUser;
import com.bh.mall.dto.res.XN627304Res;

/**
 * C端用户
 * @author: LENOVO 
 * @since: 2018年7月31日 下午9:17:11 
 * @history:
 */
public interface ICUserAO {

    String DEFAULT_ORDER_COLUMN = "user_id";

    // 微信登录/注册
    public XN627304Res doLoginWeChatByCustomer(String code, String nickname,
            String avatarUrl, String kind);

    // 修改照片
    public void doModifyPhoto(String userId, String photo);

    // 分页查询
    public Paginable<CUser> queryCuserPage(int start, int limit,
            CUser condition);

    // 列表查询
    public List<CUser> queryCuserList(CUser condition);

    // 详细查询
    public CUser getCuser(String code);

}
