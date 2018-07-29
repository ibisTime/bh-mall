package com.bh.mall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.bh.mall.ao.IMiniCodeAO;
import com.bh.mall.ao.IProCodeAO;
import com.bh.mall.core.StringValidater;
import com.bh.mall.enums.EBoolean;

/**
 * type ： 类型
 * number ： 数量
 * @param request
 * @param response 
 * @create: 2018年7月2日 上午1:29:58 nyc
 * @history:
 */
@Controller
public class CodeController {

    @Autowired
    IProCodeAO proCodeAO;

    @Autowired
    IMiniCodeAO miniCodeAO;

    @RequestMapping("/createCode")
    private void createCode(HttpServletRequest request,
            HttpServletResponse response) {
        String str = null;
        try {
            str = URLDecoder.decode(request.getQueryString(), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        JSONObject json = JSONObject.parseObject(str);
        // type==0新增条形码（箱码），type==1新增防伪溯源码
        String type = json.getString("type");
        int number = StringValidater.toInteger(json.getString("number"));
        if (EBoolean.NO.getCode().equals(type)) {
            proCodeAO.addProCode(number);
        } else {
            miniCodeAO.addMiniCode(number);
        }

        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.append("it's over");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
