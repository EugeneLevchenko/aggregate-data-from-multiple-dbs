package org.comparus.ua;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.comparus.ua.config.DataBaseProperties;
import org.comparus.ua.dto.UserDto;
import org.comparus.ua.enums.DBStrategy;
import org.comparus.ua.model.DBMapping;
import org.comparus.ua.model.DBSource;
import org.comparus.ua.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.util.ReflectionTestUtils;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import static org.comparus.ua.TestConstants.*;
import static org.comparus.ua.constants.Base.*;
import static org.hamcrest.Matchers.containsInRelativeOrder;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTestsWithTestContainers {

    @Autowired
    private UserService userService;

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = LOCAL_URI + port;
    }

    @Container
    private final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(PG_IMAGE)
            .withDatabaseName(PG_DB_NAME)
            .withUsername(PG_DB_USER)
            .withPassword(PG_DB_PASSWORD);

    @Container
    private final MySQLContainer<?> mysql = new MySQLContainer<>(MYSQL_IMAGE)
            .withDatabaseName(MYSQL_DB_NAME)
            .withUsername(MYSQL_DB_USER)
            .withPassword(MYSQL_DB_PASSWORD);

    @Container
    private final OracleContainer oracle = new OracleContainer(ORACLE_IMAGE)
            .withDatabaseName(ORACLE_DB_NAME)
            .withUsername(ORACLE_DB_USER)
            .withPassword(ORACLE_DB_PASSWORD);

    @Test
    public void testGetAllUsers() {
        initAllDatabases();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(USERS_MAPPING + FIND_ALL_MAPPING)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(".", Matchers.hasSize(9))
                .body(MAPPING_ID, containsInRelativeOrder(PG_ID_1, PG_ID_2, PG_ID_3, MYSQL_ID_1, MYSQL_ID_2, MYSQL_ID_3, ORACLE_ID_1, ORACLE_ID_2, ORACLE_ID_3))
                .body(MAPPING_USERNAME, containsInRelativeOrder(PG_LOGIN_1, PG_LOGIN_2, PG_LOGIN_3, MYSQL_LOGIN_1, MYSQL_LOGIN_2, MYSQL_LOGIN_3, ORACLE_LOGIN_1, ORACLE_LOGIN_2, ORACLE_LOGIN_3))
                .body(MAPPING_NAME, containsInRelativeOrder(PG_NAME_1, PG_NAME_2, PG_NAME_3, MYSQL_NAME_1, MYSQL_NAME_2, MYSQL_NAME_3, ORACLE_NAME_1, ORACLE_NAME_2, ORACLE_NAME_3))
                .body(MAPPING_SURNAME, containsInRelativeOrder(PG_SURNAME_1, PG_SURNAME_2, PG_SURNAME_3, MYSQL_SURNAME_1, MYSQL_SURNAME_2, MYSQL_SURNAME_3, ORACLE_SURNAME_1, ORACLE_SURNAME_2, ORACLE_SURNAME_3));
    }

    @Test
    public void testGetUsersByFilters() {
        initAllDatabases();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new UserDto(PG_ID_1, PG_LOGIN_1, PG_NAME_1, PG_SURNAME_1))
                .when()
                .post(USERS_MAPPING + FIND_BY_FILTERS_MAPPING)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(".", Matchers.hasSize(1))
                .body(MAPPING_ID, containsInRelativeOrder(PG_ID_1))
                .body(MAPPING_USERNAME, containsInRelativeOrder(PG_LOGIN_1))
                .body(MAPPING_NAME, containsInRelativeOrder(PG_NAME_1))
                .body(MAPPING_SURNAME, containsInRelativeOrder(PG_SURNAME_1));

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new UserDto(PG_ID_1, "", "                 ", null))
                .when()
                .post(USERS_MAPPING + FIND_BY_FILTERS_MAPPING)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(".", Matchers.hasSize(1))
                .body(MAPPING_ID, containsInRelativeOrder(PG_ID_1))
                .body(MAPPING_USERNAME, containsInRelativeOrder(PG_LOGIN_1))
                .body(MAPPING_NAME, containsInRelativeOrder(PG_NAME_1))
                .body(MAPPING_SURNAME, containsInRelativeOrder(PG_SURNAME_1));

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new UserDto(PG_ID_3, PG_LOGIN_3, PG_NAME_3, PG_SURNAME_3))
                .when()
                .post(USERS_MAPPING + FIND_BY_FILTERS_MAPPING)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(".", Matchers.hasSize(3))
                .body(MAPPING_ID, containsInRelativeOrder(PG_ID_3, MYSQL_ID_3, ORACLE_ID_3))
                .body(MAPPING_USERNAME, containsInRelativeOrder(PG_LOGIN_3, MYSQL_LOGIN_3, ORACLE_LOGIN_3))
                .body(MAPPING_NAME, containsInRelativeOrder(PG_NAME_3, MYSQL_NAME_3, ORACLE_NAME_3))
                .body(MAPPING_SURNAME, containsInRelativeOrder(PG_SURNAME_3, MYSQL_SURNAME_3, ORACLE_SURNAME_3));

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new UserDto(null, "", PG_NAME_3, "                              "))
                .when()
                .post(USERS_MAPPING + FIND_BY_FILTERS_MAPPING)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(".", Matchers.hasSize(3))
                .body(MAPPING_ID, containsInRelativeOrder(PG_ID_3, MYSQL_ID_3, ORACLE_ID_3))
                .body(MAPPING_USERNAME, containsInRelativeOrder(PG_LOGIN_3, MYSQL_LOGIN_3, ORACLE_LOGIN_3))
                .body(MAPPING_NAME, containsInRelativeOrder(PG_NAME_3, MYSQL_NAME_3, ORACLE_NAME_3))
                .body(MAPPING_SURNAME, containsInRelativeOrder(PG_SURNAME_3, MYSQL_SURNAME_3, ORACLE_SURNAME_3));
    }

    @Test
    public void testGetUserById() {
        initAllDatabases();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(USERS_MAPPING + FIND_BY_ID_MAPPING + PARAM_USER_ID + PG_ID_1)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(MAPPING_ID, Matchers.equalTo(PG_ID_1))
                .body(MAPPING_USERNAME, Matchers.equalTo(PG_LOGIN_1))
                .body(MAPPING_NAME, Matchers.equalTo(PG_NAME_1))
                .body(MAPPING_SURNAME, Matchers.equalTo(PG_SURNAME_1));

        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(USERS_MAPPING + FIND_BY_ID_MAPPING + PARAM_USER_ID + MYSQL_ID_1)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(MAPPING_ID, Matchers.equalTo(MYSQL_ID_1))
                .body(MAPPING_USERNAME, Matchers.equalTo(MYSQL_LOGIN_1))
                .body(MAPPING_NAME, Matchers.equalTo(MYSQL_NAME_1))
                .body(MAPPING_SURNAME, Matchers.equalTo(MYSQL_SURNAME_1));

        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(USERS_MAPPING + FIND_BY_ID_MAPPING + PARAM_USER_ID + ORACLE_ID_1)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(MAPPING_ID, Matchers.equalTo(ORACLE_ID_1))
                .body(MAPPING_USERNAME, Matchers.equalTo(ORACLE_LOGIN_1))
                .body(MAPPING_NAME, Matchers.equalTo(ORACLE_NAME_1))
                .body(MAPPING_SURNAME, Matchers.equalTo(ORACLE_SURNAME_1));
    }

    private void initAllDatabases() {
        JdbcTemplate jdbcTemplatePostgres = new JdbcTemplate(setupDriver(postgres));
        jdbcTemplatePostgres.execute(PG_CREATE_SQL);
        jdbcTemplatePostgres.execute(PG_INSERT_SQL);

        JdbcTemplate jdbcTemplateMySql = new JdbcTemplate(setupDriver(mysql));
        jdbcTemplateMySql.execute(MYSQL_CREATE_SQL);
        jdbcTemplateMySql.execute(MYSQL_INSERT_SQL);

        JdbcTemplate jdbcTemplateOracle = new JdbcTemplate(setupDriver(oracle));
        jdbcTemplateOracle.execute(ORACLE_CREATE_SQL);
        jdbcTemplateOracle.execute(ORACLE_INSERT_SQL);

        DataBaseProperties dataBaseProperties = new DataBaseProperties();
        dataBaseProperties.setDatabases(
                List.of(new DBSource("", DBStrategy.postgres.name(), postgres.getJdbcUrl(), PG_TABLE, postgres.getUsername(),
                                postgres.getPassword(), new DBMapping(PG_COLUMN_1, PG_COLUMN_2, PG_COLUMN_3, PG_COLUMN_4)),
                        new DBSource("", DBStrategy.mysql.name(), mysql.getJdbcUrl(), MYSQL_TABLE, mysql.getUsername(),
                                mysql.getPassword(), new DBMapping(MYSQL_COLUMN_1, MYSQL_COLUMN_2, MYSQL_COLUMN_3, MYSQL_COLUMN_4)),
                        new DBSource("", DBStrategy.oracle.name(), oracle.getJdbcUrl(), ORACLE_TABLE, oracle.getUsername(),
                                oracle.getPassword(), new DBMapping(ORACLE_COLUMN_1, ORACLE_COLUMN_2, ORACLE_COLUMN_3, ORACLE_COLUMN_4))));

        ReflectionTestUtils.setField(userService, DB_PROPS_FIELD_NAME, dataBaseProperties);
    }

    private DriverManagerDataSource setupDriver(JdbcDatabaseContainer<?> container) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(container.getDriverClassName());
        dataSource.setUrl(container.getJdbcUrl());
        dataSource.setUsername(container.getUsername());
        dataSource.setPassword(container.getPassword());
        return dataSource;
    }
}
