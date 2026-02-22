package co.edu.uniquindio.poo.model;

import java.util.List;

public class Mecanico extends Persona{
    private int sueldo;
    private String noCertificado;
    private TipoEspecialidad tipoEspecialidad;
    private List<Servicio> listaServicios;

    /**
     * constructor con atributos
     * @param nombre del mecanico
     * @param tel del mecanico
     * @param direccion del mecanico
     * @param id del mecanico
     * @param taller
     * @param listaServicios del mecanico
     * @param tipoEspecialidad del mecanico
     * @param sueldo del mecanico
     * @param noCertificado del mecanico
     */

    public Mecanico(String nombre, String tel, String direccion, String id, Taller taller, List<Servicio> listaServicios, TipoEspecialidad tipoEspecialidad, int sueldo, String noCertificado) {
        super(nombre, tel, direccion, id, taller);
        this.listaServicios = listaServicios;
        this.sueldo=sueldo;
        this.noCertificado=noCertificado;
        this.tipoEspecialidad=tipoEspecialidad;
    }

    /**
     * @return la lista de servicios del mecanico
     */

    public List<Servicio> getListaServicios() {
        return listaServicios;
    }

    /**
     * Metodo para actualizae la lista de servicios del mecanico
     * @param listaServicios del mecanico
     */

    public void setListaServicios(List<Servicio> listaServicios) {
        this.listaServicios = listaServicios;
    }

    /**
     * @return el noCertificado del mecanico
     */

    public String getNoCertificado() {
        return noCertificado;
    }

    /**
     * Metodo para actualizar el noCertificado del mecanico
     * @param noCertificado del mecanico
     */

    public void setNoCertificado(String noCertificado) {
        this.noCertificado = noCertificado;
    }

    /**
     * @return del sueldo del mecanico
     */

    public int getSueldo() {
        return sueldo;
    }

    /**
     * Metodo para actualizar el sueldo del mecanico
     * @param sueldo del mecanico
     */

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * @return el tipo de especialidad del mecanico
     */

    public TipoEspecialidad getTipoEspecialidad() {
        return tipoEspecialidad;
    }

    /**
     * Metodo para actualizar el tipo de especialidad del mecanico
     * @param tipoEspecialidad del mecanico
     */

    public void setTipoEspecialidad(TipoEspecialidad tipoEspecialidad) {
        this.tipoEspecialidad = tipoEspecialidad;
    }
}
