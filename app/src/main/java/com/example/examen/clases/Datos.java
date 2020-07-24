package com.example.examen.clases;

public class Datos {
    String nombre;
    String imagen;

    public Datos(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
