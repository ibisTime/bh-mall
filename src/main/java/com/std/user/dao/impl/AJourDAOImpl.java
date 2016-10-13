/**
 * @Title AJourDAOImpl.java 
 * @Package com.ibis.account.dao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-2-14 下午2:18:55 
 * @version V1.0   
 */
package com.std.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.user.dao.IAJourDAO;
import com.std.user.dao.base.support.AMybatisTemplate;
import com.std.user.domain.AccountJour;

/** 
 * @author: miyb 
 * @since: 2015-2-14 下午2:18:55 
 * @history:
 */
@Repository("aJourDAOImpl")
public class AJourDAOImpl extends AMybatisTemplate implements IAJourDAO {

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(AccountJour data) {
        return super.insert(NAMESPACE.concat("insert_accountJour"), data);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(AccountJour data) {
        return 0;
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public AccountJour select(AccountJour condition) {
        return super.select(NAMESPACE.concat("select_accountJour"), condition,
            AccountJour.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(AccountJour condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_accountJour_count"), condition);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<AccountJour> selectList(AccountJour condition) {
        return super.selectList(NAMESPACE.concat("select_accountJour"),
            condition, AccountJour.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<AccountJour> selectList(AccountJour condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_accountJour"), start,
            count, condition, AccountJour.class);
    }

    /** 
     * @see com.xnjr.account.dao.ibis.account.dao.IAJourDAO#updateCheckInfo(com.ibis.account.domain.AccountJour)
     */
    @Override
    public int doCheckAccount(AccountJour data) {
        return super.update(NAMESPACE.concat("update_check_account"), data);
    }

}
