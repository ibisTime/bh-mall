package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IMiniCodeAO;
import com.bh.mall.ao.IOrderAO;
import com.bh.mall.ao.IProCodeAO;
import com.bh.mall.bo.IMiniCodeBO;
import com.bh.mall.bo.IProCodeBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.domain.MiniCode;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.ProCode;
import com.bh.mall.enums.ECodeStatus;
import com.bh.mall.exception.BizException;

@Service
public class MiniCodeAOImpl implements IMiniCodeAO {

    @Autowired
    IMiniCodeBO miniCodeBO;

    @Autowired
    IProCodeBO proCodeBO;

    @Autowired
    IProCodeAO proCodeAO;

    @Autowired
    IOrderAO orderAO;

    @Override
    public Paginable<MiniCode> queryMiniCodePage(int start, int limit,
            MiniCode condition) {
        return miniCodeBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<MiniCode> queryMiniCodeList(MiniCode condition) {
        return miniCodeBO.queryMiniCodeList(condition);
    }

    @Override
    public MiniCode getMiniCode(String code) {
        return miniCodeBO.getMiniCode(code);
    }

    @Override
    public void addMiniCode(int number) {
        // 获取数据库的防伪溯源码与条形码
        List<ProCode> barList = proCodeBO.queryCodeList();
        List<MiniCode> stList = miniCodeBO.queryCodeList();

        // 将新增的Code存储起来，并进行比较
        List<String> list = new ArrayList<String>();

        // 新增并校验是否重复
        loop: for (int i = 0; i < number; i++) {
            String traceCode = OrderNoGenerater.generateTrace();
            String miniCode = OrderNoGenerater.generateTrace();
            // 新增箱码
            // 若重复，重新生成
            if (proCodeAO.checkCode(traceCode, barList, stList)) {
                i--;
                continue;
            }
            // 若重复，重新生成
            if (proCodeAO.checkCode(miniCode, barList, stList)) {
                i--;
                continue;
            }

            for (String string : list) {
                if (traceCode.equals(string)) {
                    i--;
                    continue loop;
                }
                if (miniCode.equals(string)) {
                    i--;
                    continue loop;
                }
            }

            // 新增的Code放入List中
            list.add(miniCode);

            Date date = new Date();
            MiniCode data = new MiniCode();
            data.setminiCode(miniCode);
            data.setTraceCode(traceCode);
            data.setStatus(ECodeStatus.TO_USER.getCode());
            data.setCreateDatetime(date);
            miniCodeBO.saveMiniCode(data);

        }
    }

    @Override
    public int getSecurity(String miniCode) {
        MiniCode data = miniCodeBO.getSecurity(miniCode);
        if (!ECodeStatus.USE_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该防伪码还未启用");
        }

        // 查询次数是否为空，防止之前未默认为零的报错
        if (null == data.getNumber()) {
            data.setNumber(0);
        }
        return miniCodeBO.refreshSecurity(data);
    }

    @Override
    public MiniCode getTrace(String traceCode) {
        MiniCode data = miniCodeBO.getTrace(traceCode);
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
