package com.github.crab2died;

import com.alibaba.fastjson.annotation.JSONField;

public class CallLog {

    @JSONField(serialize = false)
    private String id;

    @JSONField(name = "call_id")
    private String callId;

    @JSONField(name = "call_time")
    private long callTime;

    private Long duration;

    private Float mos;

    @JSONField(name = "from_user_id")
    private String fromUserId;

    @JSONField(name = "from_account_id")
    private String fromAccountId;

    @JSONField(name = "from_ext_id")
    private String fromExtId;

    @JSONField(name = "from_ext_num")
    private Integer fromExtNum;

    @JSONField(name = "from_phone_number")
    private String fromPhoneNumber;

    @JSONField(name = "from_ip")
    private String fromIp;

    @JSONField(name = "from_callerloc")
    private String fromCallerloc;

    @JSONField(name = "from_position")
    private String fromPosition;

    @JSONField(name = "from_isp")
    private String fromIsp;

    @JSONField(name = "from_network")
    private String fromNetwork;

    @JSONField(name = "from_device_type")
    private String fromDeviceType;

    @JSONField(name = "from_device_model")
    private String fromDeviceModel;

    @JSONField(name = "from_codec")
    private String fromCodec;

    @JSONField(name = "from_avg_jitter")
    private Integer fromAvgJitter;

    @JSONField(name = "from_max_jitter")
    private Integer fromMaxJitter;

    @JSONField(name = "from_packet_loss_rate")
    private Float fromPacketLossRate;

    @JSONField(name = "from_packet_loss")
    private Integer fromPacketLoss;

    @JSONField(name = "from_avg_mos")
    private Float fromAvgMos;

    @JSONField(name = "to_user_id")
    private String toUserId;

    @JSONField(name = "to_account_id")
    private String toAccountId;

    @JSONField(name = "to_ext_id")
    private String toExtId;

    @JSONField(name = "to_ext_num")
    private Integer toExtNum;

    @JSONField(name = "to_phone_number")
    private String toPhoneNumber;

    @JSONField(name = "to_isp")
    private String toIsp;

    @JSONField(name = "to_ip")
    private String toIp;

    @JSONField(name = "to_callerloc")
    private String toCallerloc;

    @JSONField(name = "to_position")
    private String toPosition;

    @JSONField(name = "to_network")
    private String toNetwork;

    @JSONField(name = "to_device_type")
    private String toDeviceType;

    @JSONField(name = "to_device_model")
    private String toDeviceModel;

    @JSONField(name = "to_codec")
    private String toCodec;

    @JSONField(name = "to_avg_jitter")
    private Integer toAvgJitter;

    @JSONField(name = "to_max_jitter")
    private Integer toMaxJitter;

    @JSONField(name = "to_packet_loss_rate")
    private Float toPacketLossRate;

    @JSONField(name = "to_packet_loss")
    private Integer toPacketLoss;

    @JSONField(name = "to_avg_mos")
    private Float toAvgMos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public long getCallTime() {
        return callTime;
    }

