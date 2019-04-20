package com.minhe.seckill.controller;

import com.minhe.seckill.service.OrderService;
import com.minhe.seckill.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: SSM-SECOND-KILL
 * @description:
 * @author: MinheZ
 * @create: 2019-04-20 11:02
 **/
@Controller
@RequestMapping(value = "/")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private StockService stockService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/getStockCount")
    @ResponseBody
    public String getStockCount(Integer id) {
        int currentCount = 0;
        try {
            currentCount = stockService.getStockCount(id);
        } catch (Exception e) {
            logger.error("Exception", e);
        }
        logger.info("currentCount={}", currentCount);
        return String.valueOf(currentCount);
    }
}
