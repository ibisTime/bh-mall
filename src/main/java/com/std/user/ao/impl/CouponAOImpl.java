package com.std.user.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.user.ao.ICouponAO;
import com.std.user.bo.IAccountBO;
import com.std.user.bo.ICouponBO;
import com.std.user.bo.IUserBO;
import com.std.user.bo.base.Paginable;
import com.std.user.domain.Coupon;
import com.std.user.domain.User;
import com.std.user.enums.EBizType;
import com.std.user.enums.ECouponStatus;
import com.std.user.enums.ECurrency;
import com.std.user.enums.ESysUser;
import com.std.user.exception.BizException;

@Service
public class CouponAOImpl implements ICouponAO {

    @Autowired
    private ICouponBO couponBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public String addCoupon(Coupon data) {
        return couponBO.saveCoupon(data);
    }

    @Override
    @Transactional
    public void scanCoupon(String userId, String code) {
        Coupon coupon = couponBO.getCoupon(code);
        // 判断用户是否属于该公司的
        User user = userBO.getUser(userId);
        if (!coupon.getCompanyCode().equals(user.getCompanyCode())) {
            throw new BizException("xn0000", "该用户不属于该商户，无法扫描");
        }
        if (ECouponStatus.USED.getCode().equals(coupon.getStatus())) {
            throw new BizException("xn0000", "该卡券已使用，不能二次扫描");
        }
        // todo:缺划账接口
        couponBO.refreshCouponStatus(code, ECouponStatus.USED, userId);
        accountBO.doTransferAmountRemote(ESysUser.SYS_USER_YAOCHENG.getCode(),
            userId, ECurrency.YC_CB, coupon.getAmount(), EBizType.YC_SCB, "用户["
                    + user.getMobile() + "]" + EBizType.YC_SCB.getValue(),
            EBizType.YC_SCB.getValue());
    }

    @Override
    public void dropCoupon(String code, String updater) {
        Coupon coupon = couponBO.getCoupon(code);
        if (!ECouponStatus.TO_USE.getCode().equals(coupon.getStatus())) {
            throw new BizException("xn0000", "该卡券状态不是待使用状态，无法作废");
        }
        couponBO.refreshCouponStatus(code, ECouponStatus.DROP, updater);
    }

    @Override
    public Paginable<Coupon> queryCouponPage(int start, int limit,
            Coupon condition) {
        return couponBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Coupon> queryCouponList(Coupon condition) {
        return couponBO.queryCouponList(condition);
    }

    @Override
    public Coupon getCoupon(String code) {
        return couponBO.getCoupon(code);
    }
}
