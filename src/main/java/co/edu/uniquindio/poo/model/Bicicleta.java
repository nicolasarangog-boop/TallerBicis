package co.edu.uniquindio.poo.model;

public class Bicicleta {
    private String color;
    private String marca;
    private String noSerial;
    private String anio;
    private TipoBicicleta tipo;
    private Cliente cliente;
    private taller taller;

    /**
     * Constructor con los atributos de:
     * @param color de la bicicleta
     * @param marca de la bicicleta
     * @param noSerial de la bicicleta
     * @param anio antiguedad de la bicicleta
     * @param tipo de la bicicleta
     * @param cliente dueño de la bicicleta
     * @param taller asociado a la bicicleta
     */
    public Bicicleta(String color, String marca, String noSerial, String anio, TipoBicicleta tipo,
                     Cliente cliente, taller taller) {
        this.color = color;
        this.marca = marca;
        this.noSerial = noSerial;
        this.anio = anio;
        this.tipo = tipo;
        this.cliente = cliente;
        this.taller = taller;
    }

    /**
     * @return color de la bicicleta
     */
    public String getColor() {
        return color;
    }

    /**
     * Metodo para actualizar color de la bicicleta
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return marca de la bicicleta
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Metodo para actualizar marca de la bicicleta
     * @param marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return No. Serial de la bicicleta
     */
    public String getNoSerial() {
        return noSerial;
    }

    /**
     * Metodo para actualizar No. Serial de la bicicleta
     * @param noSerial
     */
    public void setNoSerial(String noSerial) {
        this.noSerial = noSerial;
    }

    /**
     * @return años antiguedad de la bicicleta
     */
    public String getAnio() {
        return anio;
    }

    /**
     * Metodo para actualizar años antiguedad de la bicicleta
     * @param anio
     */
    public void setAnio(String anio) {
        this.anio = anio;
    }

    /**
     * @return tipo de la bicicleta
     */
    public TipoBicicleta getTipo() {
        return tipo;
    }

    /**
     * Metodo para actualizar el tipo de la bicicleta
     * @param tipo
     */
    public void setTipo(TipoBicicleta tipo) {
        this.tipo = tipo;
    }

    /**
     * @return cliente dueño de la bicicleta
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Metodo para actualizar el cliente dueño de la bicicleta
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return taller asociado a la bicicletq
     */
    public taller getTaller() {
        return taller;
    }

    /**
     * Metodo para actualizar el taller asociado a la bicicleta
     * @param taller
     */
    public void setTaller(taller taller) {
        this.taller = taller;
    }
}
