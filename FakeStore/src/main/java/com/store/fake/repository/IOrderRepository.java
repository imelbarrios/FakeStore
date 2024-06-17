package com.store.fake.repository;

import com.store.fake.domain.OrderDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderDomain, Long> {

    @Query(value = "SELECT * FROM TblOrder WHERE IdOrder=:id", nativeQuery = true)
    OrderDomain findByRecordId(@Param("id") Long id);


    @Query(value = "SELECT * FROM TblOrder WHERE IdClient=:id", nativeQuery = true)
    List<OrderDomain> findByIdClient(@Param("id") Long id);


}
