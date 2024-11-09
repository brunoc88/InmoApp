package com.softulp.imnoapp.model;

public class Tipo_inmueble {
    private int id_tipoInmueble;
    private String tipoNombre;
    private boolean estado;

    public Tipo_inmueble(int id_tipoInmueble, String tipoNombre, boolean estado) {
        this.id_tipoInmueble = id_tipoInmueble;
        this.tipoNombre = tipoNombre;
        this.estado = estado;
    }

    public int getId_tipoInmueble() {
        return id_tipoInmueble;
    }

    public void setId_tipoInmueble(int id_tipoInmueble) {
        this.id_tipoInmueble = id_tipoInmueble;
    }

    public String getTipoNombre() {
        return tipoNombre;
    }

    public void setTipoNombre(String tipoNombre) {
        this.tipoNombre = tipoNombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


}
