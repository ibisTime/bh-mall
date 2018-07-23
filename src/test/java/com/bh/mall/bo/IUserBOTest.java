/**
 * @Title IUserBOTest.java 
 * @Package com.ibis.pz 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 下午9:23:30 
 * @version V1.0   
 */
package com.bh.mall.bo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bh.mall.base.ABizTest;

/** 
 * @author: miyb 
 * @since: 2015-3-7 下午9:23:30 
 * @history:
 */
public class IUserBOTest extends ABizTest {
    @Autowired
    private IUserBO userBO;

    @Autowired
    private IBuserBO buserBO;

    @Test
    public void doRegister() {
        // String userId = userBO.doRegister("13958092437", "123456",
        // "127.0.0.1",
        // "", 0L);
        // logger.info("doRegister : {}", userId);

    }

    @Test
    public void doBuserRegister() {
        // String userId = buserBO.doRegister("13958092437", "123456",
        // "127.0.0.1",
        // "", 0L);
        // logger.info("doRegister : {}", userId);

    }

    // 错误测试

}
