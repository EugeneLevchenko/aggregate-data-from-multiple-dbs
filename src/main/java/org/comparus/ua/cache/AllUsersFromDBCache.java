package org.comparus.ua.cache;

import org.comparus.ua.dto.UserDto;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class AllUsersFromDBCache {

    private final List<UserDto> allUsersCache = new ArrayList<>();

    public List<UserDto> getAllUsersFromCache() {
        return this.allUsersCache;
    }

    public void addAllUsersToCache(List<UserDto> users) {
        this.allUsersCache.addAll(users);
    }
}
