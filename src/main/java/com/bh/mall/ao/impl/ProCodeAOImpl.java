package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IProCodeAO;
import com.bh.mall.bo.IMiniCodeBO;
import com.bh.mall.bo.IProCodeBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.domain.MiniCode;
import com.bh.mall.domain.ProCode;
import com.bh.mall.domain.SYSConfig;
import com.bh.mall.enums.ECodeStatus;
import com.bh.mall.enums.ESysConfigType;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.exception.BizException;

@Service
public class ProCodeAOImpl implements IProCodeAO {
    @Autowired
    IProCodeBO proCodeBO;

    @Autowired
    IMiniCodeBO miniCodeBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Override
    @Transactional
    public void addProCode(int number) {
        // 获取数据库的防伪溯源码与条形码
        List<ProCode> barList = proCodeBO.queryCodeList();
        List<MiniCode> stList = miniCodeBO.queryCodeList();

        // 将新增的Code存储起来，并进行比较
        List<String> list = new LinkedList<String>();

        // 新增并校验是否重复
        loop: for (int i = 0; i < number; i++) {
            String proCode = OrderNoGenerater.generate();
            // 新增箱码,若重复，重新生成
            if (this.checkCode(proCode, barList, stList)) {
                i--;
                continue;
            }

            for (String string : list) {
                if (proCode.equals(string)) {
                    i--;
                    continue loop;
                }
            }
            // 新增的Code放入List中
            list.add(proCode);
            Date date = new Date();
            ProCode barData = new ProCode();
            barData.setCode(proCode);
            barData.setStatus(ECodeStatus.TO_USER.getCode());
            barData.setCreateDatetime(date);
            proCodeBO.saveProCode(barData);

        }

    }

    @Override
    @Transactional
    public ProCode queryProCode() {
        // 取出一个未使用过的箱码
        ProCode data = proCodeBO.getNoUseProCode();
        SYSConfig sysConfig = sysConfigBO.getConfig(
            ESysConfigType.URL.getCode(), ESystemCode.BH.getCode(),
            ESystemCode.BH.getCode());
        data.setUrl(sysConfig.getCvalue());

        // 获取
        List<MiniCode> list = new ArrayList<MiniCode>();
        list.add(miniCodeBO.getNoUseMiniCode());
        data.setStList(list);
        return data;
    }

    @Override
    @Transactional
    public synchronized List<ProCode> downLoad(int number, int quantity) {
        // 获取盒码
        ProCode condition = new ProCode();
        condition.setStatus(ECodeStatus.TO_USER.getCode());
        Paginable<ProCode> page = proCodeBO.getPaginable(0, quantity,
            condition);
        if (CollectionUtils.isEmpty(page.getList())) {
            throw new BizException("xn00000", "箱码已经没有啦");
        }
        // 获取二维码的URL
        SYSConfig sysConfig = sysConfigBO.getConfig(
            ESysConfigType.URL.getCode(), ESystemCode.BH.getCode(),
            ESystemCode.BH.getCode());

        // 获取箱码下得盒码
        for (ProCode proCode : page.getList()) {
            proCode.setUrl(sysConfig.getCvalue());
            MiniCode traceCondition = new MiniCode();
            traceCondition.setStatus(ECodeStatus.TO_USER.getCode());
            Paginable<MiniCode> tracePage = miniCodeBO
                .getPaginable(quantity - 1, number, traceCondition);

            // 更新箱状态
            if (!ECodeStatus.TO_USER.getCode().equals(proCode.getStatus())) {
                throw new BizException("xn00000", "箱码已被使用");
            }
            proCodeBO.refreshProCode(proCode);

            // 是否还有可用盒码
            if (CollectionUtils.isEmpty(tracePage.getList())) {
                throw new BizException("xn00000", "盒码已经没有啦");
            }
            // 建立关联关系并更新状态
            for (MiniCode trace : tracePage.getList()) {
                miniCodeBO.refreshMiniCode(trace, proCode.getCode());
            }
            proCode.setStList(tracePage.getList());
            quantity = quantity + 1;
        }
        return page.getList();

    }

    @Override
    public Paginable<ProCode> queryProCodePage(int start, int limit,
            ProCode condition) {
        return proCodeBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ProCode> queryProCodeList(ProCode condition) {
        return proCodeBO.queryProCodeList(condition);
    }

    @Override
    public ProCode getProCode(String code) {
        return proCodeBO.getProCode(code);
    }

    @Override
    public boolean checkCode(String code, List<ProCode> list,
            List<MiniCode> stList) {
        // 是否与箱码重复
        for (ProCode proCode : list) {
            if (proCode.getCode().equals(code)) {
                return true;
            }
        }

        // 校验是否重复
        for (MiniCode miniCode : stList) {
            if (miniCode.getminiCode().equals(code)) {
                return true;
            }
            if (miniCode.getTraceCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

}
