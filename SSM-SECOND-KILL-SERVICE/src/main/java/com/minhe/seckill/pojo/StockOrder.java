package com.minhe.seckill.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: SSM-SECOND-KILL
 * @description: StockOrder pojo
 * @author: MinheZ
 * @create: 2019-04-16 14:51
 **/

public class StockOrder implements Serializable {

    private Integer id;
    private String name;
    private Long userPhone;
    private Date createTime;


    private Stock stock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "StockOrder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userPhone=" + userPhone +
                ", createTime=" + createTime +
                '}';
    }
}
