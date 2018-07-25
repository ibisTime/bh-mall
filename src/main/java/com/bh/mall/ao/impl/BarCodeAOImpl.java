package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IBarCodeAO;
import com.bh.mall.bo.IBarCodeBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.ISecurityTraceBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.domain.BarCode;
import com.bh.mall.domain.SYSConfig;
import com.bh.mall.domain.SecurityTrace;
import com.bh.mall.enums.ECodeStatus;
import com.bh.mall.enums.ESysConfigType;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.exception.BizException;

@Service
public class BarCodeAOImpl implements IBarCodeAO {

    @Autowired
    IBarCodeBO barCodeBO;

    @Autowired
    ISecurityTraceBO securityTraceBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Override
    @Transactional
    public void addBarCode(int number) {
        // 获取数据库的防伪溯源码与条形码
        List<BarCode> barList = barCodeBO.queryCodeList();
        List<SecurityTrace> stList = securityTraceBO.queryCodeList();

        // 将新增的Code存储起来，并进行比较
        List<String> list = new LinkedList<String>();

        // 新增并校验是否重复
        loop: for (int i = 0; i < number; i++) {
            String barCode = OrderNoGenerater.generate();
            // 新增箱码,若重复，重新生成
            if (this.checkCode(barCode, barList, stList)) {
                i--;
                continue;
            }

            for (String string : list) {
                if (barCode.equals(string)) {
                    i--;
                    continue loop;
                }
            }
            // 新增的Code放入List中
            list.add(barCode);
            Date date = new Date();
            BarCode barData = new BarCode();
            barData.setCode(barCode);
            barData.setStatus(ECodeStatus.TO_USER.getCode());
            barData.setCreateDatetime(date);
            barCodeBO.saveBarCode(barData);

        }

    }

    @Override
    @Transactional
    public BarCode queryBarCode() {
        // 取出一个未使用过的箱码
        BarCode data = barCodeBO.getNoUseBarCode();
        SYSConfig sysConfig = sysConfigBO.getConfig(
            ESysConfigType.URL.getCode(), ESystemCode.BH.getCode(),
            ESystemCode.BH.getCode());
        data.setUrl(sysConfig.getCvalue());

        // 获取
        List<SecurityTrace> list = new ArrayList<SecurityTrace>();
        list.add(securityTraceBO.getNoUseSecurityTrace());
        data.setStList(list);
        return data;
    }

    @Override
    @Transactional
    public synchronized List<BarCode> downLoad(int number, int quantity) {
        // 获取盒码
        BarCode condition = new BarCode();
        condition.setStatus(ECodeStatus.TO_USER.getCode());
        Paginable<BarCode> page = barCodeBO.getPaginable(0, quantity,
            condition);
        if (CollectionUtils.isEmpty(page.getList())) {
            throw new BizException("xn00000", "箱码已经没有啦");
        }
        // 获取二维码的URL
        SYSConfig sysConfig = sysConfigBO.getConfig(
            ESysConfigType.URL.getCode(), ESystemCode.BH.getCode(),
            ESystemCode.BH.getCode());

        // 获取箱码下得盒码
        for (BarCode barCode : page.getList()) {
            barCode.setUrl(sysConfig.getCvalue());
            SecurityTrace traceCondition = new SecurityTrace();
            traceCondition.setStatus(ECodeStatus.TO_USER.getCode());
            Paginable<SecurityTrace> tracePage = securityTraceBO
                .getPaginable(quantity - 1, number, traceCondition);
            barCodeBO.refreshBarCode(barCode);

            // 是否还有可用盒码
            if (CollectionUtils.isEmpty(tracePage.getList())) {
                throw new BizException("xn00000", "盒码已经没有啦");
            }
            // 建立关联关系并更新状态
            for (SecurityTrace trace : tracePage.getList()) {
                securityTraceBO.refreshSecurityTrace(trace, barCode.getCode());
            }
            barCode.setStList(tracePage.getList());
            quantity = quantity + 1;
        }
        return page.getList();

    }

    @Override
    public Paginable<BarCode> queryBarCodePage(int start, int limit,
            BarCode condition) {
        return barCodeBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<BarCode> queryBarCodeList(BarCode condition) {
        return barCodeBO.queryBarCodeList(condition);
    }

    @Override
    public BarCode getBarCode(String code) {
        return barCodeBO.getBarCode(code);
    }

    @Override
    public boolean checkCode(String code, List<BarCode> list,
            List<SecurityTrace> stList) {
        // 是否与箱码重复
        for (BarCode barCode : list) {
            if (barCode.getCode().equals(code)) {
                return true;
            }
        }

        // 校验是否重复
        for (SecurityTrace securityTrace : stList) {
            if (securityTrace.getSecurityCode().equals(code)) {
                return true;
            }
            if (securityTrace.getTraceCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

}
