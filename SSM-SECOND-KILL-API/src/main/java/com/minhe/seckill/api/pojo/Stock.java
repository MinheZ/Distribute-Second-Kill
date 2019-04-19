package com.minhe.seckill.api.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: SSM-SECOND-KILL
 * @description: Stock pojo
 * @author: MinheZ
 * @create: 2019-04-16 14:42
 **/

public class Stock implements Serializable {

    private static final long serialVersionUID = -8437012513227627973L;

    private Integer id;
    private String name;
    private Integer count;
    private Integer sale;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private Integer version;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", sale=" + sale +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", version=" + version +
                '}';
    }
}
