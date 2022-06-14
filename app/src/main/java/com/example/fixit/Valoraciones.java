package com.example.fixit;

public class Valoraciones {

    private String id_valoracion;
    private String calificacion_valoracion;
    private String id_pyme;
    private String rut_usuario;
    private String comentario_valoracion;
    private String nombre_pyme;
    private String nombre_usuario;
    private String apellido_usuario;

    public Valoraciones() {
        this.id_valoracion = id_valoracion;
        this.calificacion_valoracion = calificacion_valoracion;
        this.id_pyme = id_pyme;
        this.rut_usuario = rut_usuario;
        this.comentario_valoracion = comentario_valoracion;
        this.nombre_pyme = nombre_pyme;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
    }

    public Valoraciones(String id_valoracion, String calificacion_valoracion, String id_pyme, String rut_usuario, String comentario_valoracion, String nombre_pyme, String nombre_usuario, String apellido_usuario) {
        this.id_valoracion = id_valoracion;
        this.calificacion_valoracion = calificacion_valoracion;
        this.id_pyme = id_pyme;
        this.rut_usuario = rut_usuario;
        this.comentario_valoracion = comentario_valoracion;
        this.nombre_pyme = nombre_pyme;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
    }

    @Override
    public String toString() {
        return "Comentarios{" +
                "id_valoracion='" + id_valoracion + '\'' +
                ", calificacion_valoracion='" + calificacion_valoracion + '\'' +
                ", id_pyme='" + id_pyme + '\'' +
                ", rut_usuario='" + rut_usuario + '\'' +
                ", comentario_valoracion='" + comentario_valoracion + '\'' +
                ", nombre_pyme='" + nombre_pyme + '\'' +
                ", nombre_usuario='" + nombre_usuario + '\'' +
                ", apellido_usuario='" + apellido_usuario + '\'' +
                '}';
    }

    public String getId_valoracion() {
        return id_valoracion;
    }

    public void setId_valoracion(String id_valoracion) {
        this.id_valoracion = id_valoracion;
    }

    public String getCalificacion_valoracion() {
        return calificacion_valoracion;
    }

    public void setCalificacion_valoracion(String calificacion_valoracion) {
        this.calificacion_valoracion = calificacion_valoracion;
    }

    public String getId_pyme() {
        return id_pyme;
    }

    public void setId_pyme(String id_pyme) {
        this.id_pyme = id_pyme;
    }

    public String getRut_usuario() {
        return rut_usuario;
    }

    public void setRut_usuario(String rut_usuario) {
        this.rut_usuario = rut_usuario;
    }

    public String getComentario_valoracion() {
        return comentario_valoracion;
    }

    public void setComentario_valoracion(String comentario_valoracion) {
        this.comentario_valoracion = comentario_valoracion;
    }

    public String getNombre_pyme() {
        return nombre_pyme;
    }

    public void setNombre_pyme(String nombre_pyme) {
        this.nombre_pyme = nombre_pyme;
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
}
