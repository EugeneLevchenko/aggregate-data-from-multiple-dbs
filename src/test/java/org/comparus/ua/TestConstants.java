package org.comparus.ua;

public class TestConstants {
    public static final String LOCAL_URI = "http://localhost:";
    public static final String DB_PROPS_FIELD_NAME = "dbProperties";
    public static final String PARAM_USER_ID = "?userId=";

    public static final String PG_IMAGE = "postgres:15.1-alpine";
    public static final String PG_DB_NAME = "test1";
    public static final String PG_DB_USER = "test1";
    public static final String PG_DB_PASSWORD = "test1";
    public static final String MYSQL_IMAGE = "mysql:8.0.24";
    public static final String MYSQL_DB_NAME = "test2";
    public static final String MYSQL_DB_USER = "test2";
    public static final String MYSQL_DB_PASSWORD = "test2";
    public static final String ORACLE_IMAGE = "gvenzl/oracle-xe:21-slim";
    public static final String ORACLE_DB_NAME = "test3";
    public static final String ORACLE_DB_USER = "test3";
    public static final String ORACLE_DB_PASSWORD = "test3";

    public static final String PG_CREATE_SQL = "CREATE TABLE users_postgres (user_id_postgres VARCHAR (256), login_postgres VARCHAR (256), first_name_postgres VARCHAR (256), last_name_postgres VARCHAR (256));";
    public static final String PG_INSERT_SQL = "INSERT INTO users_postgres (user_id_postgres, login_postgres, first_name_postgres, last_name_postgres) VALUES ('id-1', 'login-1', 'name-1', 'surname-1'), ('id-2', 'login-2', 'name-2', 'surname-2'), ('id-3', 'login-3', 'name-3', 'surname-3');";
    public static final String MYSQL_CREATE_SQL = "CREATE TABLE users_mysql (user_id_mysql VARCHAR (256), login_mysql VARCHAR (256),first_name_mysql VARCHAR (256),last_name_mysql VARCHAR (256));";
    public static final String MYSQL_INSERT_SQL = "INSERT INTO users_mysql (user_id_mysql, login_mysql, first_name_mysql, last_name_mysql) VALUES ('id-4', 'login-4', 'name-4', 'surname-4'), ('id-5', 'login-5', 'name-5', 'surname-5'), ('id-3', 'login-3', 'name-3', 'surname-3');";
    public static final String ORACLE_CREATE_SQL = "CREATE TABLE users_oracle (user_id_oracle VARCHAR2 (256), login_oracle VARCHAR2 (256), first_name_oracle VARCHAR2 (256), last_name_oracle VARCHAR2 (256))";
    public static final String ORACLE_INSERT_SQL = "INSERT ALL INTO users_oracle (user_id_oracle, login_oracle, first_name_oracle, last_name_oracle) VALUES ('id-6', 'login-6', 'name-6', 'surname-6') INTO users_oracle (user_id_oracle, login_oracle, first_name_oracle, last_name_oracle) VALUES ('id-7', 'login-7', 'name-7', 'surname-7') INTO users_oracle (user_id_oracle, login_oracle, first_name_oracle, last_name_oracle) VALUES ('id-3', 'login-3', 'name-3', 'surname-3') SELECT 1 FROM dual";

    public static final String PG_TABLE = "users_postgres";
    public static final String MYSQL_TABLE = "users_mysql";
    public static final String ORACLE_TABLE = "users_oracle";

    public static final String PG_COLUMN_1 = "user_id_postgres";
    public static final String PG_COLUMN_2 = "login_postgres";
    public static final String PG_COLUMN_3 = "first_name_postgres";
    public static final String PG_COLUMN_4 = "last_name_postgres";

    public static final String MYSQL_COLUMN_1 = "user_id_mysql";
    public static final String MYSQL_COLUMN_2 = "login_mysql";
    public static final String MYSQL_COLUMN_3 = "first_name_mysql";
    public static final String MYSQL_COLUMN_4 = "last_name_mysql";

    public static final String ORACLE_COLUMN_1 = "user_id_oracle";
    public static final String ORACLE_COLUMN_2 = "login_oracle";
    public static final String ORACLE_COLUMN_3 = "first_name_oracle";
    public static final String ORACLE_COLUMN_4 = "last_name_oracle";

    public static final String PG_ID_1 = "id-1";
    public static final String PG_LOGIN_1 = "login-1";
    public static final String PG_NAME_1 = "name-1";
    public static final String PG_SURNAME_1 = "surname-1";

    public static final String PG_ID_2 = "id-2";
    public static final String PG_LOGIN_2 = "login-2";
    public static final String PG_NAME_2 = "name-2";
    public static final String PG_SURNAME_2 = "surname-2";

    public static final String PG_ID_3 = "id-3";
    public static final String PG_LOGIN_3 = "login-3";
    public static final String PG_NAME_3 = "name-3";
    public static final String PG_SURNAME_3 = "surname-3";

    public static final String MYSQL_ID_1 = "id-4";
    public static final String MYSQL_LOGIN_1 = "login-4";
    public static final String MYSQL_NAME_1 = "name-4";
    public static final String MYSQL_SURNAME_1 = "surname-4";

    public static final String MYSQL_ID_2 = "id-5";
    public static final String MYSQL_LOGIN_2 = "login-5";
    public static final String MYSQL_NAME_2 = "name-5";
    public static final String MYSQL_SURNAME_2 = "surname-5";

    public static final String MYSQL_ID_3 = "id-3";
    public static final String MYSQL_LOGIN_3 = "login-3";
    public static final String MYSQL_NAME_3 = "name-3";
    public static final String MYSQL_SURNAME_3 = "surname-3";

    public static final String ORACLE_ID_1 = "id-6";
    public static final String ORACLE_LOGIN_1 = "login-6";
    public static final String ORACLE_NAME_1 = "name-6";
    public static final String ORACLE_SURNAME_1 = "surname-6";

    public static final String ORACLE_ID_2 = "id-7";
    public static final String ORACLE_LOGIN_2 = "login-7";
    public static final String ORACLE_NAME_2 = "name-7";
    public static final String ORACLE_SURNAME_2 = "surname-7";

    public static final String ORACLE_ID_3 = "id-3";
    public static final String ORACLE_LOGIN_3 = "login-3";
    public static final String ORACLE_NAME_3 = "name-3";
    public static final String ORACLE_SURNAME_3 = "surname-3";

    public static final String MAPPING_ID = "id";
    public static final String MAPPING_USERNAME = "username";
    public static final String MAPPING_NAME = "name";
    public static final String MAPPING_SURNAME = "surname";
}