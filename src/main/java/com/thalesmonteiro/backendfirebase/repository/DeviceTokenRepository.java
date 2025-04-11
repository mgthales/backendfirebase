package com.thalesmonteiro.backendfirebase.repository;


import com.thalesmonteiro.backendfirebase.entity.DeviceToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceTokenRepository extends JpaRepository<DeviceToken, Long> {
    boolean existsByToken(String token);
    Optional<DeviceToken> findByNomeaparelho(String nomeaparelho);

}