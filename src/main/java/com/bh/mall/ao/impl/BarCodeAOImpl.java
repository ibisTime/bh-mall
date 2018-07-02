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
import com.bh.mall.enums.ECodeStatus;
import com.bh.mall.enums.ESysConfigType;
import com.bh.mall.enums.ESystemCode;

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
    public synchronized List<BarCode> downLoad(int number, int quantity) {
        BarCode condition = new BarCode();
        condition.setStatus(ECodeStatus.USE_NO.getCode());
        Paginable<BarCode> page = barCodeBO.getPaginable(0, number, condition);

        SYSConfig sysConfig = sysConfigBO.getConfig(
            ESysConfigType.URL.getCode(), ESystemCode.BH.getCode(),
            ESystemCode.BH.getCode());

        SecurityTrace traceCondition = new SecurityTrace();
        condition.setStatus(ECodeStatus.USE_NO.getCode());
        for (BarCode barCode : page.getList()) {

            barCodeBO.refreshBarCode(barCode);
            barCode.setUrl(sysConfig.getCvalue());
            Paginable<SecurityTrace> tracePage = securityTraceBO
                .getPaginable(number, quantity, traceCondition);
            for (SecurityTrace trace : tracePage.getList()) {
                trace.setRefCode(barCode.getCode());
                securityTraceBO.refreshStatus(trace);
            }
            barCode.setStList(tracePage.getList());
            number = number + 1;
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

    // @Override
    // public String downLoadPic(String picUrl, String code) {
    // if (picUrl == null)
    // return null;
    // @SuppressWarnings("restriction")
    // BASE64Decoder decoder = new BASE64Decoder();
    // try {
    // @SuppressWarnings("restriction")
    // byte[] b = decoder.decodeBuffer(picUrl);
    // System.out.println(new String(b));
    // return new String(b);
    // } catch (Exception e) {
    // return null;
    // }
    // }

}
