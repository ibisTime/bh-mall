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

@Controller
public class CallbackInnerOrderController {
    private static Logger logger = Logger
        .getLogger(CallbackInnerOrderController.class);

    @Autowired
    IInnerOrderAO innerOrderAO;

    // 自身支付回调
    @RequestMapping("/innerOrder/callback")
    public synchronized void doCallbackPay(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        try {
            // 获取回调参数
            PrintWriter out = response.getWriter();
            InputStream inStream = request.getInputStream();
            String result = getReqResult(out, inStream);
            logger.info("**** 公众号支付内购订单回调结果 ****：" + result);
            // 解析回调结果并通知业务biz
            innerOrderAO.paySuccess(result);
            // 通知微信服务器(我已收到请求，不用再继续回调我了)
            String noticeStr = setXML("SUCCESS", "");
            out.print(new ByteArrayInputStream(
                noticeStr.getBytes(Charset.forName("UTF-8"))));
        } catch (Exception e) {
            logger.info("公众号支付回调异常,原因：" + e.getMessage());
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
