/**
 * @Title AJourAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-5-7 上午11:32:16 
 * @version V1.0   
 */
package com.std.user.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.user.ao.IAJourAO;
import com.std.user.bo.IAJourBO;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.AccountJour;

/** 
 * @author: miyb 
 * @since: 2015-5-7 上午11:32:16 
 * @history:
 */
@Service
public class AJourAOImpl implements IAJourAO {
    @Autowired
    IAJourBO aJourBO;

    @Override
    public Paginable<AccountJour> queryAccountJourPage(int start, int limit,
            AccountJour condition) {
        return aJourBO.getPaginable(start, limit, condition);
    }
}
