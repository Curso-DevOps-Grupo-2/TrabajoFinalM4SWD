package com.devops.dxc.devops.model;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Util {

    private final static Logger LOGGER = Logger.getLogger(Util.class.getName());

    /**
     * Método para cacular el 10% del ahorro en la AFP.  Las reglas de negocio se pueden conocer en 
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     * 
     * @param ahorro
     * @return
     */
    public static void getDxc(int ahorro, Dxc response) {
        if(((ahorro*0.1)/getUf()) > 150 ){
            response.setDxc((150*getUf()));
        } else if((ahorro*0.1)<=1000000 && ahorro >=1000000){
            response.setDxc(1000000);
        } else if( ahorro <=1000000){
            response.setDxc(ahorro);
        } else {
            response.setDxc((int) (ahorro*0.1));
        }
    }

    /**
     * Método que retorna el valor de la UF.  Este método debe ser refactorizado por una integración a un servicio
     * que retorne la UF en tiempo real.  Por ejemplo mindicador.cl
     * @return
     */
    public static int getUf() {

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {

            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<ValorUf> response = restTemplate.getForEntity("https://mindicador.cl/api/uf/" + formato.format(new Date()), ValorUf.class);
            ValorUf valorUf = response.getBody();
            List<Serie> listSerie = valorUf.getSerie();
            LOGGER.info("El valor de la UF es: $" + listSerie.get(0).getValor());
            return listSerie.get(0).getValor();

        } catch (Exception e) {
            LOGGER.info("No ha sido posible obtener el valor de la uf, se considera valor aproximado de $30.000");
        }
        return 30000;
    }

    public static void calcularSaldo(int ahorro, int sueldo, Dxc response) {
        response.setSaldo(ahorro - response.getDxc());
    }

    public static void calcularImpuesto(int sueldo, Dxc response) {

        int tramo = calcularTramo(sueldo * 12);
        switch (tramo){
            case 3:
                response.setImpuesto((int) (response.getDxc()*0.08));
                break;
            case 4:
                response.setImpuesto((int) (response.getDxc()*0.135));
                break;
            case 5:
                response.setImpuesto((int) (response.getDxc()*0.23));
                break;
            case 6:
                response.setImpuesto((int) (response.getDxc()*0.304));
                break;
            case 7:
                response.setImpuesto((int) (response.getDxc()*0.35));
                break;
            default:
                response.setImpuesto(0);
        }
    }

    private static int calcularTramo(int sueldoAnual) {
        if ((17864280 < sueldoAnual) && (sueldoAnual < 29773800)) {
            return 3;
        } else if ((29773801 < sueldoAnual) && (sueldoAnual < 41600000)) {
            return 4;
        } else if ((41600001 < sueldoAnual) && (sueldoAnual < 53500000)) {
            return 5;
        } else if ((53500001 < sueldoAnual) && (sueldoAnual < 71400000)) {
            return 6;
        } else if ((71400001 < sueldoAnual)) {
            return 7;
        }
        return 1;
    }
}
