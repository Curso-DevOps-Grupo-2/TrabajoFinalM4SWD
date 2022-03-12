package com.devops.dxc.devops.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Util;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest/msdxc")
public class RestData {

    private final static Logger LOGGER = Logger.getLogger(RestData.class.getName());

    @GetMapping(path = "/calcula-10", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Dxc getCalcula10(@RequestParam(name = "sueldo") int sueldo, @RequestParam(name = "ahorro") int ahorro) {

        LOGGER.log(Level.INFO, "Se inicia calculo del 10%");

        Dxc response = new Dxc(ahorro, sueldo);
        Util.calcularSaldo(ahorro, sueldo, response);
        Util.calcularImpuesto(sueldo, response);

        return response;
    }

}