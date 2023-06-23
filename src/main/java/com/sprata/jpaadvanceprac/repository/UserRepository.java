package com.sprata.jpaadvanceprac.repository;

import com.sprata.jpaadvanceprac.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
