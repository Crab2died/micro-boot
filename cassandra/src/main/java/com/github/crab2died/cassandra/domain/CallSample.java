package com.github.crab2died.cassandra.domain;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("call_sample")
public class CallSample {

    @PrimaryKeyColumn(name = "call_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String callId;

    @Column("time")
    private long time;

    @Column("account_id")
    private String accountId;

    @Column("user_id")
    private String userId;

    @Column("ext_id")
    private String extId;

    @Column("ext_num")
    private int extNum;

    @Column("avg_mos")
    private Float avgMos;

    @Column("min_mos")
    private Float minMos;

    @Column("ip")
    private String ip;

    @Column("isp")
    private String isp;

    @Column("device_type")
    private int deviceType;

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public int getExtNum() {
        return extNum;
    }

    public void setExtNum(int extNum) {
        this.extNum = extNum;
    }

    public Float getAvgMos() {
        return avgMos;
    }

    public void setAvgMos(Float avgMos) {
        this.avgMos = avgMos;
    }

    public Float getMinMos() {
        return minMos;
    }

    public void setMinMos(Float minMos) {
        this.minMos = minMos;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }
}
