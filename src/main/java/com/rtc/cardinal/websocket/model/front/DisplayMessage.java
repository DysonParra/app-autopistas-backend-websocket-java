/*
 * @fileoverview {DisplayMessage} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {DisplayMessage} fue realizada el 31/07/2022.
 * @Dev - La primera version de {DisplayMessage} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.websocket.model.front;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Definición de {@code DisplayMessage}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DisplayMessage implements FrontMessage {

    private final String messageType = "DISPLAY";
    private String idDisplay;
    private String message;
    private String tipoDisplay;

}