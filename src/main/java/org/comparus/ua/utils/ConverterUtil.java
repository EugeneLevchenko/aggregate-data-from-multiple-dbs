package org.comparus.ua.utils;

import org.comparus.ua.dto.UserDto;
import org.comparus.ua.model.DBMapping;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
public class ConverterUtil {

    public List<UserDto> convertUserDataFromDBToDtoList(List<Map<String, Object>> rows, DBMapping mapping) {
        return rows.stream().map(row -> new UserDto(
                (String) row.get(mapping.getId()),
                (String) row.get(mapping.getUsername()),
                (String) row.get(mapping.getName()),
                (String) row.get(mapping.getSurname()))).toList();
    }
}