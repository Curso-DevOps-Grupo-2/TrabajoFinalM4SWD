package com.devops.dxc.devops.model;

import java.util.List;

public class ValorUf {

    String version;
    String autor;
    String codigo;
    String nombre;
    String unidad_medida;
    List<Serie> serie ;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public List<Serie> getSerie() {
        return serie;
    }

    public void setSerie(List<Serie> serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ValorUf{");
        sb.append("version='").append(version).append('\'');
        sb.append(", autor='").append(autor).append('\'');
        sb.append(", codigo='").append(codigo).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", unidad_medida='").append(unidad_medida).append('\'');
        sb.append(", listSerie=").append(serie);
        sb.append('}');
        return sb.toString();
    }
}
