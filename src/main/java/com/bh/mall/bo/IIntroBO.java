package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Intro;



public interface IIntroBO extends IPaginableBO<Intro> {


    public boolean isIntroExist(String code);


    public String saveIntro(Intro data);


    public int removeIntro(String code);


    public int refreshIntro(Intro data);


    public List<Intro> queryIntroList(Intro condition);


    public Intro getIntro(String code);


}