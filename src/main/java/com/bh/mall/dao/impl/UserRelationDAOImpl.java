/**
 * @Title UserRelationDAOImpl.java 
 * @Package com.std.user.dao.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年8月31日 上午11:01:42 
 * @version V1.0   
 */
package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IUserRelationDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.User;
import com.bh.mall.domain.UserRelation;

/** 
 * @author: xieyj 
 * @since: 2016年8月31日 上午11:01:42 
 * @history:
 */
@Repository("userRelationDAOImpl")
public class UserRelationDAOImpl extends AMybatisTemplate implements
        IUserRelationDAO {

    /** 
     * @see com.bh.mall.dao.IUserRelationDAO#updateStatus()
     */
    @Override
    public int updateStatus(UserRelation data) {
        return super
            .update(NAMESPACE.concat("update_userRelationStatus"), data);
    }

    /** 
     * @see com.bh.mall.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(UserRelation data) {
        return super.insert(NAMESPACE.concat("insert_userRelation"), data);
    }

    /** 
     * @see com.bh.mall.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(UserRelation data) {
        return super.delete(NAMESPACE.concat("delete_userRelation"), data);
    }

    /** 
     * @see com.bh.mall.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public UserRelation select(UserRelation condition) {
        return super.select(NAMESPACE.concat("select_userRelation"), condition,
            UserRelation.class);
    }

    /** 
     * @see com.bh.mall.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(UserRelation condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_userRelation_count"), condition);
    }

    /** 
     * @see com.bh.mall.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<UserRelation> selectList(UserRelation condition) {
        return super.selectList(NAMESPACE.concat("select_userRelation"),
            condition, UserRelation.class);
    }

    /** 
     * @see com.bh.mall.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<UserRelation> selectList(UserRelation condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_userRelation"), start,
            count, condition, UserRelation.class);
    }

    /** 
     * @see com.bh.mall.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectUserTotalCount(UserRelation condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_user_count"),
            condition);
    }

    /** 
     * @see com.bh.mall.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<User> selectUserList(UserRelation condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_user"), start, count,
            condition, User.class);
    }

    /** 
     * @see com.bh.mall.dao.IUserRelationDAO#selectUserList(com.bh.mall.domain.UserRelation)
     */
    @Override
    public List<User> selectUserList(UserRelation condition) {
        return super.selectList(NAMESPACE.concat("select_user"), condition,
            User.class);
    }
}
