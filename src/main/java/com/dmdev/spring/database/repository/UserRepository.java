package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final ConnectionPool connectionPool;

    public UserRepository(@Qualifier("pool2") ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
