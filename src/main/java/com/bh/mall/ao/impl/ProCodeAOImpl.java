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

    /**
     * 1、取出所有的盒码与箱码，用于后续的比较
     * 2、每新生成一个箱码与之前新生成进行比较，如不重复，放入新生成的List中用于下次对比
     * 3、每新生成一个盒码与之前新生成进行比较，如不重复，放入新生成的List中用于下次对比
     * 4、开始生成盒码与箱码比较，相同时跳出当前循环，重新生成
     * 5、新生成的盒码/箱码与数据库中的进行对比，重复时重新生成
     * @see com.bh.mall.ao.IProCodeAO#addProCode(int, int)
     */
    @Override
    @Transactional
    public void addProCode(int proNumber, int miniNumber) {
        long start = System.currentTimeMillis();

        // 获取数据库的防伪溯源码与条形码
        List<ProCode> proList = proCodeBO.queryCodeList();
        List<MiniCode> miniList = miniCodeBO.queryCodeList();

        // 将新增的Code存储起来，并进行比较
        List<String> newProList = new LinkedList<String>();
        List<String> newMiniList = new LinkedList<String>();
        List<String> newTraceList = new LinkedList<String>();

        Date date = null;
        loop: for (int pro = 0; pro < proNumber; pro++) {
            String proCode = OrderNoGenerater.generate();
            // 新生成的箱码是否重复
            if (newProList.contains(proCode) || newMiniList.contains(proCode)
                    || newTraceList.contains(proCode)) {
                pro++;
                break;
            } else {
                // 把新生成的箱码放入newProList中
                newProList.add(proCode);
            }

            for (int mini = 0; mini < miniNumber; mini++) {
                date = new Date();
                String miniCode = OrderNoGenerater.generateTrace();
                String traceCode = OrderNoGenerater.generateTrace();

                // 最新的防伪码与之前生成的盒码/箱码重复，跳出当前循环，重新生成盒码
                if (newProList.contains(miniCode)
                        || newMiniList.contains(miniCode)
                        || newTraceList.contains(miniCode)) {
                    mini--;
                    continue;
                }

                // 最新的溯源码与之前生成的盒码/箱码重复，跳出当前循环，重新生成盒码
                if (newProList.contains(traceCode)
                        || newMiniList.contains(traceCode)
                        || newTraceList.contains(traceCode)) {
                    mini--;
                    continue;
                }

                // 新城生的盒码之间或与箱码重复，跳出当前循环，重新生成盒码
                if (miniCode.equals(traceCode) || miniCode.equals(proCode)
                        || traceCode.equals(proCode)) {
                    mini--;
                    continue;
                } else {
                    newMiniList.add(miniCode);
                    newTraceList.add(traceCode);
                }

                // 校验新增的盒码与箱码是否与原有的箱码重复
                for (ProCode data : proList) {
                    if (data.getCode().equals(proCode)) {
                        pro++;
                        continue loop;
                    }
                    if (data.getCode().equals(miniCode)
                            || data.getCode().equals(traceCode)) {
                        mini--;
                        break;
                    }
                }

                // 校验新增的盒码与箱码是否与原有的盒码重复
                for (MiniCode data : miniList) {
                    if (data.getMiniCode().equals(proCode)
                            || data.getTraceCode().equals(proCode)) {
                        pro++;
                        continue loop;
                    }

                    if (data.getMiniCode().equals(miniCode)
                            || data.getTraceCode().equals(miniCode)
                            || data.getTraceCode().equals(traceCode)
                            || data.getTraceCode().equals(miniCode)) {
                        mini--;
                        break;
                    }
                }

                // 绑定盒码与箱码关系
                miniCodeBO.saveMiniCode(miniCode, traceCode, proCode, date);
            }

            // 新增箱码关系
            proCodeBO.saveProCode(proCode, date);
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
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
    public synchronized List<ProCode> downLoad(int number) {
        // 获取盒码
        ProCode condition = new ProCode();
        condition.setStatus(ECodeStatus.TO_USER.getCode());
        Paginable<ProCode> page = proCodeBO.getPaginable(0, number, condition);
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
            MiniCode miniCondition = new MiniCode();
            miniCondition.setRefCode(proCode.getCode());
            miniCondition.setStatus(ECodeStatus.TO_USER.getCode());
            List<MiniCode> miniList = miniCodeBO
                .queryMiniCodeList(miniCondition);

            // 更新箱状态
            if (!ECodeStatus.TO_USER.getCode().equals(proCode.getStatus())) {
                throw new BizException("xn00000", "箱码已被使用");
            }
            proCode.setStatus(ECodeStatus.USE_NO.getCode());
            proCodeBO.refreshProCode(proCode);

            // 是否还有可用盒码
            if (CollectionUtils.isEmpty(miniList)) {
                throw new BizException("xn00000", "盒码已经没有啦");
            }
            // 建立关联关系并更新状态
            for (MiniCode trace : miniList) {
                miniCodeBO.refreshMiniCode(trace);
            }
            proCode.setStList(miniList);
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
        ProCode data = proCodeBO.getProCode(code);
        List<MiniCode> miniList = miniCodeBO
            .getMiniCodeByProCode(data.getCode());
        data.setStList(miniList);
        return data;
    }

}
