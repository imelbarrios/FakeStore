package com.store.fake.repository;

import com.store.fake.domain.ClientDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<ClientDomain,Long> {


    @Query(value = "SELECT * FROM TblClient WHERE Username=:username", nativeQuery = true)
    ClientDomain findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM TblClient WHERE IdClient=:idClient", nativeQuery = true)
    ClientDomain findByIdClient(@Param("idClient")Long idClient);

}
