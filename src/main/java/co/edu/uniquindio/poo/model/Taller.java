package co.edu.uniquindio.poo.model;

import java.util.List;

public class Taller {
    private String nombre;
    private String tel;
    private String direccion;
    private String id;
    private List<Servicio> listaServicios;
    private List<Persona> listaPersonas;
    private List<Bicicleta> listaBicicleta;
    private List<Repuesto> listaRepuestos;

    /**
     * constructor con los atributos
     * @param nombre del taller
     * @param tel del taller
     * @param direccion del taller
     * @param id del taller
     * @param listaServicios del taller
     * @param listaPersonas del taller
     * @param listaBicicleta del taller
     * @param listaRepuestos del taller
     */
    public Taller (String nombre, String tel, String direccion, String id,List<Servicio> listaServicios,List<Persona> listaPersonas,
                   List<Bicicleta> listaBicicleta,List<Repuesto> listaRepuestos ) {
        this.nombre=nombre;
        this.tel=tel;
        this.direccion=direccion;
        this.id=id;
        this.listaRepuestos=listaRepuestos;
        this.listaBicicleta=listaBicicleta;
        this.listaPersonas=listaPersonas;
        this.listaServicios=listaServicios;
    }

    /**
     * @return telefono del taller
     */

    public String getTel() {
    return tel;
    }

    /**
     * Metodo para actualizar el telefono del taller
     * @param tel del taller
     */

    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return nombre del taller
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para actuaalizar el nombre del taller
     * @param nombre del taller
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return la direccion del taller
     */

    public String getDireccion() {
        return direccion;
    }

    /**
     * Metodo para actualizar la direccion del taller
     * @param direccion del taller
     */

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return del id del taller
     */

    public String getId() {
        return id;
    }

    /**
     * Metodo para actualizar el id del taller
     * @param id del taller
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return la lista de servicios del taller
     */

    public List<Servicio> getListaServicios() {
        return listaServicios;
    }

    /**
     * Metodo para actualizar la lista de servicios del taller
     * @param listaServicios del taller
     */

    public void setListaServicios(List<Servicio> listaServicios) {
        this.listaServicios = listaServicios;
    }

    /**
     * @return lista de las personas del taller
     */

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    /**
     * Metodo para actualizar la lista de personas del taller
     * @param listaPersonas del taller
     */

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    /**
     * @return de la lista de bicicletas del taller
     */

    public List<Bicicleta> getListaBicicleta() {
        return listaBicicleta;
    }

    /**
     * Metodo para actualizar la lista de bicicletas del taller
     * @param listaBicicleta del taller
     */

    public void setListaBicicleta(List<Bicicleta> listaBicicleta) {
        this.listaBicicleta = listaBicicleta;
    }

    /**
     * @return la lista de repuestos del taller
     */

    public List<Repuesto> getListaRepuestos() {
        return listaRepuestos;
    }

    /**
     * Metodo para actualizar la lista de repuestos del taller
     * @param listaRepuestos del taller
     */
    public void setListaRepuestos(List<Repuesto> listaRepuestos) {
        this.listaRepuestos = listaRepuestos;
    }
}
