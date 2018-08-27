package com.github.crab2died.common.config.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Aspect
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Component
public class DataSourceServiceAop implements PriorityOrdered {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceServiceAop.class);

    @Before("execution(* com.github.crab2died.*.service..*.insert*(..)) || " +
            "execution(* com.github.crab2died.*.service..*.update*(..)) || " +
            "execution(* com.github.crab2died.*.service..*.upsert*(..)) || " +
            "execution(* com.github.crab2died.*.service..*.delete*(..)) || " +
            "execution(* com.github.crab2died.*.service..*.put*(..)) || " +
            "execution(* com.github.crab2died.*.service..*.set*(..)) || " +
            "execution(* com.github.crab2died.*.service..*.remove*(..))"
    )
    public void writeDataSource() {
        logger.debug("Datasource APO :: write db selected...");
        DataSourceContextHolder.write();
    }

    @Before("execution(* com.github.crab2died.*.service..*.get*(..)) || " +
            "execution(* com.github.crab2died.*.service..*.query*(..)) || " +
            "execution(* com.github.crab2died.*.service..*.find*(..)) || " +
            "execution(* com.github.crab2died.*.service..*.overview*(..)) || " +
            "execution(* com.github.crab2died.*.service..*.process*(..))"
    )
    public void readDataSource() {
        logger.debug("Datasource APO :: read db selected...");
        if (!DataSourceEnum.WRITE_DB.type().equals(DataSourceContextHolder.jdbcType())) {
            DataSourceContextHolder.read();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
