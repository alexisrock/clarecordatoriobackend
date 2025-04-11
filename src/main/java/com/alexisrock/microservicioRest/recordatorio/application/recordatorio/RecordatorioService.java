package com.alexisrock.microservicioRest.recordatorio.application.recordatorio;

import com.alexisrock.microservicioRest.Common.BaseResponse;

import java.util.List;

public interface RecordatorioService {


    BaseResponse create(RecordatorioRequest request);

    List<RecordatorioResponse> getRecordatoriosById(Integer idUser);

    BaseResponse update(RecordatorioUpdateRequest request);

    BaseResponse delete(Integer idRecordatorio);
}
