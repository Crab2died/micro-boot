package com.github.crab2died.elastic.configuration;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * elastic configuration
 *
 * @date 2018/7/6 10:45
 */
@Configuration
@ConfigurationProperties(prefix = "spring.elastic")
public class ElasticConfiguration {

    private String clusterName = "elastic-app";

    private String clusterAddress = "localhost:9300";

    private boolean transportSniff = false;

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterAddress() {
        return clusterAddress;
    }

    public void setClusterAddress(String clusterAddress) {
        this.clusterAddress = clusterAddress;
    }

    public boolean isTransportSniff() {
        return transportSniff;
    }

    public void setTransportSniff(boolean transportSniff) {
        this.transportSniff = transportSniff;
    }

    @Bean
    public Client createClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", transportSniff)
                .build();
        String[] addresses = clusterAddress.split(",");
        TransportAddress[] transportAddress = new TransportAddress[addresses.length];
        for (int i = 0; i < addresses.length; i++) {
            String[] hostAndPort = addresses[i].split(":");
            transportAddress[i] = new TransportAddress(InetAddress.getByName(hostAndPort[0]), Integer.parseInt(hostAndPort[1]));
        }
        return new PreBuiltTransportClient(settings)
                .addTransportAddresses(transportAddress);
    }
}

