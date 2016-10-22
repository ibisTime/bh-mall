package com.std.user.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.user.bo.IFieldTimesBO;
import com.std.user.bo.base.PaginableBOImpl;
import com.std.user.dao.IFieldTimesDAO;
import com.std.user.domain.FieldTimes;
import com.std.user.enums.EFieldType;
import com.std.user.exception.BizException;

@Component
public class FieldTimesBOImpl extends PaginableBOImpl<FieldTimes> implements
        IFieldTimesBO {

    @Autowired
    private IFieldTimesDAO fieldTimesDAO;

    @Override
    public String saveFieldTimes(FieldTimes data) {
        String code = null;
        if (data != null) {
            fieldTimesDAO.insert(data);
        }
        return code;
    }

    @Override
    public List<FieldTimes> queryFieldTimesList(FieldTimes condition) {
        return fieldTimesDAO.selectList(condition);
    }

    /** 
     * @see com.std.user.bo.IFieldTimesBO#isFieldTimesExist(java.lang.String, java.lang.String)
     */
    @Override
    public void isFieldTimesExist(EFieldType fieldType, String userId) {
        FieldTimes condition = new FieldTimes();
        condition.setType(fieldType.getCode());
        condition.setUserId(userId);
        List<FieldTimes> list = fieldTimesDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new BizException("xn000000", fieldType.getValue()
                    + "已修改一次，无法再次修改");
        }
    }
}
