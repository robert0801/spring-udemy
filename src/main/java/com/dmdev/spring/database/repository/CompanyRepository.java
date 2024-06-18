package com.dmdev.spring.database.repository;

import com.dmdev.spring.bpp.Auditing;
import com.dmdev.spring.bpp.Transaction;
import com.dmdev.spring.database.entity.Company;
import com.dmdev.spring.database.pool.ConnectionPool;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final ConnectionPool connectionPool;
    private final List<ConnectionPool> pools;
    private final Integer poolSize;

    public CompanyRepository(@Qualifier(value = "pool2") ConnectionPool connectionPool,
                             List<ConnectionPool> pools,
                             @Value("${db.pool.size}") Integer poolSize) {
        this.connectionPool = connectionPool;
        this.pools = pools;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
        System.out.println("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findingById method...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("deleting method...");
    }
}
