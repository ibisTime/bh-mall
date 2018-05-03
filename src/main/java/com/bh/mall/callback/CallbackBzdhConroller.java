package com.bh.mall.callback;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bh.mall.ao.IInnerOrderAO;
import com.bh.mall.ao.IOrderAO;
import com.bh.mall.enums.EBizType;

/**
 * 币种兑换回调控制层
 * @author: xieyj 
 * @since: 2017年4月20日 下午5:00:52 
 * @history:
 */
@Controller
public class CallbackBzdhConroller {

    private static Logger logger = Logger
        .getLogger(CallbackBzdhConroller.class);

    @Autowired
    IInnerOrderAO innerOrderAO;

    @Autowired
    IOrderAO orderAO;

    // 自身支付回调
    @RequestMapping("/bzdh/callback")
    public synchronized void doCallbackPay(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        boolean isSuccess = Boolean.valueOf(request.getParameter("isSuccess"));
        logger.info("isSuccess：" + isSuccess);
        String payGroup = request.getParameter("payGroup");
        logger.info("payGroup：" + payGroup);
        String payCode = request.getParameter("payCode");
        logger.info("payCode：" + payCode);
        Long amount = Long.valueOf(request.getParameter("transAmount"));
        logger.info("amount：" + amount);
        String bizType = request.getParameter("bizType");
        logger.info("bizType：" + bizType);
        // 支付成功，商户处理后同步返回给微信参数
        if (!isSuccess) {
            logger.info("支付失败");
        } else {
            logger.info("===============付款成功==============");
            // ------------------------------
            // 处理业务开始
            // ------------------------------
            try {
                if (EBizType.AJ_GMNP.getCode().equals(bizType)) {
                    System.out.println("**** 进入内购产品售卖，服务器回调 start****");
                    innerOrderAO.paySuccess(payGroup, payCode, amount);
                    System.out.println("**** 进入内购产品购买售卖，服务器回调 end****");
                } else if (EBizType.AJ_GMCP.getCode().equals(bizType)) {
                    System.out.println("**** 进入产品售卖，服务器回调 start****");
                    orderAO.paySuccess(payGroup, payCode, amount);
                    System.out.println("**** 进入产品购买售卖，服务器回调 end****");
                }
            } catch (Exception e) {
                logger.info("支付回调异常,原因：" + e.getMessage());
            }
        }
    }
}
