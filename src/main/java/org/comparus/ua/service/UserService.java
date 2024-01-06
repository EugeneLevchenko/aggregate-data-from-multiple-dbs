package org.comparus.ua.service;

import org.comparus.ua.cache.AllUsersFromDBCache;
import org.comparus.ua.config.DataBaseProperties;
import org.comparus.ua.dto.UserDto;
import org.comparus.ua.exception.NotFoundException;
import org.comparus.ua.repository.impl.JdbcUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import static org.comparus.ua.constants.Base.USER_NOT_FOUND_MSG;

@Service
public class UserService {

    @Autowired
    private DataBaseProperties dbProperties;

    @Autowired
    private JdbcUserRepository userRepo;

    @Autowired
    private AllUsersFromDBCache allUsersFromDBCache;

    public List<UserDto> getAllUsers() {
        List<UserDto> allUsersFromCache = allUsersFromDBCache.getAllUsersFromCache();
        if (allUsersFromCache.isEmpty()) {
            List<UserDto> allUsersFromDB = dbProperties.getDatabases().stream().flatMap(db -> userRepo.findAllByDbSource(db).stream()).toList();
            allUsersFromDBCache.addAllUsersToCache(allUsersFromDB);
            return allUsersFromDB;
        }
        return allUsersFromCache;
    }

    public UserDto getUserById(String userId) {
        return getAllUsers().stream().filter(user -> user.getId().equals(userId)).findFirst().orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));
    }

    public List<UserDto> getUsersByFilters(UserDto userDto) {
        return getAllUsers().stream()
                .filter(user -> !StringUtils.hasText(userDto.getId()) || userDto.getId().equals(user.getId()))
                .filter(user -> !StringUtils.hasText(userDto.getUsername()) || userDto.getUsername().equals(user.getUsername()))
                .filter(user -> !StringUtils.hasText(userDto.getName()) || userDto.getName().equals(user.getName()))
                .filter(user -> !StringUtils.hasText(userDto.getSurname()) || userDto.getSurname().equals(user.getSurname())).toList();
    }
}