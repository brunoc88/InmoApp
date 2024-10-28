package com.softulp.imnoapp.model;

public class Propietario {
    private int id_propietario;
    private String nombre;
    private String apellido;
    private String email;
    private String clave;
    private String dni;
    private String telefono;

    // Constructor vacío
    public Propietario() { }
    // Constructor con parámetros
    public Propietario(int id_propietario, String nombre, String apellido, String email, String clave, String dni, String telefono) {
        this.id_propietario = id_propietario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.clave = clave;
        this.dni = dni;
        this.telefono = telefono;
    }

    // Métodos getter y setter
    public int getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(int id_propietario) {
        this.id_propietario = id_propietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}


