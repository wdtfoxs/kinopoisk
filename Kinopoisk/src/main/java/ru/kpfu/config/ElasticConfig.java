package ru.kpfu.config;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * Created by Aydar Farrakhov on 05.09.16.
 */
@Configuration
public class ElasticConfig {

    private static final String CLUSTER_PROP = "cluster.name";
    private static final String TRANSPORT_SNIFF_PROP = "client.transport.sniff";

    public static final String INDEX = "kinopoisk";
    //public static final String MOVIE_TYPE = "movies";


    private String elasticIP = "127.0.0.1";

    private int elasticPort = 9300;

    private String clusterName = "elasticsearch";

    private boolean transportSniff =true;

    @Bean
    public Client elasticSearchClient() throws UnknownHostException {
        Settings settings = Settings.settingsBuilder()
                .put(CLUSTER_PROP, clusterName)
                .put(TRANSPORT_SNIFF_PROP, transportSniff)
                .build();

        Client client = TransportClient.builder().settings(settings)
                .build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(elasticIP), elasticPort));

        return client;
    }




}
