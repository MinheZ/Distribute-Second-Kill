package com.minhe.seckill.service.impl;

import com.minhe.seckill.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml"})
public class OrderServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService order;

    private Integer id = 1;

    @Test
    public void createWrongOrder() {
        logger.info("result={}", order.createWrongOrder(id));
        //System.out.println(order);
    }

//    private SampleResult result;
//
//    @Override
//    public Arguments getDefaultParameters() {
//        Arguments params = new Arguments();
//        params.addArgument("id","1");
//        return params;
//    }
//
//    @Override
//    public void setupTest(JavaSamplerContext arg0) {
//        result = new SampleResult();
//        if (id.equals(1))
//            result.setSamplerData("1");
//    }
//
//    @Override
//    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
//        boolean success = true;
//        result.sampleStart();
//        try {
//            createWrongOrder();
//        } catch (Exception e) {
//            success = false;
//        } finally {
//            result.sampleEnd();
//            result.setSuccessful(success);
//        }
//        return result;
//    }
//
//    @Override
//    public void teardownTest(JavaSamplerContext arg0) {
//
//    }
}