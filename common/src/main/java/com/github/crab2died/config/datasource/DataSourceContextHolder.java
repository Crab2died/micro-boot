package com.github.crab2died.config.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceContextHolder {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    private static final ThreadLocal<String> local = new ThreadLocal<>();

    static void write() {
        logger.debug("write db selected...");
        local.set(DataSourceEnum.WRITE_DB.type());
    }

    static void read() {
        logger.debug("read db selected...");
        local.set(DataSourceEnum.READE_DB.type());
    }

    static String jdbcType() {
        return local.get();
    }

    public static void clear() {
        local.remove();
    }

}
