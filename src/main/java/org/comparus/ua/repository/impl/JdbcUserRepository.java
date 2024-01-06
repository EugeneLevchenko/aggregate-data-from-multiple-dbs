package org.comparus.ua.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.comparus.ua.dto.UserDto;
import org.comparus.ua.enums.DBStrategy;
import org.comparus.ua.exception.InternalErrorException;
import org.comparus.ua.model.DBMapping;
import org.comparus.ua.model.DBSource;
import org.comparus.ua.repository.UserRepository;
import org.comparus.ua.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import java.util.List;
import static org.comparus.ua.constants.Base.*;
import static org.comparus.ua.enums.DBStrategy.valueOf;

@Slf4j
@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ConverterUtil converterUtil;

    @Override
    public List<UserDto> findAllByDbSource(DBSource db) {
        DBMapping mapping = db.getMapping();
        String sqlSelectAllUsers = String.format(SQL_SELECT_ALL_USERS_QUERY, mapping.getId(),
                mapping.getUsername(), mapping.getName(), mapping.getSurname(), db.getTable());
        try {
            jdbcTemplate.setDataSource(setDriver(db));
            return converterUtil.convertUserDataFromDBToDtoList(jdbcTemplate.queryForList(sqlSelectAllUsers), mapping);
        } catch (DataAccessException e) {
            log.error(CONNECTION_TO_DB_FAILED_ERROR_LOG, e.getCause().getMessage());
            throw new InternalErrorException(e.getCause().getMessage());
        }
    }

    private DriverManagerDataSource setDriver(DBSource db) {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName(getDriverClassName(valueOf(db.getStrategy())));
        driver.setUrl(db.getUrl() + db.getName());
        driver.setUsername(db.getUser());
        driver.setPassword(db.getPassword());
        return driver;
    }

    private String getDriverClassName(DBStrategy strategy) {
        return switch (strategy) {
            case postgres -> POSTGRESQL_DRIVER;
            case mysql -> MYSQL_DRIVER;
            case oracle -> ORACLE_DRIVER;
        };
    }
}
