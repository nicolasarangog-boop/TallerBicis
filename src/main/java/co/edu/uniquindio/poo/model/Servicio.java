package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.List;

public class Servicio {
private LocalDate fecha;
private String motivo;
private String diagnostico;
private int trabajosRealizados;
private int costoTotal;
private Taller ownedByTaller;
private Mecanico mecanico;
private Cliente cliente;
private List<DetalleRepuesto> listaDetalleRepuesto;

    /**
     * Constructor con los atributos
     * @param fecha ingreso al servicio
     * @param motivo de servicio
     * @param diagnostico post servicio
     * @param trabajosRealizados hasta la fecha del servicio
     * @param costoTotal del servicio
     * @param ownedByTaller taller a quien pertenece el servicio
     * @param mecanico asignado al servicio
     * @param cliente a quien se le brinda el servicio
     * @param listaDetalleRepuesto utilizados en el servicio
     */
    public Servicio(LocalDate fecha, String motivo, String diagnostico, int trabajosRealizados, int costoTotal,
                    Taller ownedByTaller, Mecanico mecanico, Cliente cliente, List<DetalleRepuesto> listaDetalleRepuesto) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.trabajosRealizados = trabajosRealizados;
        this.costoTotal = costoTotal;
        this.ownedByTaller = ownedByTaller;
        this.mecanico = mecanico;
        this.cliente = cliente;
        this.listaDetalleRepuesto = listaDetalleRepuesto;
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
    public int getCostoTotal() {
        return costoTotal;
    }

    /**
     * Metodo para actualizar el costo total del servicio
     * @param costoTotal
     */
    public void setCostoTotal(int costoTotal) {
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
}
