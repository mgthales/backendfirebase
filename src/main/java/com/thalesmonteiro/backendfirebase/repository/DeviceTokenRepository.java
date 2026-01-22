package com.thalesmonteiro.backendfirebase.repository;


import com.thalesmonteiro.backendfirebase.entity.DeviceToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceTokenRepository extends JpaRepository<DeviceToken, Long> {
    boolean existsByToken(String token);
        @Query(
            value = "SELECT * FROM devicetoken WHERE nomeaparelho = :nomeaparelho",
            nativeQuery = true
    )
    Optional<DeviceToken> findByNomeaparelho(@Param("nomeaparelho") String nomeaparelho);


}
