package com.portfolioFBD.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolioFBD.Entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
        
}
