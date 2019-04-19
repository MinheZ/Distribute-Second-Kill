package com.minhe.seckill.dto;

/**
 * @program: SSM-SECOND-KILL
 * @description: 决定是否暴露秒杀接口
 * @author: MinheZ
 * @create: 2019-04-17 19:13
 **/

public class Exposer {

    private boolean exposer;    // 是否开启秒杀

    private String md5;     // 加密措施

    private Integer id;     // 商品id

    private long now;       // 系统当前之间

    private long start;     // 开始时间

    private long end;       // 结束时间

    public Exposer(boolean exposer, String md5, Integer id) {
        this.exposer = exposer;
        this.md5 = md5;
        this.id = id;
    }

    public Exposer(boolean exposer, Integer id, long now, long start, long end) {
        this.exposer = exposer;
        this.id = id;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposer, Integer id) {
        this.exposer = exposer;
        this.id = id;
    }

    public boolean isExposer() {
        return exposer;
    }

    public void setExposer(boolean exposer) {
        this.exposer = exposer;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposer=" + exposer +
                ", md5='" + md5 + '\'' +
                ", id=" + id +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
