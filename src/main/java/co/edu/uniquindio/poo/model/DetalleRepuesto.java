package co.edu.uniquindio.poo.model;

public class DetalleRepuesto {
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private Repuesto ownedByRepuesto;
    private Servicio servicio;

    /**
     * Constructor con los atributos
     * @param cantidad del repuesto
     * @param precioUnitario del repuesto
     * @param subtotal del repuesto
     * @param ownedByRepuesto propietario de los detalles
     * @param servicio en el que emplearon los repuestos señalados
     */
    public DetalleRepuesto(int cantidad, double precioUnitario, double subtotal, Repuesto ownedByRepuesto,
                           Servicio servicio) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.ownedByRepuesto = ownedByRepuesto;
        this.servicio = servicio;
    }

    /**
     * @return cantidad empleada de un repuesto
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Metodo para actualizar la cantidad empleada de un repuesto
     * @param cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return precio unitario de un repuesto
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Metodo para actualizar el precio unitario de un repuesto
     * @param precioUnitario
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * @return el subtotal de un repuesto
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Metodo para actualizar el subtotal de un repuesto
     * @param subtotal
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return el repuesto a que hace referencia el detalle
     */
    public Repuesto getOwnedByRepuesto() {
        return ownedByRepuesto;
    }

    /**
     * Metodo para actualizar el repuesto a que hace referencia el detalle
     * @param ownedByRepuesto
     */
    public void setOwnedByRepuesto(Repuesto ownedByRepuesto) {
        this.ownedByRepuesto = ownedByRepuesto;
    }

    /**
     * @return el servicio en el cual fue empleado el detalle del repuesto
     */
    public Servicio getServicio() {
        return servicio;
    }

    /**
     * Metodo para actualizar el servicio en el cual fue empleado el detalle del repuesto
     * @param servicio
     */
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public double calcularSubtotal(){
        return cantidad * precioUnitario;
    }
}


