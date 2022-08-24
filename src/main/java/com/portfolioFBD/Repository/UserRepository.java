package com.portfolioFBD.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolioFBD.Entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
        
}
