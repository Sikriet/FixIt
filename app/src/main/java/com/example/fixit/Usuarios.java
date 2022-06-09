package com.example.fixit;

public class Usuarios {
    private String rut_usuario;
    private String nombre_usuario;
    private String apellido_usuarioo;
    private String email_usuario;
    private String telefono_usuario;
    private String direccion_usuario;
    private String password_usuario;
    private String password2_usuario;

    public Usuarios() {
    }

    public Usuarios(String rut_usuario, String nombre_usuario, String apellido_usuarioo, String email_usuario, String telefono_usuario, String direccion_usuario, String password_usuario, String password2_usuario) {
        this.rut_usuario = rut_usuario;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuarioo = apellido_usuarioo;
        this.email_usuario = email_usuario;
        this.telefono_usuario = telefono_usuario;
        this.direccion_usuario = direccion_usuario;
        this.password_usuario = password_usuario;
        this.password2_usuario = password2_usuario;
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

    public String getApellido_usuarioo() {
        return apellido_usuarioo;
    }

    public void setApellido_usuarioo(String apellido_usuarioo) {
        this.apellido_usuarioo = apellido_usuarioo;
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

    public String getPassword_usuario() {
        return password_usuario;
    }

    public void setPassword_usuario(String password_usuario) {
        this.password_usuario = password_usuario;
    }

    public String getpassword2_usuario() {
        return password2_usuario;
    }

    public void setPassword2_usuario(String password2_usuario) {
        this.password2_usuario = password2_usuario;
    }
}
