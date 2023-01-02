/*
 * @fileoverview {VehiculoSobrepeso} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {VehiculoSobrepeso} fue realizada el 31/07/2022.
 * @Dev - La primera version de {VehiculoSobrepeso} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.definicion.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Definición de {@code VehiculoSobrepeso}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@Entity
//@Table(name = "VehiculoSobrepeso", schema = "cardinal", catalog = "cardinal")
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class VehiculoSobrepeso implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long intIdRepeso;
    private Integer intPesoMaximo;
    private Integer intDiferenciaPeso;
    @JoinColumn(name = "intIdDinamica", referencedColumnName = "intIdDinamica")
    private Long intIdDinamica;
    private String strPlacaVehiculo;
    private Boolean bitBorrado;

}