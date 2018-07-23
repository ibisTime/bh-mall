package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IBarCodeAO;
import com.bh.mall.ao.IOrderAO;
import com.bh.mall.ao.ISecurityTraceAO;
import com.bh.mall.bo.IBarCodeBO;
import com.bh.mall.bo.ISecurityTraceBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.domain.BarCode;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.SecurityTrace;
import com.bh.mall.enums.ECodeStatus;
import com.bh.mall.exception.BizException;

@Service
public class SecurityTraceAOImpl implements ISecurityTraceAO {

    @Autowired
    ISecurityTraceBO securityTraceBO;

    @Autowired
    IBarCodeBO barCodeBO;

    @Autowired
    IBarCodeAO barCodeAO;

    @Autowired
    IOrderAO orderAO;

    @Override
    public Paginable<SecurityTrace> querySecurityTracePage(int start, int limit,
            SecurityTrace condition) {
        return securityTraceBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SecurityTrace> querySecurityTraceList(SecurityTrace condition) {
        return securityTraceBO.querySecurityTraceList(condition);
    }

    @Override
    public SecurityTrace getSecurityTrace(String code) {
        return securityTraceBO.getSecurityTrace(code);
    }

    @Override
    public void addSecurityTrace(int number) {
        // 获取数据库的防伪溯源码与条形码
        List<BarCode> barList = barCodeBO.queryCodeList();
        List<SecurityTrace> stList = securityTraceBO.queryCodeList();

        // 将新增的Code存储起来，并进行比较
        List<String> list = new ArrayList<String>();

        // 新增并校验是否重复
        loop: for (int i = 0; i < number; i++) {
            String traceCode = OrderNoGenerater.generateTrace();
            String securityCode = OrderNoGenerater.generateTrace();
            // 新增箱码
            // 若重复，重新生成
            if (barCodeAO.checkCode(traceCode, barList, stList)) {
                i--;
                continue;
            }
            // 若重复，重新生成
            if (barCodeAO.checkCode(securityCode, barList, stList)) {
                i--;
                continue;
            }

            for (String string : list) {
                if (traceCode.equals(string)) {
                    i--;
                    continue loop;
                }
                if (securityCode.equals(string)) {
                    i--;
                    continue loop;
                }
            }

            // 新增的Code放入List中
            list.add(securityCode);

            Date date = new Date();
            SecurityTrace data = new SecurityTrace();
            data.setSecurityCode(securityCode);
            data.setTraceCode(traceCode);
            data.setStatus(ECodeStatus.TO_USER.getCode());
            data.setCreateDatetime(date);
            securityTraceBO.saveSecurityTrace(data);

        }
    }

    @Override
    public int getSecurity(String securityCode) {
        SecurityTrace data = securityTraceBO.getSecurity(securityCode);
        if (!ECodeStatus.USE_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该防伪码还未启用");
        }

        // 查询次数是否为空，防止之前未默认为零的报错
        if (null == data.getNumber()) {
            data.setNumber(0);
        }
        return securityTraceBO.refreshSecurity(data);
    }

    @Override
    public SecurityTrace getTrace(String traceCode) {
        SecurityTrace data = securityTraceBO.getTrace(traceCode);
        if (!ECodeStatus.USE_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该溯源码还未启用");
        }
        if (StringUtils.isBlank(data.getOrderCode())) {
            throw new BizException("xn00000", "该溯源码还未绑定任何订单");
        }

        Order order = orderAO.getOrder(data.getOrderCode());
        data.setOrderData(order);
        return data;
    }
}
