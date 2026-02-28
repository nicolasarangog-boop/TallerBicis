package co.edu.uniquindio.poo.model;

import java.util.List;

public class Repuesto {
    private String nombre;
    private String codigo;
    private int stockDisponible;
    private Taller ownedByTaller;
    private List<DetalleRepuesto> listaDetalleRepuesto;

    /**
     * Constructor con los atributos
     * @param nombre del repuesto
     * @param codigo del repuesto
     * @param stockDisponible del repuesto
     * @param ownedByTaller asociado al repuesto
     * @param listaDetalleRepuesto
     */
    public Repuesto(String nombre, String codigo, int stockDisponible, Taller ownedByTaller,
                    List<DetalleRepuesto> listaDetalleRepuesto) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.stockDisponible = stockDisponible;
        this.ownedByTaller = ownedByTaller;
        this.listaDetalleRepuesto = listaDetalleRepuesto;
    }

    /**
     * @return nombre del repuesto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para actualizar el nombre del repuesto
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return codigo del repuesto
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Metodo para actualizar el codigo del repuesto
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return stock disponible del repuesto
     */
    public int getStockDisponible() {
        return stockDisponible;
    }

    /**
     * Metodo para actualizar el stock disponible del repuesto
     * @param stockDisponible
     */
    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    /**
     * @return el taller asociado
     */
    public Taller getOwnedByTaller() {
        return ownedByTaller;
    }

    /**
     * Metodo para actualizar el taller asociado
     * @param ownedByTaller
     */
    public void setOwnedByTaller(Taller ownedByTaller) {
        this.ownedByTaller = ownedByTaller;
    }

    /**
     * @return la lista de detalles del repuesto
     */
    public List<DetalleRepuesto> getListaDetalleRepuesto() {
        return listaDetalleRepuesto;
    }

    /**
     * Metodo para actualizar la lista de detalles del repuesto
     * @param listaDetalleRepuesto
     */
    public void setListaDetalleRepuesto(List<DetalleRepuesto> listaDetalleRepuesto) {
        this.listaDetalleRepuesto = listaDetalleRepuesto;
    }
}
