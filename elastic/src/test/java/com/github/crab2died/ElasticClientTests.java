package com.github.crab2died;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.admin.indices.alias.exists.AliasesExistResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
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
        TransportAddress transportAddress1 = new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300);
        TransportAddress transportAddress2 = new TransportAddress(InetAddress.getByName("localhost"), 9300);
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(transportAddress1)
                .addTransportAddress(transportAddress2);
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
        IndexResponse response = client.prepareIndex("call-log-2018-09", "call_log", callLog.getId())
                .setSource(JSON.toJSONString(callLog), XContentType.JSON)
                .get();
        System.out.println(response.toString());
    }

    @Test
    public void insertSample() throws InterruptedException {
        AtomicInteger count = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        long time = System.currentTimeMillis();
        for (int i = 0; i < 30000; i++) {
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
                        Script script = CallBuilder.script(map);
                        UpdateRequest request = new UpdateRequest("call-log-2018-08", "call_log", callLog.getCallId());
                        request.routing(callLog.getCallId()).script(script).upsert(JSON.toJSONString(callLog), XContentType.JSON);
                        try {
//                            client.prepareUpdate("call-log-2018-08", "call_log", callLog.getCallId())
//                                    //.setDoc(JSON.parseObject(JSON.toJSONString(callLog)))
//                                    .setScript(script)
//                                    .setUpsert(CallBuilder.obj2Map(callLog))
//                                    .setRouting(callLog.getCallId())
//                                    .get();
                            client.update(request).get();
                            System.out.println(count.incrementAndGet() + " => " + JSON.toJSONString(callLog));
                        } catch (Exception e) {
//                            client.prepareUpdate("call-log-2018-08", "call_log", callLog.getCallId())
//                                    //.setDoc(JSON.parseObject(JSON.toJSONString(callLog)))
//                                    .setScript(script)
//                                    .setUpsert(CallBuilder.obj2Map(callLog))
//                                    .setRouting(callLog.getCallId())
//                                    .get();
                            client.update(request).get();
                            System.out.println(count.incrementAndGet() + " [retry]=> " + JSON.toJSONString(callLog));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                break;
            }
        }
        System.out.println("insert => " + count.get() + ", take time : " + (System.currentTimeMillis() - time) / 1000);
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
            Map<String, Object> source = hit.getSourceAsMap();
            if (null != source.get("from_account_id")) {
                count++;
            }
            if (null != source.get("to_account_id")) {
                count++;
            }
        }
        System.out.println(count);
    }

    @Test
    public void queryTest() {

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("call-log-2018-08").setTypes("call_log");
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        BoolQueryBuilder queryBuilder01 = QueryBuilders.boolQuery();
        queryBuilder01
                .should(QueryBuilders.matchQuery("from_account_id", "A-00001"))
                .should(QueryBuilders.matchQuery("to_account_id", "A-00001"));

        queryBuilder.must(queryBuilder01);
        queryBuilder.must(QueryBuilders.rangeQuery("call_time").gte(1533800460000L).lt(1533800580000L));

        searchRequestBuilder.setQuery(queryBuilder);
        searchRequestBuilder.setSize(10);
        System.out.println(searchRequestBuilder.toString());
        SearchResponse resp = searchRequestBuilder.execute().actionGet();
        System.out.println(resp);

    }

    @Test
    public void indicesTest() {
        //client.admin().indices().exists(new IndicesExistsRequest().indices("call-log-2018-10")).actionGet();
        IndicesExistsResponse exist = client.admin().indices().prepareExists("call-log-2018-10").get();
        if (!exist.isExists()) {
            CreateIndexResponse createResp = client.admin().indices().prepareCreate("call-log-2018-10")
//                    .setSettings(Settings.builder()
//                            .put("index.number_of_shards", 5)
//                            .put("index.number_of_replicas", 0)
//                            .put("refresh_interval", "20s")
//                            .put("max_result_window", 1000000)
            //        )
                    .setSource(CALL_LOG_MAPPING, XContentType.JSON)
            //        .setAliases(ALIASES)
                    .get();
        }
        // IndicesAliasesResponse resp = client.admin().indices().prepareAliases().removeAlias("call-log-2018-10", "call_logs").get();
        AliasesExistResponse result = client.admin().indices().prepareAliasesExist("call_logs").addIndices("call-log-2018-10").get();
        System.out.println(result.isExists());
        // System.out.println(resp.isAcknowledged());
    }

    private static final String ALIASES = "{\n" +
            " \"call_logs\":{}\n" +
            "}";

    private static final String CALL_LOG_MAPPING = "{\n" +
            "  \"settings\": {\n" +
            "    \"number_of_shards\": 5,\n" +
            "    \"number_of_replicas\": 0,\n" +
            "    \"refresh_interval\": \"20s\",\n" +
            "    \"max_result_window\": 1000000\n" +
            "  },\n" +
            "  \"aliases\":{\"call_logs\":{}}," +
            "  \"mappings\": {\n" +
            "    \"call_log\": {\n" +
            "      \"_all\": {\n" +
            "        \"enabled\": false\n" +
            "      },\n" +
            "      \"properties\": {\n" +
            "        \"call_time\": {\n" +
            "          \"type\": \"date\"\n" +
            "        },\n" +
            "        \"call_id\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"mos\": {\n" +
            "          \"type\": \"float\"\n" +
            "        },\n" +
            "        \"duration\": {\n" +
            "          \"type\": \"integer\"\n" +
            "        },\n" +
            "        \"from_account_id\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"from_user_id\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"from_ext_id\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"from_ext_num\": {\n" +
            "          \"type\": \"integer\"\n" +
            "        },\n" +
            "        \"from_phone_number\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"from_avg_mos\": {\n" +
            "          \"type\": \"float\"\n" +
            "        },\n" +
            "        \"from_min_mos\": {\n" +
            "          \"type\": \"float\"\n" +
            "        },\n" +
            "        \"from_avg_jitter\": {\n" +
            "          \"type\": \"integer\"\n" +
            "        },\n" +
            "        \"from_max_jitter\": {\n" +
            "          \"type\": \"integer\"\n" +
            "        },\n" +
            "        \"from_packet_loss_rate\": {\n" +
            "          \"type\": \"float\"\n" +
            "        },\n" +
            "        \"from_packet_loss_total\": {\n" +
            "          \"type\": \"integer\"\n" +
            "        },\n" +
            "        \"from_device_type\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"from_device_model\": {\n" +
            "          \"type\": \"text\"\n" +
            "        },\n" +
            "        \"from_ip\": {\n" +
            "          \"type\": \"text\"\n" +
            "        },\n" +
            "        \"from_codec\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"from_network\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"from_isp\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"from_location\": {\n" +
            "          \"type\": \"text\"\n" +
            "        },\n" +
            "        \"to_account_id\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"to_user_id\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"to_ext_id\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"to_ext_num\": {\n" +
            "          \"type\": \"integer\"\n" +
            "        },\n" +
            "        \"to_phone_number\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"to_avg_mos\": {\n" +
            "          \"type\": \"float\"\n" +
            "        },\n" +
            "        \"to_min_mos\": {\n" +
            "          \"type\": \"float\"\n" +
            "        },\n" +
            "        \"to_avg_jitter\": {\n" +
            "          \"type\": \"integer\"\n" +
            "        },\n" +
            "        \"to_max_jitter\": {\n" +
            "          \"type\": \"integer\"\n" +
            "        },\n" +
            "        \"to_packet_loss_rate\": {\n" +
            "          \"type\": \"float\"\n" +
            "        },\n" +
            "        \"to_packet_loss_total\": {\n" +
            "          \"type\": \"integer\"\n" +
            "        },\n" +
            "        \"to_device_type\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"to_device_model\": {\n" +
            "          \"type\": \"text\"\n" +
            "        },\n" +
            "        \"to_ip\": {\n" +
            "          \"type\": \"text\"\n" +
            "        },\n" +
            "        \"to_codec\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"to_network\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"to_isp\": {\n" +
            "          \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"to_location\": {\n" +
            "          \"type\": \"text\"\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
