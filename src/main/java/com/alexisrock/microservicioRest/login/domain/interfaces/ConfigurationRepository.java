package com.alexisrock.microservicioRest.login.domain.interfaces;
import com.alexisrock.microservicioRest.login.domain.entities.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration,String> {


    @Query("SELECT c FROM Configuration c WHERE c.Id = :id")
    Configuration getValuesConfigurationsById(@Param("id") String Id);
}
