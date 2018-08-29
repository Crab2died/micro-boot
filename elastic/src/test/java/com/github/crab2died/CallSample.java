package com.github.crab2died;

import com.alibaba.fastjson.annotation.JSONField;

public class CallSample {

    private long time;

    @JSONField(name = "account_id")
    private String accountId;

    @JSONField(name = "user_id")
    private String userId;

    @JSONField(name = "device_type")
    private String deviceType;

    private String isp;

    private String codec;

    private String network;

    private int direction;

    @JSONField(name = "call_id")
    private String callId;

    @JSONField(name = "start_call_time")
    private long startCallTime;

    @JSONField(name = "end_call_time")
    private long endCallTime;

    @JSONField(name = "ext_id")
    private String extId;

    @JSONField(name = "ext_num")
    private Integer extNum;

    @JSONField(name = "phone_number")
    private String phoneNumber;

    @JSONField(name = "avg_mos")
    private Float avgMos = 0F;

    @JSONField(name = "device_model")
    private String deviceModel;

    private String ip;

    private String callerloc;

    private String position;

    @JSONField(name = "avg_jitter")
    private Integer avgJitter;

    @JSONField(name = "max_jitter")
    private Integer maxJitter;

    @JSONField(name = "packet_loss_rate")
    private Float packetLossRate;

    @JSONField(name = "packet_loss")
    private Integer packetLoss;

    @JSONField(serialize = false)
    private Integer eventual = 0;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        time *= 1000;
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public long getStartCallTime() {
        return startCallTime;
    }

    public void setStartCallTime(long startCallTime) {
        startCallTime *= 1000;
        this.startCallTime = startCallTime;
    }

    public long getEndCallTime() {
        endCallTime *= 1000;
        return endCallTime;
    }

    public void setEndCallTime(long endCallTime) {
        this.endCallTime = endCallTime;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public Integer getExtNum() {
        return extNum;
    }

    public void setExtNum(Integer extNum) {
        this.extNum = extNum;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Float getAvgMos() {
        return avgMos;
    }

    public void setAvgMos(Float avgMos) {
        this.avgMos = avgMos;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCallerloc() {
        return callerloc;
    }

    public void setCallerloc(String callerloc) {
        this.callerloc = callerloc;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getAvgJitter() {
        return avgJitter;
    }

    public void setAvgJitter(Integer avgJitter) {
        this.avgJitter = avgJitter;
    }

    public Integer getMaxJitter() {
        return maxJitter;
    }

    public void setMaxJitter(Integer maxJitter) {
        this.maxJitter = maxJitter;
    }

    public Float getPacketLossRate() {
        return packetLossRate;
    }

    public void setPacketLossRate(Float packetLossRate) {
        this.packetLossRate = packetLossRate;
    }

    public Integer getPacketLoss() {
        return packetLoss;
    }

    public void setPacketLoss(Integer packetLoss) {
        this.packetLoss = packetLoss;
    }

    public Integer getEventual() {
        return eventual;
    }

    public void setEventual(Integer eventual) {
        this.eventual = eventual;
    }
}
