package com.example.fixit;

public class UserClass {
    private String rut_usuario;
    private String nombre_usuario;
    private String apellido_usuario;
    private String email_usuario;
    private String telefono_usuario;
    private String direccion_usuario;
    private String contrasena_usuario;
    private String vehiculo_usuario;
    private String fecha_creacion;
    private String estado_usuario;
    private String img_profileURL;

    public UserClass() {
        this.rut_usuario = rut_usuario;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.email_usuario = email_usuario;
        this.telefono_usuario = telefono_usuario;
        this.direccion_usuario = direccion_usuario;
        this.contrasena_usuario = contrasena_usuario;
        this.vehiculo_usuario = vehiculo_usuario;
        this.fecha_creacion = fecha_creacion;
        this.estado_usuario = estado_usuario;
        this.img_profileURL = img_profileURL;
    }

    public UserClass(String rut_usuario, String nombre_usuario, String apellido_usuario, String email_usuario, String telefono_usuario, String direccion_usuario, String contrasena_usuario, String vehiculo_usuario, String fecha_creacion, String estado_usuario, String img_profileURL) {
        this.rut_usuario = rut_usuario;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.email_usuario = email_usuario;
        this.telefono_usuario = telefono_usuario;
        this.direccion_usuario = direccion_usuario;
        this.contrasena_usuario = contrasena_usuario;
        this.vehiculo_usuario = vehiculo_usuario;
        this.fecha_creacion = fecha_creacion;
        this.estado_usuario = estado_usuario;
        this.img_profileURL = img_profileURL;
    }

    @Override
    public String toString() {
        return "UserClass{" +
                "rut_usuario='" + rut_usuario + '\'' +
                ", nombre_usuario='" + nombre_usuario + '\'' +
                ", apellido_usuario='" + apellido_usuario + '\'' +
                ", email_usuario='" + email_usuario + '\'' +
                ", telefono_usuario='" + telefono_usuario + '\'' +
                ", direccion_usuario='" + direccion_usuario + '\'' +
                ", contrasena_usuario='" + contrasena_usuario + '\'' +
                ", vehiculo_usuario='" + vehiculo_usuario + '\'' +
                ", fecha_creacion='" + fecha_creacion + '\'' +
                ", estado_usuario='" + estado_usuario + '\'' +
                ", img_profileURL='" + img_profileURL + '\'' +
                '}';
    }

    public String getRut_usuario() {
        return rut_usuario;
    }

    public void setRut_usuario(String rut_usuario) {
        this.rut_usuario = rut_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getTelefono_usuario() {
        return telefono_usuario;
    }

    public void setTelefono_usuario(String telefono_usuario) {
        this.telefono_usuario = telefono_usuario;
    }

    public String getDireccion_usuario() {
        return direccion_usuario;
    }

    public void setDireccion_usuario(String direccion_usuario) {
        this.direccion_usuario = direccion_usuario;
    }

    public String getContrasena_usuario() {
        return contrasena_usuario;
    }

    public void setContrasena_usuario(String contrasena_usuario) {
        this.contrasena_usuario = contrasena_usuario;
    }

    public String getVehiculo_usuario() {
        return vehiculo_usuario;
    }

    public void setVehiculo_usuario(String vehiculo_usuario) {
        this.vehiculo_usuario = vehiculo_usuario;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getEstado_usuario() {
        return estado_usuario;
    }

    public void setEstado_usuario(String estado_usuario) {
        this.estado_usuario = estado_usuario;
    }

    public String getImg_profileURL() {
        return img_profileURL;
    }

    public void setImg_profileURL(String img_profileURL) {
        this.img_profileURL = img_profileURL;
    }
}
