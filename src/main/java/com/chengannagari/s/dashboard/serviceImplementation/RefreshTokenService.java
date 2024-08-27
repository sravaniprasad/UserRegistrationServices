package com.chengannagari.s.dashboard.serviceImplementation;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chengannagari.s.dashboard.Entity.RefreshToken;
import com.chengannagari.s.dashboard.Entity.User;
import com.chengannagari.s.dashboard.Reposiyory.RefreshTokenRepository;
import com.chengannagari.s.dashboard.Reposiyory.UserRepository;


@Service
public class RefreshTokenService {

	public long refreshTokenValidity=2*60 * 1000;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public RefreshToken create(String userName) {
		
		User user=userRepo.findByEmail(userName).get();
		RefreshToken rt2=user.getRefreshToken();
		if(rt2==null) {
			rt2=	RefreshToken.builder()
					.refreshToken(UUID.randomUUID().toString())
					.expiry(Instant.now().plusMillis(refreshTokenValidity))
				 	.user(user).build();
		}
		else {
			rt2.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
		}
	user.setRefreshToken(rt2);
		
	refreshTokenRepo.save(rt2);
	return rt2;
	}
	
	public RefreshToken verifyRefreshToken(String refreshToken) throws Exception {
		
		
	RefreshToken rt1=refreshTokenRepo.findByRefreshToken(refreshToken).orElseThrow(()->new RuntimeException("give token doesn't exist in database"));
	
	if(rt1.getExpiry().compareTo(Instant.now())<0) {
		refreshTokenRepo.delete(rt1);
		throw new Exception("Refresh token Expired!!!");
	}
	 return rt1;
	}
	
	
}
