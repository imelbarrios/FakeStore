package com.store.fake.repository;

import com.store.fake.domain.OrderDetailDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderDetailRepository extends JpaRepository<OrderDetailDomain, Long> {

    @Query(value = "SELECT * FROM TblOrderDetail WHERE IdOrder=:idOrder", nativeQuery = true)
    List<OrderDetailDomain> findAllByIdOrder(@Param("idOrder") Long idOrder);



}
