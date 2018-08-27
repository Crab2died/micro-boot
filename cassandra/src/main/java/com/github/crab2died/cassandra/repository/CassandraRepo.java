package com.github.crab2died.cassandra.repository;

import com.github.crab2died.cassandra.domain.CallSample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.stratio.cassandra.lucene.builder.Builder.*;

@Repository
public class CassandraRepo {

    private static final Logger logger = LoggerFactory.getLogger(CassandraRepo.class);

    @Autowired
    private CassandraOperations cassandraOperations;

    public List<CallSample> getList() {
        List<CallSample> list;

        String filter = search()
                .filter(
                        must(range("time").lower(1533003380000L).upper(1535799780000L))
                        .should(match("account_id", "A-00001"), match("user_id", "U-00003"))
                )
                .build();
        String cql = "SELECT * FROM call_sample where expr(call_sample_index, '" + filter + "') LIMIT 3";
        list = cassandraOperations.select(cql, CallSample.class);
        logger.info(cql);
        return list;
    }

}
