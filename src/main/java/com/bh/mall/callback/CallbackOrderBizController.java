package com.bh.mall.callback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bh.mall.ao.IOutOrderAO;

@Controller
public class CallbackOrderBizController {
    private static Logger logger = Logger
        .getLogger(CallbackOrderBizController.class);

    @Autowired
    IOutOrderAO outOrderAO;

    // 自身支付回调
    @RequestMapping("/order/callback")
    public synchronized void doCallbackPay(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        boolean isSuccess = Boolean.valueOf(request.getParameter("isSuccess"));
        String payGroup = request.getParameter("payGroup");
        String payCode = request.getParameter("payCode");
        Long amount = Long.valueOf(request.getParameter("transAmount"));
        String bizType = request.getParameter("bizType");
        String applyUser = request.getParameter("jourCode");
        // 支付成功，商户处理后同步返回给微信参数
        if (!isSuccess) {
            logger.info("支付失败");
        } else {
            logger.info("===============付款成功==============");
            // ------------------------------
            // 处理业务开始
            // ------------------------------
            try {
                System.out.println("**** 进入内购订单服务器回调 start****");

                System.out.println("**** 进入内购订单服服务器回调 end****");
            } catch (Exception e) {
                logger.info("支付回调异常,原因：" + e.getMessage());
            }
        }
    }

    private String getReqResult(PrintWriter out, InputStream inStream)
            throws IOException {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return new String(outSteam.toByteArray(), "utf-8");
    }

    public String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";
    }
}
