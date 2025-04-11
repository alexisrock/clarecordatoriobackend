package com.alexisrock.microservicioRest.login.domain.interfaces;

import com.alexisrock.microservicioRest.login.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Users,Integer> {

    @Query("SELECT u FROM Users u WHERE u.Email = :email")
    Users getUserByEmail(@Param("email") String email);

}
