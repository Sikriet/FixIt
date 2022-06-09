package com.example.fixit;

public class Producto {
    private String id_producto;
    private String nombre_producto;
    private String precio_producto;
    private String cantidad_producto;
    private String id_pyme;

    public Producto() {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.precio_producto = precio_producto;
        this.cantidad_producto = cantidad_producto;
        this.id_pyme = id_pyme;
    }

    public Producto(String id_producto, String nombre_producto, String precio_producto, String cantidad_producto, String id_pyme) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.precio_producto = precio_producto;
        this.cantidad_producto = cantidad_producto;
        this.id_pyme = id_pyme;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id_producto='" + id_producto + '\'' +
                ", nombre_producto='" + nombre_producto + '\'' +
                ", precio_producto='" + precio_producto + '\'' +
                ", cantidad_producto='" + cantidad_producto + '\'' +
                ", id_pyme='" + id_pyme + '\'' +
                '}';
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(String precio_producto) {
        this.precio_producto = precio_producto;
    }

    public String getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(String cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public String getId_pyme() {
        return id_pyme;
    }

    public void setId_pyme(String id_pyme) {
        this.id_pyme = id_pyme;
    }
}
