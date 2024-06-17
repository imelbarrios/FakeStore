package com.store.fake.repository;

import com.store.fake.domain.OrderPaymentDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderPaymentRepository extends JpaRepository<OrderPaymentDomain,Long> {

    @Query(value = "SELECT * FROM TblOrderPayment WHERE IdOrder=:idOrder", nativeQuery = true)
    OrderPaymentDomain findByIdOrder(@Param("idOrder") Long idOrder);

}
