package com.github.crab2died.config.datasource;

public enum DataSourceEnum {

    /** reade db*/
    READE_DB {
        @Override
        protected String type() {
            return "read_db";
        }
    },

    /** write & read db*/
    WRITE_DB {
        @Override
        protected String type() {
            return "write_db";
        }
    };

    protected abstract String type();
}
