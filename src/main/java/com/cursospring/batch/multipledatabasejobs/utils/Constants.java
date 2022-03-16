package com.cursospring.batch.multipledatabasejobs.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final int CHUNK_SIZE = 10;
    public static final String CONTEXT_KEY_NAME = "migration";
    public static final String H2_CONFIG = "H2Config";
    public static final String POSTGRES_CONFIG = "PostgresConfig";
    public static final String SQL_SERVER_CONFIG = "SQLServerConfig";
}
