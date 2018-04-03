package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Intro;
import com.bh.mall.dto.req.XN627241Req;

@Component
public interface IIntroAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addIntro(Intro data);

    public void dropIntro(String code);

    public void editIntro(XN627241Req req);

    public Paginable<Intro> queryIntroPage(int start, int limit, Intro condition);

    public List<Intro> queryIntroList(Intro condition);

    public Intro getIntro(String code);

}
