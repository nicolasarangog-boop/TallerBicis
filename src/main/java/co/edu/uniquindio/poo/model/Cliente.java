package co.edu.uniquindio.poo.model;

import java.util.List;

public class Cliente extends Persona {
    private List<Bicicleta> listaBicicletas;
    private List<Servicio> listaServicios;

    /**
     * constructor con atributos
     * @param nombre del cliente
     * @param tel del cliente
     * @param direccion del cliente
     * @param id del cliente
     * @param taller
     * @param listaBicicletas del cliente
     * @param listaServicios del cliente
     */
    public Cliente(String nombre, String tel, String direccion, String id, Taller taller, List<Bicicleta> listaBicicletas,
                   List<Servicio> listaServicios) {
        super(nombre, tel, direccion, id, taller);
        this.listaBicicletas = listaBicicletas;
        this.listaServicios=listaServicios;
    }

    /**
     * @return la lista de bicicletas del cliente
     */
    public List<Bicicleta> getListaBicicletas() {
        return listaBicicletas;
    }

    /**
     * Metodo para actualizar la lista de bicicletas del cliente
     * @param listaBicicletas del cliente
     */

    public void setListaBicicletas(List<Bicicleta> listaBicicletas) {
        this.listaBicicletas = listaBicicletas;
    }

    /**
     * @return la lista de servicios del cliente
     */

    public List<Servicio> getListaServicios() {
        return listaServicios;
    }

    /**
     * Metodo para actualizar la lista de servicios del cliente
     * @param listaServicios del cliente
     */

    public void setListaServicios(List<Servicio> listaServicios) {
        this.listaServicios = listaServicios;
    }
}
