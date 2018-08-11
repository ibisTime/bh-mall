package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IJourAO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IInOrderBO;
import com.bh.mall.bo.IJourBO;
import com.bh.mall.bo.IOutOrderBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.InOrder;
import com.bh.mall.domain.Jour;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.ESysUser;

@Service
public class JourAOImpl implements IJourAO {

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IAgentBO agentBO;

    @Autowired
    private IOutOrderBO outOrderBO;

    @Autowired
    private IInOrderBO inOrderBO;

    @Override
    public Paginable<Jour> queryJourPage(int start, int limit, Jour condition) {
        Paginable<Jour> page = jourBO.getPaginable(start, limit, condition);
        for (Jour data : page.getList()) {
            if (!ESysUser.SYS_USER_BH.getCode().equals(data.getUserId())) {
                Agent agent = agentBO.getAgent(data.getUserId());
                data.setMobile(agent.getMobile());
            }
            // 关联订单转义
            if (EBizType.AJ_CHJL_OUT.getCode().equals(data.getBizType())
                    || EBizType.AJ_TJJL_OUT.getCode()
                        .equals(data.getBizType())) {
                OutOrder outOrder = outOrderBO.getOutOrder(data.getRefNo());
                data.setOutOrder(outOrder);
            } else if (EBizType.AJ_JSJL.getCode().equals(data.getBizType())) {
                Agent agent = agentBO.getAgent(data.getUserId());
                data.setAgent(agent);

            } else if (EBizType.AJ_CHJL_IN.getCode().equals(data.getBizType())
                    || EBizType.AJ_TJJL_IN.getCode()
                        .equals(data.getBizType())) {
                InOrder inOrder = inOrderBO.getInOrder(data.getRefNo());
                data.setInOrder(inOrder);
            }
        }

        return jourBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Jour> queryJourList(Jour condition) {
        List<Jour> list = jourBO.queryJourList(condition);
        for (Jour data : list) {
            if (!ESysUser.SYS_USER_BH.getCode().equals(data.getUserId())) {
                Agent agent = agentBO.getAgent(data.getUserId());
                data.setMobile(agent.getMobile());
            }
            // 关联订单转义
            if (EBizType.AJ_CHJL_OUT.getCode().equals(data.getBizType())
                    || EBizType.AJ_TJJL_OUT.getCode()
                        .equals(data.getBizType())) {
                OutOrder outOrder = outOrderBO.getOutOrder(data.getRefNo());
                data.setOutOrder(outOrder);
            } else if (EBizType.AJ_JSJL.getCode().equals(data.getBizType())) {
                Agent agent = agentBO.getAgent(data.getUserId());
                data.setAgent(agent);

            } else if (EBizType.AJ_CHJL_IN.getCode().equals(data.getBizType())
                    || EBizType.AJ_TJJL_IN.getCode()
                        .equals(data.getBizType())) {
                InOrder inOrder = inOrderBO.getInOrder(data.getRefNo());
                data.setInOrder(inOrder);
            }
        }
        return list;
    }

    @Override
    public Jour getJour(String code) {
        Jour data = jourBO.getJour(code);
        if (!ESysUser.SYS_USER_BH.getCode().equals(data.getUserId())) {
            Agent agent = agentBO.getAgent(data.getUserId());
            data.setMobile(agent.getMobile());
        }
        // 关联订单转义
        if (EBizType.AJ_CHJL_OUT.getCode().equals(data.getBizType())
                || EBizType.AJ_TJJL_OUT.getCode().equals(data.getBizType())) {
            OutOrder outOrder = outOrderBO.getOutOrder(data.getRefNo());
            data.setOutOrder(outOrder);
        } else if (EBizType.AJ_JSJL.getCode().equals(data.getBizType())) {
            Agent agent = agentBO.getAgent(data.getUserId());
            data.setAgent(agent);

        } else if (EBizType.AJ_CHJL_IN.getCode().equals(data.getBizType())
                || EBizType.AJ_TJJL_IN.getCode().equals(data.getBizType())) {
            InOrder inOrder = inOrderBO.getInOrder(data.getRefNo());
            data.setInOrder(inOrder);
        }
        return data;
    }

    @Override
    public Long getTotalAmount(String bizType, String channelType,
            String accountNumber, String dateStart, String dateEnd) {
        return jourBO.getTotalAmount(bizType, channelType, accountNumber,
            dateStart, dateEnd);
    }

    @Override
    public Paginable<Jour> queryDetailPage(int start, int limit,
            Jour condition) {
        if (EBoolean.NO.getCode().equals(condition.getBizType())) {
            condition.setBizType(EBizType.AJ_TJJL.getCode());
        } else if (EBoolean.YES.getCode().equals(condition.getBizType())) {
            condition.setBizType(EBizType.AJ_CHJL.getCode());
        } else {
            condition.setBizType(EBizType.AJ_JSJL.getCode());
        }

        return jourBO.getPaginable(start, limit, condition);
    }

}
