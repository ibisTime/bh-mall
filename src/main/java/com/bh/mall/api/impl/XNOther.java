package com.bh.mall.api.impl;

import com.bh.mall.api.AProcessor;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;

public class XNOther extends AProcessor {

    @Override
    public Object doBusiness() throws BizException {
        throw new BizException("702xxx", "无效API功能号");
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        throw new ParaException("702xxx", "无效API功能号");

    }

}
