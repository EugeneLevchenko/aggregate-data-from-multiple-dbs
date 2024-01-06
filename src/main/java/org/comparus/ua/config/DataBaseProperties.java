package org.comparus.ua.config;

import lombok.Getter;
import lombok.Setter;
import org.comparus.ua.model.DBSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.util.List;
import static org.comparus.ua.constants.Base.DB_PROPS_CLASS_PATH;
import static org.comparus.ua.constants.Base.DB_PROPS_PREFIX;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = DB_PROPS_PREFIX)
@PropertySource(value = DB_PROPS_CLASS_PATH, factory = DataBasePropertyFactory.class)
public class DataBaseProperties {
    private List<DBSource> databases;
}