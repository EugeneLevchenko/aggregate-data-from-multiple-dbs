package org.comparus.ua.model;

import lombok.Value;

@Value
public class DBSource {
    String name;
    String strategy;
    String url;
    String table;
    String user;
    String password;
    DBMapping mapping;
}
