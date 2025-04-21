package com.alexisrock.microservicioRest.recordatorio.application.recordatorio;

import com.alexisrock.microservicioRest.Common.BaseResponse;
import com.alexisrock.microservicioRest.Common.exceptions.ApiException;
import com.alexisrock.microservicioRest.recordatorio.application.validations.ValidatorUtil;
import com.alexisrock.microservicioRest.recordatorio.domain.entities.Recordatorio;
import com.alexisrock.microservicioRest.recordatorio.domain.interfaces.RecordatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.alexisrock.microservicioRest.recordatorio.application.Mappers.TransformEntities;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecordatorioServiceImpl implements RecordatorioService {


    @Autowired
    private RecordatorioRepository repository;

    @Override
    public BaseResponse create(RecordatorioRequest request) {
        try {
            ValidatorUtil.validate(request);
            BaseResponse response = new BaseResponse();
            Recordatorio recordatorio = new Recordatorio(request.getDescripcion(), request.getFechaRegistro(),request.getHora(),request.isCheckRecordatorio(),request.isEsRecurente(),request.isEstado(), request.getIdUsuario());
            repository.save(recordatorio);
            response.setMessage("Recordatorio creado con exito.");
            return response;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<RecordatorioResponse> getRecordatoriosById(Integer idUser) {
        try {
            List<RecordatorioResponse> recordatorioResponses = new ArrayList<>(){};
            List<Recordatorio>  recordatorios = repository.getRecordatorioByUserId(idUser);
            TransformEntities.getMapperListRecordatorioResponse(recordatorioResponses,recordatorios);
            return recordatorioResponses;
        }catch (Exception e){
            throw e;
        }
    }


    @Override
    public BaseResponse update(RecordatorioUpdateRequest request) {
        try {
            validateIdRecordatorioIsCero(request.getId());
            BaseResponse response = new BaseResponse();
            Optional<Recordatorio> optionalRecordatorio = repository.findById(request.getId());
            Recordatorio recordatorio = optionalRecordatorio.orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el recordatorio con Id: " + request.getId()));

            validateExitsRecordatorio(recordatorio);
            TransformEntities.getMaperRecordatorio(recordatorio, request);
            repository.save(recordatorio);
            response.setMessage("Recordatorio actualizado con exito.");
            return response;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public BaseResponse delete(Integer idRecordatorio){
        try {
            BaseResponse response = new BaseResponse();
            validateIdRecordatorioIsCero(idRecordatorio);
            Recordatorio recordatorio = repository.findById(idRecordatorio).get();
            validateExitsRecordatorio(recordatorio);
            repository.delete(recordatorio);
            response.setMessage("Recordatorio eliminado con exito.");
            return response;
        }catch (Exception e)
        {
            throw e;
        }
    }

    private void validateIdRecordatorioIsCero(Integer idRecordatorio){
        if (idRecordatorio==0)
            throw  new ApiException("El id del recordatorio no puede ser cero");
    }

    private void validateExitsRecordatorio(Recordatorio recordatorio){
        if (recordatorio== null)
            throw  new ApiException("El id del recordatorio no existe o ya fue eliminado");

    }
}
