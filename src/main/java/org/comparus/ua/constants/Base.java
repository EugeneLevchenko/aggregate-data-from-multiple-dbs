package org.comparus.ua.constants;

public class Base {
    public static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
    public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String SQL_SELECT_ALL_USERS_QUERY = "SELECT %s, %s, %s, %s FROM %s";
    public static final String USER_NOT_FOUND_MSG = "User with id: [%s] not found";
    public static final String USERS_MAPPING = "/users";
    public static final String FIND_ALL_MAPPING = "/find-all";
    public static final String FIND_BY_ID_MAPPING = "/find-by-id";
    public static final String FIND_BY_FILTERS_MAPPING = "/find-by-filters";
    public static final String DB_PROPS_PREFIX = "data-sources";
    public static final String DB_PROPS_CLASS_PATH = "classpath:data-source.yml";
    public static final String CONNECTION_TO_DB_FAILED_ERROR_LOG = "Connection to DB failed at findAllByDbSource() with message: [{}]";
}
