package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.List;

public class Servicio {
private LocalDate fecha;
private String codigo;
private String motivo;
private String diagnostico;
private int trabajosRealizados;
private double costoTotal;
private Taller ownedByTaller;
private Mecanico mecanico;
private Cliente cliente;
private List<DetalleRepuesto> listaDetalleRepuesto;

    /**
     * Constructor con los atributos
     * @param fecha ingreso al servicio
     * @param codigo de servicio
     * @param motivo de servicio
     * @param diagnostico post servicio
     * @param trabajosRealizados hasta la fecha del servicio
     * @param ownedByTaller taller a quien pertenece el servicio
     * @param mecanico asignado al servicio
     * @param cliente a quien se le brinda el servicio
     * @param listaDetalleRepuesto utilizados en el servicio
     */
    public Servicio(LocalDate fecha, String codigo, String motivo, String diagnostico, int trabajosRealizados,
                    double costoTotal, Taller ownedByTaller, Mecanico mecanico, Cliente cliente, List<DetalleRepuesto> listaDetalleRepuesto) {
        this.fecha = fecha;
        this.codigo=codigo;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.trabajosRealizados = trabajosRealizados;
        this.costoTotal = costoTotal;
        this.ownedByTaller = ownedByTaller;
        this.mecanico = mecanico;
        this.cliente = cliente;        this.listaDetalleRepuesto = listaDetalleRepuesto;
    }

    /**
     * @return fecha de ingreso al servicio
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Metodo para actualizar la fecha de ingreso al servicio
     * @param fecha
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * @return codigo del servicio
     */

    public String getCodigo() {
        return codigo;
    }

    /**
     * Metodo para actualizar el codigo del servicio
     * @param codigo del servicio
     */

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return motivo de ingreso al servicio
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Metodo para actualizar el motivo de ingreso al servicio
     * @param motivo
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return diagnostico posterior al servicio
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Metodo para actualizar el diagnostico posterior al servicio
     * @param diagnostico
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * @return cantidad de trabajos realizados hasta la fecha
     */
    public int getTrabajosRealizados() {
        return trabajosRealizados;
    }

    /**
     * Metodo para actualizar cantidad de trabajos realizados
     * @param trabajosRealizados
     */
    public void setTrabajosRealizados(int trabajosRealizados) {
        this.trabajosRealizados = trabajosRealizados;
    }

    /**
     * @return costo total del servicio
     */
    public double getCostoTotal(double precioServicio) {
        double costoParcial= precioServicio + sumarSubtotal();
        double costoTotal= costoParcial - calcularDescuento(costoParcial);
        return costoTotal;
    }

    /**
     * Metodo para actualizar costo total del servicio
     * @param costoTotal
     */
    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    /**
     * @return taller a quien pertenece el servicio
     */
    public Taller getOwnedByTaller() {
        return ownedByTaller;
    }

    /**
     * Metodo para actualizar el taller a quien pertenece el servicio
     * @param ownedByTaller
     */
    public void setOwnedByTaller(Taller ownedByTaller) {
        this.ownedByTaller = ownedByTaller;
    }

    /**
     * @return mecanico asignado al servicio
     */
    public Mecanico getMecanico() {
        return mecanico;
    }

    /**
     * Metodo para actualizar el mecanico asignado al servicio
     * @param mecanico
     */
    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    /**
     * @return cliente a quien se le brinda el servicio
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Metodo para actualizar el cliente a quien se le brinda el servicio
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return la lista detallada de los repuestos empleados durante el servicio
     */
    public List<DetalleRepuesto> getListaDetalleRepuesto() {
        return listaDetalleRepuesto;
    }

    /**
     * Metodo para actualizar la lista detallada de los repuestos empleados durante el servicio
     * @param listaDetalleRepuesto
     */
    public void setListaDetalleRepuesto(List<DetalleRepuesto> listaDetalleRepuesto) {
        this.listaDetalleRepuesto = listaDetalleRepuesto;
    }

    /**
     * Metodo para obtener la suma de los subtotales de los diversos repuestos empleados
     * @return la suma total de los diversos subtotales de los repuestos empleados
     */
    public double sumarSubtotal(){
      double suma= 0;
        for(DetalleRepuesto detalleRepuesto: listaDetalleRepuesto){
            suma += detalleRepuesto.getSubtotal();
        }
        return suma;
    }

    /**
     * Metodo para calcular si se debe realizar un descuento
     * @param costoTotal del servicio como base del descuento
     * @return valor a aplicar descuento si cumple la condición
     */
    public double calcularDescuento(double costoTotal){
       double descuento=0;
        if (trabajosRealizados ==5){
            descuento= (costoTotal*0.25);
        }
        return  descuento;
    }
}
