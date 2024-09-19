package com.frogdevelopment.data;

import static jakarta.transaction.Transactional.TxType.REQUIRED;

import jakarta.transaction.Transactional;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

@Transactional(REQUIRED)
@JdbcRepository(dialect = Dialect.MYSQL)
public interface MyItemRepository extends PageableRepository<MyEntity, Long> {

    void updateByField1(String field1, MyEntity newEntity);
}
