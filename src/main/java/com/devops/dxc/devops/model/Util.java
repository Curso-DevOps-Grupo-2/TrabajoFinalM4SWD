package com.devops.dxc.devops.model;

import com.devops.dxc.devops.rest.RestData;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class Util {

    private final static Logger LOGGER = Logger.getLogger(Util.class.getName());
    /**
     * Método para cacular el 10% del ahorro en la AFP.  Las reglas de negocio se pueden conocer en 
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     * 
     * @param ahorro
     * @param sueldo
     * @return
     */
    public static int getDxc(int ahorro, int sueldo){
        if(((ahorro*0.1)/getUf()) > 150 ){
            return (int) (150*getUf()) ;
        } else if((ahorro*0.1)<=1000000 && ahorro >=1000000){
            return (int) 1000000;
        } else if( ahorro <=1000000){
            return (int) ahorro;
        } else {
            return (int) (ahorro*0.1);
        }
    }

    /**
     * Método que retorna el valor de la UF.  Este método debe ser refactorizado por una integración a un servicio
     * que retorne la UF en tiempo real.  Por ejemplo mindicador.cl
     * @return
     */
    public static int getUf(){

       /* Client client = ClientBuilder.newClient();
        LOGGER.info(String.valueOf(new Date()));
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        LOGGER.info(formato.format(new Date()));
        String respuesta = client.target("https://mindicador.cl/api/uf/" + new Date()).request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(String.class);*/
        return 29000;
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
        } else if ((53000001 < sueldoAnual) && (sueldoAnual < 71400000)) {
            return 6;
        } else if ((71000001 < sueldoAnual)) {
            return 7;
        }
        return 1;
    }
}
