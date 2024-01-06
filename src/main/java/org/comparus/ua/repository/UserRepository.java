package org.comparus.ua.repository;

import org.comparus.ua.dto.UserDto;
import org.comparus.ua.model.DBSource;
import java.util.List;

public interface UserRepository {

    List<UserDto> findAllByDbSource(DBSource ds);

}
