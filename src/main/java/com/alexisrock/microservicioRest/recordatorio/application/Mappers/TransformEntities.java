package com.alexisrock.microservicioRest.recordatorio.application.Mappers;

import com.alexisrock.microservicioRest.recordatorio.application.recordatorio.RecordatorioResponse;
import com.alexisrock.microservicioRest.recordatorio.application.recordatorio.RecordatorioUpdateRequest;
import com.alexisrock.microservicioRest.recordatorio.domain.entities.Recordatorio;

import java.util.List;

public class TransformEntities {






    public static List<RecordatorioResponse> getMapperListRecordatorioResponse( List<RecordatorioResponse> recordatorioResponses, List<Recordatorio> recordatorioList){

        if (!recordatorioList.isEmpty()){
            recordatorioList.forEach(x-> {
                RecordatorioResponse response = new RecordatorioResponse(x.getId(), x.getDescripcion(), x.getFecharegistro(), x.getHora(), x.isCheckrecordatorio(), x.isEsrecurente(), x.isEstado(), x.getIdusuario());
                recordatorioResponses.add(response);
            });
        }
        return recordatorioResponses;
    }


    public static Recordatorio getMaperRecordatorio(Recordatorio recordatorio, RecordatorioUpdateRequest request){

       if (request.isCheckRecordatorio() != null)
            recordatorio.setCheckrecordatorio(request.isCheckRecordatorio());
       if (request.isEsRecurente()!= null)
           recordatorio.setEsrecurente(request.isEsRecurente());
       if (request.isEstado()!= null)
           recordatorio.setEstado(request.isEstado());

        return recordatorio;
    }
}
