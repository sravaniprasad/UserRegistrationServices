package com.chengannagari.s.dashboard.Reposiyory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chengannagari.s.dashboard.Entity.Role;
import com.chengannagari.s.dashboard.Entity.User;
import com.chengannagari.s.dashboard.Entity.UserDTO;

import jakarta.transaction.Transactional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findAll();
Optional<User> findByFirstName(String firstName);
boolean existsByFirstName(String firstName);
Optional<User> findByUserName(String userName);
boolean existsByUserName(String userName);
boolean existsByPhoneNumber(long phoneNumber);
boolean existsByEmail(String email);
Optional<User> findByPhoneNumber(long phoneNumber);
Optional<User> findByEmail(String email);
@Query("SELECT u FROM User u WHERE u.role = :role")
User findByRole(@Param("role") Role role);
void save(Optional<User> user);
@Modifying
@Transactional
@Query("UPDATE User u SET u.password = :newPassword WHERE u.email = :email")
void updatePasswordByEmail(@Param("email") String email, @Param("newPassword") String newPassword);





//User findByRole(Role admin);
}
