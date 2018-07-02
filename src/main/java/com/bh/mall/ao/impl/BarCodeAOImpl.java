package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.bh.mall.enums.EBoolean;
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
        List<String> list = new ArrayList<String>();

        // 新增并校验是否重复
        loop: for (int i = 0; i < number; i++) {
            String barCode = OrderNoGenerater.generate();
            // 新增箱码
            // 若重复，重新生成
            if (this.checkCode(barCode, barList, stList)) {
                System.out.println("dicyi:" + barCode);
                i--;
                continue;
            }

            for (String string : list) {
                if (barCode.equals(string)) {
                    System.out.println("2222222:" + barCode);
                    i--;
                    continue loop;
                }
            }
            System.out.println(barCode);
            // 新增的Code放入List中
            list.add(barCode);

            Date date = new Date();
            BarCode barData = new BarCode();
            barData.setCode(barCode);
            barData.setStatus(ECodeStatus.USE_NO.getCode());
            barData.setCreateDatetime(date);
            barCodeBO.saveBarCode(barData);

        }

    }

    @Override
    @Transactional
    public BarCode queryBarCode(int number) {
        // 取出一个未使用过的箱码
        BarCode data = barCodeBO.getNoUseBarCode();
        SYSConfig sysConfig = sysConfigBO.getConfig(
            ESysConfigType.URL.getCode(), ESystemCode.BH.getCode(),
            ESystemCode.BH.getCode());
        data.setUrl(sysConfig.getCvalue());

        // 获取
        SecurityTrace condition = new SecurityTrace();
        condition.setStatus(ECodeStatus.USE_NO.getCode());
        Paginable<SecurityTrace> page = securityTraceBO.getPaginable(0, number,
            condition);

        for (SecurityTrace securityTrace : page.getList()) {
            securityTrace.setRefCode(data.getCode());
            securityTraceBO.refreshSecurityTrace(securityTrace);
        }

        data.setStList(page.getList());
        return data;
    }

    @Override
    @Transactional
    public void downLoad(String code) {
        BarCode data = barCodeBO.getBarCode(code);
        if (EBoolean.YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该箱码已被使用，请重新下载！");
        }

        List<SecurityTrace> list = securityTraceBO
            .getSecurityTraceByBarCode(code);
        // 盒码中是否又被使用过的,没有则修改状态
        for (SecurityTrace securityTrace : list) {
            if (EBoolean.YES.getCode().equals(securityTrace.getStatus())) {
                throw new BizException("xn00000", "本批盒码有已被使用过得盒码，请重新下载！");
            }
            securityTraceBO.refreshStatus(securityTrace);
        }

        // 修改箱码状态
        barCodeBO.refreshBarCode(data);

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
