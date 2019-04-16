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
    private Integer sid;
    private String name;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
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
}
