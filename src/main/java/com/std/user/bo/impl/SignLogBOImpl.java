package com.std.user.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.user.bo.ISignLogBO;
import com.std.user.bo.base.PaginableBOImpl;
import com.std.user.dao.ISignLogDAO;
import com.std.user.domain.SignLog;

/** 
 * 签到BO
 * @author: zuixian 
 * @since: 2016年9月18日 下午7:21:13 
 * @history:
 */
@Component
public class SignLogBOImpl extends PaginableBOImpl<SignLog> implements
        ISignLogBO {

    @Autowired
    private ISignLogDAO signLogDAO;

    @Override
    public int saveSignLog(SignLog data) {
        return signLogDAO.insert(data);
    }

    @Override
    public List<SignLog> querySignLogList(SignLog condition) {
        return signLogDAO.selectList(condition);
    }
}
