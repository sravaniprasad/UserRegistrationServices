package com.chengannagari.s.dashboard.Reposiyory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chengannagari.s.dashboard.Entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

	Optional<RefreshToken> findByRefreshToken(String token);
}
 