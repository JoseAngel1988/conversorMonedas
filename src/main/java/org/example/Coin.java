package org.example;

public class Coin {
    private String codigo;
    private String nombre;

    public Coin(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }
}
