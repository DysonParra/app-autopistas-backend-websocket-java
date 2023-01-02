/*
 * @fileoverview {DynamicDisplayCommandProcessor} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {DynamicDisplayCommandProcessor} fue realizada el 31/07/2022.
 * @Dev - La primera version de {DynamicDisplayCommandProcessor} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.websocket.commandprocessor;

import com.rtc.cardinal.websocket.model.Station;
import com.rtc.cardinal.websocket.model.element.DisplayMessageElement;
import com.rtc.cardinal.websocket.peripheric.Peripheric;
import java.util.LinkedList;
import java.util.Queue;
import lombok.NonNull;

/**
 * TODO: Definición de {@code DynamicDisplayCommandProcessor}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class DynamicDisplayCommandProcessor implements GenericCommandProcessor<Boolean> {

    protected final Queue<String> dynamicDisplayQueue = new LinkedList<>();

    /**
     * TODO: Definición de {@code processCommand}.
     *
     * @param plcCommand
     * @param station
     * @param sender
     * @return
     */
    @Override
    public Boolean processCommand(String plcCommand, @NonNull String sender, @NonNull Station station) {
        String message = null;
        Integer dynamicWeight;
        System.out.println("Validating code (dynamic display): '" + plcCommand + "'");
        switch (plcCommand) {
            case "B40":
                dynamicWeight = 0;
                try {
                    dynamicWeight = (Integer) station.getPeriphericInfo(Station.ID_DYNAMIC_WEIGHT + 1, Peripheric.GET_LAST_WEIGHT);
                } catch (ClassCastException | NullPointerException e) {
                }
                message = (dynamicWeight != null && dynamicWeight != 0) ? "A VIA NACIONAL <-- " + dynamicWeight + " KG" : "A VIA NACIONAL";
                break;
            case "B41":
                dynamicWeight = 0;
                try {
                    dynamicWeight = (Integer) station.getPeriphericInfo(Station.ID_DYNAMIC_WEIGHT + 1, Peripheric.GET_LAST_WEIGHT);
                } catch (ClassCastException | NullPointerException e) {
                }
                message = (dynamicWeight != null && dynamicWeight != 0) ? dynamicWeight + " KG" + " --> A BASCULA ESTATICA" : "A BASCULA ESTATICA";
                break;
            case "411":
            case "511":
                message = "";
                break;

            case "DESENCOLAR":
                message = dynamicDisplayQueue.poll();
                break;
        }

        if (message != null) {
            if (!(plcCommand.equals("B40") || plcCommand.equals("B41"))) {
                station.sendInfoToPeripheric(Station.ID_DYNAMIC_DISPLAY + 1,
                        DisplayMessageElement.builder()
                                .message(message)
                                .sender(sender + ":" + plcCommand)
                                .build());
            } else {
                dynamicDisplayQueue.add(message);
                System.out.println("PROCESSING B40 or B41");
                processCommand("DESENCOLAR", sender, station);
                System.out.println("PROCESSED B40 or B41");
            }
            System.out.println("Found code (dynamic display): '" + plcCommand + "'");
            return true;
        } else {
            System.out.println("Not found code (dynamic display): '" + plcCommand + "'");
            return false;
        }
    }
}