package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Intro;

@Component
public interface IIntroAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addIntro(Intro data);

    public int dropIntro(String code);

    public int editIntro(Intro data);

    public Paginable<Intro> queryIntroPage(int start, int limit,
            Intro condition);

    public List<Intro> queryIntroList(Intro condition);

    public Intro getIntro(String code);

}
