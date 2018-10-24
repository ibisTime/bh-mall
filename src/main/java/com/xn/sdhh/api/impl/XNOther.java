package com.xn.sdhh.api.impl;

import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;

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
