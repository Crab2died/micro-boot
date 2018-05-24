package com.github.crab2died.config.datasource;

public enum DataSourceEnum {

    READE_DB {
        @Override
        protected String type() {
            return "read_db";
        }
    },

    WRITE_DB {
        @Override
        protected String type() {
            return "write_db";
        }
    };

    protected abstract String type();
}
