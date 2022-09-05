/*
 * @fileoverview {FileName} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {FileName} fue realizada el 31/07/2022.
 * @Dev - La primera version de {FileName} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.definicion.repositorio;

import com.rtc.cardinal.definicion.dominio.TransitoDinamica;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * TODO: Definición de {@code RepositorioTransitoDinamica}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@Repository
public interface RepositorioTransitoDinamica extends JpaRepository<TransitoDinamica, Long> {

    public List<TransitoDinamica> findByIntIdDinamica(Long id);

    public TransitoDinamica findByStrPlacaVehiculo(String id);

    @Query("SELECT m FROM TransitoDinamica m WHERE m.intIdDinamica LIKE CONCAT('%', :strBusqueda, '%')")
    public Page<TransitoDinamica> buscarEntidades(@Param("strBusqueda") String strBusqueda, Pageable pageable);
}
