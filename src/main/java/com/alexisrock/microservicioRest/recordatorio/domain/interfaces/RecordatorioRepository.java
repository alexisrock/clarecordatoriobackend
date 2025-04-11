package com.alexisrock.microservicioRest.recordatorio.domain.interfaces;

import com.alexisrock.microservicioRest.recordatorio.domain.entities.Recordatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordatorioRepository extends JpaRepository<Recordatorio,Integer> {

    @Query("SELECT r FROM Recordatorio r WHERE r.Idusuario = :idUser")
    List<Recordatorio> getRecordatorioByUserId(@Param("idUser") Integer idUser);


}
