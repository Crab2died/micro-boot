package com.github.crab2died;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ElasticClientTests {

    private Client client;

    @Before
    public void initClient() throws UnknownHostException {

        Settings settings = Settings.builder()
                .put("cluster.name", "elastic-app")
                .put("client.transport.sniff", true)
                .build();
        InetSocketTransportAddress transportAddress = new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300);

        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(transportAddress);
    }

    @After
    public void closeClient() {

        if (null != client) {
            client.close();
        }
    }

    @Test
    public void insertCallLog() {

        CallLog callLog = CallBuilder.buildCallLog(true, true);
        IndexResponse response = client.prepareIndex("call-log-2018-08", "call_log", callLog.getId())
                .setSource(JSON.toJSONString(callLog), XContentType.JSON)
                .get();
        System.out.println(response.toString());
    }

    @Test
    public void insetSample() throws InterruptedException {
        AtomicInteger count = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String callId = UUID.randomUUID().toString();
            int callHash = callId.hashCode() % 2;
            callHash = callHash < 0 ? -callHash : callHash;
            Random random = new Random();
            long callTime = System.currentTimeMillis();
            int uIdx = random.nextInt(1000) % 15;
            int dir = random.nextInt(2);
            for (int j = 0; j <= callHash; j++) {
                if (callHash == 1) {
                    dir = j;
                }
                int finalDir = dir;
                executorService.execute(() -> {
                    try {
                        CallLog callLog = CallBuilder.buildCallLog(callId, callTime, uIdx + finalDir, finalDir);
//                        IndexResponse resp = client.prepareIndex("call-log-2018-08", "call_log", callLog.getCallId())
//                                .setSource(JSON.toJSONString(callLog), XContentType.JSON)
//                                .get();
                        float mos = 0;
                        if (null != callLog.getFromAvgMos()) {
                            mos = callLog.getFromAvgMos();
                        }
                        if (null != callLog.getToAvgMos()) {
                            mos = callLog.getToAvgMos();
                        }
                        Map<String, Object> map = CallBuilder.obj2Map(callLog);
                        map.put("mos", mos);
                        try {
                            client.prepareUpdate("call-log-2018-08", "call_log", callLog.getCallId())
                                    //.setDoc(JSON.parseObject(JSON.toJSONString(callLog)))
                                    .setScript(CallBuilder.script(map))
                                    .setUpsert(CallBuilder.obj2Map(callLog))
                                    .setRouting(callLog.getCallId())
                                    .get();
                        } catch (Exception e) {
                            client.prepareUpdate("call-log-2018-08", "call_log", callLog.getCallId())
                                    //.setDoc(JSON.parseObject(JSON.toJSONString(callLog)))
                                    .setScript(CallBuilder.script(map))
                                    .setUpsert(CallBuilder.obj2Map(callLog))
                                    .setRouting(callLog.getCallId())
                                    .get();
                        }
                        System.out.println(JSON.toJSONString(callLog));
                        count.getAndIncrement();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                TimeUnit.MILLISECONDS.sleep(1);
            }
        }
        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                break;
            }
        }
        System.out.println("insert => " + count + ", take time : " + (System.currentTimeMillis() - time) / 1000);
    }

    @Test
    public void jsonTest() throws Exception {
        CallLog callLog = CallBuilder.buildCallLog(UUID.randomUUID().toString(), System.currentTimeMillis(), 2, 0);
        Map map = CallBuilder.obj2Map(callLog);
        System.out.println(map);
        //System.out.println(JSON.toJSONString(CallBuilder.buildCallLog(UUID.randomUUID().toString(), System.currentTimeMillis(), 2, 0)));
    }

    @Test
    public void queryAll() {
        SearchResponse resp = client.prepareSearch("call-log-2018-08").setQuery(QueryBuilders.matchAllQuery()).setSize(100).setFrom(0).get();
        Iterator<SearchHit> is = resp.getHits().iterator();
        int count = 0;
        while (is.hasNext()) {
            SearchHit hit = is.next();
            Map<String, Object> source = hit.getSource();
            if (null != source.get("from_account_id")) {
                count++;
            }
            if (null != source.get("to_account_id")) {
                count++;
            }
        }
        System.out.println(count);
    }

}
