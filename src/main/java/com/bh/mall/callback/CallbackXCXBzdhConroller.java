package com.bh.mall.callback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bh.mall.ao.IInnerOrderAO;
import com.bh.mall.ao.IOrderAO;

/**
 * 币种兑换回调控制层
 * @author: xieyj 
 * @since: 2017年4月20日 下午5:00:52 
 * @history:
 */
@Controller
public class CallbackXCXBzdhConroller {

    private static Logger logger = Logger
        .getLogger(CallbackXCXBzdhConroller.class);

    @Autowired
    IInnerOrderAO innerOrderAO;

    @Autowired
    IOrderAO orderAO;

    // 自身支付回调
    @RequestMapping("/xcx/callback")
    public synchronized void doCallbackPay(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        try {
            // 获取回调参数
            PrintWriter out = response.getWriter();
            InputStream inStream = request.getInputStream();
            String result = getReqResult(out, inStream);
            logger.info("**** 公众号支付回调结果 ****：" + result);

            // 通知微信服务器(我已收到请求，不用再继续回调我了)

            String noticeStr = setXML("SUCCESS", "");
            // 解析回调结果并通知业务biz
            orderAO.paySuccess(result);
            out.print(new ByteArrayInputStream(
                noticeStr.getBytes(Charset.forName("UTF-8"))));
        } catch (Exception e) {
            logger.info("公众号支付回调异常,原因：" + e.getMessage());
        }

        // boolean isSuccess =
        // Boolean.valueOf(request.getParameter("isSuccess"));
        // String payGroup = request.getParameter("payGroup");
        // String payCode = request.getParameter("payCode");
        // Long amount = Long.valueOf(request.getParameter("transAmount"));
        // String bizType = request.getParameter("bizType");
        // // 支付成功，商户处理后同步返回给微信参数
        // if (!isSuccess) {
        // logger.info("支付失败");
        // } else {
        // logger.info("===============付款成功==============");
        // // ------------------------------
        // // 处理业务开始
        // // ------------------------------
        // try {
        // if (EBizType.AJ_GMNP.getCode().equals(bizType)) {
        // System.out.println("**** 进入内购产品售卖，服务器回调 start****");
        // innerOrderAO.paySuccess(payGroup, payCode, amount);
        // System.out.println("**** 进入内购产品购买售卖，服务器回调 end****");
        // } else if (EBizType.AJ_GMCP.getCode().equals(bizType)) {
        // System.out.println("**** 进入产品售卖，服务器回调 start****");
        // // orderAO.paySuccess(payGroup, payCode, amount);
        // System.out.println("**** 进入产品购买售卖，服务器回调 end****");
        // }
        // } catch (Exception e) {
        // logger.info("支付回调异常,原因：" + e.getMessage());
        // }
        // }
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
