package com.rootlab.photogram.repository;

import com.rootlab.photogram.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
