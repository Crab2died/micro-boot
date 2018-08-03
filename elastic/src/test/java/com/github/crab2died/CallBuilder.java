package com.github.crab2died;


import com.alibaba.fastjson.annotation.JSONField;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class CallBuilder {

    private static final String[] ACCOUNT = {"A-000001", "A-000002", "A-000003", "A-000004"};
    private static final String[] USER = {"U-000001", "U-000002", "U-000003", "U-000004", "U-000005", "U-000006", "U-000007", "U-000008",
            "U-000009", "U-000010", "U-000011", "U-000012", "U-000013", "U-000014", "U-000015"};
    private static final String[] EXT = {"E-000001", "E-000002", "E-000003", "E-000004", "E-000005", "E-000006", "E-000007", "E-000008",
            "E-000009", "E-000010", "E-000011", "E-000012", "E-000013", "E-000014", "E-000015"};
    private static final Integer[] CODEC = {1, 2, 3, 4};//{"OPUS", "G729", "G722", "G711"};
    private static final Integer[] NT = {1, 2, 3, 4, 5};//{"3G", "4G", "WIFI", "wired"};
    private static final Integer[] DEVICE = {1, 2, 3};//{"HardPhone", "MobileClient", "DesktopClient"};
    private static final String[] PHONE = {"1253211", "10551876251", "17865231121", "152345617", "7826312", "19732121", "3457891", "13567312"};
    private static final String[] ISP = {"CMCC", "Earthlink", "MSN", "AOL", "Verizon"};
    private static final String[] IP = {"172.12.67.12", "10.27.121.2", "24.121.3.11", "78.129.78.11", "92.15.7.132", "72.182.12.123"};
    private static final String[] LOCATION = {"HeFei,CN", "HangZhou,CN", "SuZHou,CN", "BeiJing,CN", "California,US", "Texas,US"};

    public static CallLog buildCallLog(boolean from, boolean to) {
        if (!from && !to)
            throw new IllegalArgumentException("must one side is true");

        CallLog callLog = new CallLog();
        String id = UUID.randomUUID().toString();
        Random random = new Random();

        callLog.setId(id);
        callLog.setCallId(id);
        callLog.setCallTime(System.currentTimeMillis());
        callLog.setDuration(random.nextInt(692762));

        int fidx = random.nextInt(15);
        int tidx = fidx + 2 > 14 ? 0 : fidx + 2;

        if (from) {

            callLog.setFromUserId(USER[fidx]);
            callLog.setFromAccountId(ACCOUNT[fidx % 4]);
            callLog.setFromExtId(EXT[fidx]);
            callLog.setFromExtNum(fidx + 100);
            int fj = random.nextInt(1200);
            callLog.setFromAvgJitter(fj);
            callLog.setFromMaxJitter(random.nextInt(500) + fj);
            float fm = random.nextFloat() * 10;
            callLog.setFromAvgMos(fm + random.nextInt(5));
            callLog.setFromMinMos(fm);
            callLog.setFromCodec(CODEC[fidx % 4]);
            callLog.setFromDeviceType(DEVICE[fidx % 3]);
            callLog.setFromDeviceModel(null);
            callLog.setFromIp(IP[fidx % 6]);
            callLog.setFromIsp(ISP[fidx % 5]);
            callLog.setFromPhoneNumber(PHONE[fidx % 8]);
            callLog.setFromNetwork(NT[fidx % 5]);
            callLog.setFromLocation(LOCATION[fidx % 6]);
            callLog.setFromPacketLossRate(random.nextFloat());
            callLog.setFromPacketLossTotal(random.nextInt(16291));
        }

        if (to) {
            callLog.setToUserId(USER[tidx]);
            callLog.setToAccountId(ACCOUNT[tidx % 4]);
            callLog.setToExtId(EXT[tidx]);
            callLog.setToExtNum(tidx + 100);
            int tj = random.nextInt(1200);
            callLog.setToAvgJitter(tj);
            callLog.setToMaxJitter(random.nextInt(500) + tj);
            float tm = random.nextFloat() * 10;
            callLog.setToAvgMos(tm + random.nextInt(5));
            callLog.setToMinMos(tm);
            callLog.setToCodec(CODEC[tidx % 4]);
            callLog.setToDeviceType(DEVICE[tidx % 3]);
            callLog.setToDeviceModel(null);
            callLog.setToIp(IP[tidx % 6]);
            callLog.setToIsp(ISP[tidx % 5]);
            callLog.setToPhoneNumber(PHONE[tidx % 8]);
            callLog.setToNetwork(NT[tidx % 5]);
            callLog.setToLocation(LOCATION[tidx % 6]);
            callLog.setToPacketLossRate(random.nextFloat());
            callLog.setToPacketLossTotal(random.nextInt(16291));
        }

        if (null == callLog.getFromAvgMos()) {
            callLog.setMos(callLog.getToAvgMos());
        } else if (null == callLog.getToAvgMos()) {
            callLog.setMos(callLog.getFromAvgMos());
        } else {
            callLog.setMos(callLog.getFromAvgMos() > callLog.getToAvgMos() ? callLog.getToAvgMos() : callLog.getFromAvgMos());
        }
        return callLog;
    }


    public static CallLog buildCallLog(String callId, long callTime, int uIdx, int direction) {

        Random random = new Random();
        int callHash = callId.hashCode() % 80;
        callHash = callHash < 0 ? -callHash : callHash;

        uIdx = uIdx > 14 ? 0 : uIdx;

        CallLog callLog = new CallLog();
        callLog.setId(callId);
        callLog.setCallId(callId);
        callLog.setCallTime(callTime);
        callLog.setDuration(callHash * 1050);


        if (direction == 0) {

            callLog.setFromUserId(USER[uIdx]);
            callLog.setFromAccountId(ACCOUNT[uIdx % 4]);
            callLog.setFromExtId(EXT[uIdx]);
            callLog.setFromExtNum(uIdx + 100);
            int fj = random.nextInt(1200);
            callLog.setFromAvgJitter(fj);
            callLog.setFromMaxJitter(random.nextInt(500) + fj);
            float fm = random.nextFloat() * 10;
            callLog.setFromAvgMos(fm + random.nextInt(5));
            callLog.setMos(callLog.getFromAvgMos());
            callLog.setFromMinMos(fm);
            callLog.setFromCodec(CODEC[random.nextInt(1000) % 4]);
            callLog.setFromDeviceType(DEVICE[random.nextInt(1000) % 3]);
            callLog.setFromDeviceModel(null);
            callLog.setFromIp(IP[random.nextInt(1000) % 6]);
            callLog.setFromIsp(ISP[random.nextInt(1000) % 5]);
            callLog.setFromPhoneNumber(PHONE[random.nextInt(1000) % 8]);
            callLog.setFromNetwork(NT[random.nextInt(1000) % 5]);
            callLog.setFromLocation(LOCATION[random.nextInt(1000) % 6]);
            callLog.setFromPacketLossRate(random.nextFloat());
            callLog.setFromPacketLossTotal(random.nextInt(16291));
        }

        if (direction == 1) {
            callLog.setToUserId(USER[uIdx]);
            callLog.setToAccountId(ACCOUNT[uIdx % 4]);
            callLog.setToExtId(EXT[uIdx]);
            callLog.setToExtNum(uIdx + 100);
            int tj = random.nextInt(1200);
            callLog.setToAvgJitter(tj);
            callLog.setToMaxJitter(random.nextInt(500) + tj);
            float tm = random.nextFloat() * 10;
            callLog.setToAvgMos(tm + random.nextInt(5));
            callLog.setMos(callLog.getToAvgMos());
            callLog.setToMinMos(tm);
            callLog.setToCodec(CODEC[random.nextInt(1000) % 4]);
            callLog.setToDeviceType(DEVICE[random.nextInt(1000) % 3]);
            callLog.setToDeviceModel(null);
            callLog.setToIp(IP[random.nextInt(1000) % 6]);
            callLog.setToIsp(ISP[random.nextInt(1000) % 5]);
            callLog.setToPhoneNumber(PHONE[random.nextInt(1000) % 8]);
            callLog.setToNetwork(NT[random.nextInt(1000) % 5]);
            callLog.setToLocation(LOCATION[random.nextInt(1000) % 6]);
            callLog.setToPacketLossRate(random.nextFloat());
            callLog.setToPacketLossTotal(random.nextInt(16291));
        }

        return callLog;
    }


    public static CallSample buildCallSample(String callId, long callTime, int uIdx, int direction) {

        Random random = new Random();
        int callHash = callId.hashCode() % 80;
        callHash = callHash < 0 ? -callHash : callHash;
        CallSample sample = new CallSample();
        sample.setCallId(callId);
        sample.setCallTime(callTime);
        sample.setDirection(direction);
        sample.setDuration(callHash * 1050);
        uIdx = uIdx > 14 ? 0 : uIdx;
        sample.setUserId(USER[uIdx]);
        sample.setAccountId(ACCOUNT[uIdx % 4]);
        sample.setExtId(EXT[uIdx]);
        sample.setExtNum(uIdx + 100);
        int jitter = random.nextInt(1000);
        sample.setAvgJitter(jitter);
        sample.setMaxJitter(random.nextInt(100) + jitter);
        Float mos = random.nextFloat() * 10;
        sample.setAvgMos(mos + random.nextInt(3));
        sample.setMinMos(mos);
        sample.setCodec(CODEC[random.nextInt(1000) % 4]);
        sample.setDeviceType(DEVICE[random.nextInt(1000) % 3]);
        sample.setPhoneNumber(PHONE[random.nextInt(1000) % 8]);
        sample.setPacketLossRate(random.nextFloat());
        sample.setPacketLossTotal(random.nextInt(300));
        sample.setIsp(ISP[random.nextInt(1000) % 4]);
        sample.setNetwork(NT[random.nextInt(1000) % 4]);
        sample.setIp(IP[random.nextInt(1000) % 6]);
        sample.setEventual(1);
        sample.setTime(System.currentTimeMillis());
        return sample;
    }


    public static Map<String, Object> obj2Map(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            JSONField ann = field.getAnnotation(JSONField.class);
            if (null == field.get(obj)) continue;
            if (null != ann) {
                if (ann.serialize())
                    map.put(ann.name(), field.get(obj));
            } else {
                map.put(field.getName(), field.get(obj));
            }
        }
        return map;
    }

    public static Script script(Map<String, Object> map) {

        StringBuilder sb = new StringBuilder("if (ctx._source.mos > params.mos) {ctx._source.mos = params.mos}");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if ("mos".equals(entry.getKey())) continue;
            sb.append("ctx._source.").append(entry.getKey()).append(" = params.").append(entry.getKey()).append(";");
        }
        System.out.println(map.get("call_id") + "=>" + sb);
        return new Script(ScriptType.INLINE, Script.DEFAULT_SCRIPT_LANG, sb.toString(), map);
    }

}
