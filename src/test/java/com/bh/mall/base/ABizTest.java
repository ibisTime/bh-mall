package com.bh.mall.base;

import org.apache.commons.lang3.StringUtils;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.User;
import com.bh.mall.enums.ECurrency;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml",
        "classpath:spring/spring_bo.xml" })
public abstract class ABizTest {

    protected Logger logger = LoggerFactory.getLogger(ABizTest.class);

    @Autowired
    IUserBO userBO;

    @Autowired
    IAccountBO accountBO;

    public Long getAmount(String userId) {
        Long amount = 0L;
        User user = userBO.getUser(userId);
        if (StringUtils.isNotBlank(user.getUserReferee())) {
            Account account = accountBO.getAccountByUser(userId,
                ECurrency.MK_CNY.getCode());
            amount = amount + account.getAmount();
        }
        return amount;

    }
}
