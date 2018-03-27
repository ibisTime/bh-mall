package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IBankcardAO;
import com.bh.mall.bo.IBankcardBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Bankcard;
import com.bh.mall.dto.req.XN627520Req;
import com.bh.mall.dto.req.XN627522Req;
import com.bh.mall.dto.req.XN627523Req;
import com.bh.mall.exception.BizException;

/**
 * @author: asus 
 * @since: 2016年12月22日 下午5:35:09 
 * @history:
 */
@Service
public class BankcardAOImpl implements IBankcardAO {

    @Autowired
    private IBankcardBO bankcardBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String addBankcard(XN627520Req req) {
        // 判断卡号是否重复
        List<Bankcard> list = bankcardBO.queryBankcardList(req.getUserId());
        if (CollectionUtils.isNotEmpty(list)) {
            throw new BizException("xn0000", "您已绑定银行卡,无需绑定多张");
        }

        Bankcard data = new Bankcard();
        data.setBankcardNumber(req.getBankcardNumber());
        data.setBankCode(req.getBankCode());
        data.setBankName(req.getBankName());
        data.setSubbranch(req.getSubbranch());
        data.setBindMobile(req.getBindMobile());
        data.setUserId(req.getUserId());
        data.setRealName(req.getRealName());
        data.setType(req.getType());
        data.setCurrency(req.getCurrency());
        data.setRemark(req.getRemark());
        return bankcardBO.saveBankcard(data);
    }

    @Override
    public void dropBankcard(String code) {
        if (!bankcardBO.isBankcardExist(code)) {
            throw new BizException("xn0000", "银行卡编号不存在");
        }
        bankcardBO.removeBankcard(code);
    }

    @Override
    public void editBankcard(XN627522Req req) {
        Bankcard bankcard = bankcardBO.getBankcard(req.getCode());
        if (!bankcard.getBankcardNumber().equals(req.getBankcardNumber())) { // 有修改就去判断是否唯一
            List<Bankcard> list = bankcardBO.queryBankcardList(bankcard
                .getUserId());
            for (Bankcard card : list) {
                if (req.getBankcardNumber().equals(card.getBankcardNumber())) {
                    throw new BizException("xn0000", "银行卡号已存在");
                }
            }
        }
        Bankcard data = new Bankcard();
        data.setCode(req.getCode());
        // 户名有传就修改，不传不修改
        if (StringUtils.isBlank(req.getRealName())) {
            data.setRealName(bankcard.getRealName());
        } else {
            data.setRealName(req.getRealName());
        }
        data.setBankcardNumber(req.getBankcardNumber());
        data.setBankCode(req.getBankCode());
        data.setBankName(req.getBankName());
        data.setSubbranch(req.getSubbranch());
        data.setBindMobile(req.getBindMobile());
        data.setStatus(req.getStatus());
        data.setRemark(req.getRemark());
        bankcardBO.refreshBankcard(data);
    }

    @Override
    public void editBankcard(XN627523Req req) {
        Bankcard bankcard = bankcardBO.getBankcard(req.getCode());
        userBO.checkTradePwd(bankcard.getUserId(), req.getTradePwd());
        if (!bankcard.getBankcardNumber().equals(req.getBankcardNumber())) { // 有修改就去判断是否唯一
            List<Bankcard> list = bankcardBO.queryBankcardList(bankcard
                .getUserId());
            for (Bankcard card : list) {
                if (req.getBankcardNumber().equals(card.getBankcardNumber())) {
                    throw new BizException("xn0000", "银行卡号已存在");
                }
            }
        }
        Bankcard data = new Bankcard();
        // 户名有传就修改，不传不修改
        if (StringUtils.isBlank(req.getRealName())) {
            data.setRealName(bankcard.getRealName());
        } else {
            data.setRealName(req.getRealName());
        }
        data.setCode(req.getCode());
        data.setBankcardNumber(req.getBankcardNumber());
        data.setBankCode(req.getBankCode());
        data.setBankName(req.getBankName());
        data.setSubbranch(req.getSubbranch());
        data.setBindMobile(req.getBindMobile());
        data.setStatus(req.getStatus());
        data.setRemark(req.getRemark());
        bankcardBO.refreshBankcard(data);
    }

    @Override
    public Paginable<Bankcard> queryBankcardPage(int start, int limit,
            Bankcard condition) {
        return bankcardBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Bankcard> queryBankcardList(Bankcard condition) {
        return bankcardBO.queryBankcardList(condition);
    }

    @Override
    public Bankcard getBankcard(String code) {
        return bankcardBO.getBankcard(code);
    }
}
