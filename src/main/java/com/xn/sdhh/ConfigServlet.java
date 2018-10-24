package com.xn.sdhh;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.xn.sdhh.common.PropertiesUtil;

public class ConfigServlet extends HttpServlet {
    private static final long serialVersionUID = -7810670972793349913L;

    @Override
    public void init() throws ServletException {
        super.init();
        PropertiesUtil.init("/config.properties");
    }

}
