package com.minhe.seckill.controller;

import com.minhe.seckill.exception.SoldOutException;
import com.minhe.seckill.service.OrderService;
import com.minhe.seckill.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: SSM-SECOND-KILL
 * @description:
 * @author: MinheZ
 * @create: 2019-04-20 11:02
 **/
@Controller
@RequestMapping(value = "/seckill")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private StockService stockService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/createByOptimisticLock/{sid}")
    public String createByOptimisticLock(@PathVariable int sid) {
        logger.info("sid=[{}]", sid);
        try {
            orderService.createOrderByOptimisticLock(sid);
            return "/success";
        } catch (SoldOutException soldOut) {
            logger.info("soldOut", soldOut);
        } catch (Exception e) {
            logger.info("Exception", e);
        }
        return "/failed";
    }

    @RequestMapping("/createWrongOrder/{sid}")
    public String createWrongOrder(@PathVariable int sid) {
        logger.info("sid=[{}]", sid);
        try {
            orderService.createWrongOrder(sid);
            return "/success";
        } catch (SoldOutException soldOut) {
            logger.info("soldOut", soldOut);
        } catch (Exception e) {
            logger.info("Exception", e);
        }
        return "/failed";
    }

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