    public void setCallTime(long callTime) {
        this.callTime = callTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Float getMos() {
        return mos;
    }

    public void setMos(Float mos) {
        if (Float.isNaN(mos)) {
            mos = 0F;
        }
        this.mos = mos;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public String getFromExtId() {
        return fromExtId;
    }

    public void setFromExtId(String fromExtId) {
        this.fromExtId = fromExtId;
    }

    public Integer getFromExtNum() {
        return fromExtNum;
    }

    public void setFromExtNum(Integer fromExtNum) {
        this.fromExtNum = fromExtNum;
    }

    public String getFromPhoneNumber() {
        return fromPhoneNumber;
    }

    public void setFromPhoneNumber(String fromPhoneNumber) {
        this.fromPhoneNumber = fromPhoneNumber;
    }

    public String getFromIp() {
        return fromIp;
    }

    public void setFromIp(String fromIp) {
        this.fromIp = fromIp;
    }

    public String getFromCallerloc() {
        return fromCallerloc;
    }

    public void setFromCallerloc(String fromCallerloc) {
        this.fromCallerloc = fromCallerloc;
    }

    public String getFromPosition() {
        return fromPosition;
    }

    public void setFromPosition(String fromPosition) {
        this.fromPosition = fromPosition;
    }

    public String getFromIsp() {
        return fromIsp;
    }

    public void setFromIsp(String fromIsp) {
        this.fromIsp = fromIsp;
    }

    public String getFromNetwork() {
        return fromNetwork;
    }

    public void setFromNetwork(String fromNetwork) {
        this.fromNetwork = fromNetwork;
    }

    public String getFromDeviceType() {
        return fromDeviceType;
    }

    public void setFromDeviceType(String fromDeviceType) {
        this.fromDeviceType = fromDeviceType;
    }

    public String getFromDeviceModel() {
        return fromDeviceModel;
    }

    public void setFromDeviceModel(String fromDeviceModel) {
        this.fromDeviceModel = fromDeviceModel;
    }

    public String getFromCodec() {
        return fromCodec;
    }

    public void setFromCodec(String fromCodec) {
        this.fromCodec = fromCodec;
    }

    public Integer getFromAvgJitter() {
        return fromAvgJitter;
    }

    public void setFromAvgJitter(Integer fromAvgJitter) {
        this.fromAvgJitter = fromAvgJitter;
    }

    public Integer getFromMaxJitter() {
        return fromMaxJitter;
    }

    public void setFromMaxJitter(Integer fromMaxJitter) {
        this.fromMaxJitter = fromMaxJitter;
    }

    public Float getFromPacketLossRate() {
        return fromPacketLossRate;
    }

    public void setFromPacketLossRate(Float fromPacketLossRate) {
        if (Float.isNaN(fromPacketLossRate)) {
            fromPacketLossRate = 0F;
        }
        this.fromPacketLossRate = fromPacketLossRate;
    }

    public Integer getFromPacketLoss() {
        return fromPacketLoss;
    }

    public void setFromPacketLoss(Integer fromPacketLoss) {
        this.fromPacketLoss = fromPacketLoss;
    }

    public Float getFromAvgMos() {
        return fromAvgMos;
    }

    public void setFromAvgMos(Float fromAvgMos) {
        if (Float.isNaN(fromAvgMos)) {
            fromAvgMos = 0F;
        }
        this.fromAvgMos = fromAvgMos;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }

    public String getToExtId() {
        return toExtId;
    }

    public void setToExtId(String toExtId) {
        this.toExtId = toExtId;
    }

    public Integer getToExtNum() {
        return toExtNum;
    }

    public void setToExtNum(Integer toExtNum) {
        this.toExtNum = toExtNum;
    }

    public String getToPhoneNumber() {
        return toPhoneNumber;
    }

    public void setToPhoneNumber(String toPhoneNumber) {
        this.toPhoneNumber = toPhoneNumber;
    }

    public String getToIsp() {
        return toIsp;
    }

    public void setToIsp(String toIsp) {
        this.toIsp = toIsp;
    }

    public String getToIp() {
        return toIp;
    }

    public void setToIp(String toIp) {
        this.toIp = toIp;
    }

    public String getToCallerloc() {
        return toCallerloc;
    }

    public void setToCallerloc(String toCallerloc) {
        this.toCallerloc = toCallerloc;
    }

    public String getToPosition() {
        return toPosition;
    }

    public void setToPosition(String toPosition) {
        this.toPosition = toPosition;
    }

    public String getToNetwork() {
        return toNetwork;
    }

    public void setToNetwork(String toNetwork) {
        this.toNetwork = toNetwork;
    }

    public String getToDeviceType() {
        return toDeviceType;
    }

    public void setToDeviceType(String toDeviceType) {
        this.toDeviceType = toDeviceType;
    }

    public String getToDeviceModel() {
        return toDeviceModel;
    }

    public void setToDeviceModel(String toDeviceModel) {
        this.toDeviceModel = toDeviceModel;
    }

    public String getToCodec() {
        return toCodec;
    }

    public void setToCodec(String toCodec) {
        this.toCodec = toCodec;
    }

    public Integer getToAvgJitter() {
        return toAvgJitter;
    }

    public void setToAvgJitter(Integer toAvgJitter) {
        this.toAvgJitter = toAvgJitter;
    }

    public Integer getToMaxJitter() {
        return toMaxJitter;
    }

    public void setToMaxJitter(Integer toMaxJitter) {
        this.toMaxJitter = toMaxJitter;
    }

    public Float getToPacketLossRate() {
        return toPacketLossRate;
    }

    public void setToPacketLossRate(Float toPacketLossRate) {
        if (Float.isNaN(toPacketLossRate)) {
            toPacketLossRate = 0F;
        }
        this.toPacketLossRate = toPacketLossRate;
    }

    public Integer getToPacketLoss() {
        return toPacketLoss;
    }

    public void setToPacketLoss(Integer toPacketLoss) {
        this.toPacketLoss = toPacketLoss;
    }

    public Float getToAvgMos() {
        return toAvgMos;
    }

    public void setToAvgMos(Float toAvgMos) {
        if (Float.isNaN(toAvgMos)) {
            toAvgMos = 0F;
        }
        this.toAvgMos = toAvgMos;
    }
}