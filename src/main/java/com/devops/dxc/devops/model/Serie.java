package com.devops.dxc.devops.model;

public class Serie {

    private String fecha;
    private int valor;

    public String getFecha(){
        return fecha;
    }
    public void setFecha(String input){
        this.fecha = input;
    }
    public int getValor(){
        return valor;
    }
    public void setValor(int input){
        this.valor = input;
    }
}
