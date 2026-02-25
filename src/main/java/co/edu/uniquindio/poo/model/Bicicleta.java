package co.edu.uniquindio.poo.model;

public class Bicicleta {
    private String color;
    private String marca;
    private String noSerial;
    private String anio;
    private TipoBicicleta tipo;
    private Cliente cliente;
    private Taller taller;

    /**
     * Constructor con los atributos de:
     * @param color de la bicicleta.css
     * @param marca de la bicicleta.css
     * @param noSerial de la bicicleta.css
     * @param anio antiguedad de la bicicleta.css
     * @param tipo de la bicicleta.css
     * @param cliente dueño de la bicicleta.css
     * @param taller asociado a la bicicleta.css
     */
    public Bicicleta(String color, String marca, String noSerial, String anio, TipoBicicleta tipo,
                     Cliente cliente, Taller taller) {
        this.color = color;
        this.marca = marca;
        this.noSerial = noSerial;
        this.anio = anio;
        this.tipo = tipo;
        this.cliente = cliente;
        this.taller = taller;
    }

    /**
     * @return color de la bicicleta.css
     */
    public String getColor() {
        return color;
    }

    /**
     * Metodo para actualizar color de la bicicleta.css
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return marca de la bicicleta.css
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Metodo para actualizar marca de la bicicleta.css
     * @param marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return No. Serial de la bicicleta.css
     */
    public String getNoSerial() {
        return noSerial;
    }

    /**
     * Metodo para actualizar No. Serial de la bicicleta.css
     * @param noSerial
     */
    public void setNoSerial(String noSerial) {
        this.noSerial = noSerial;
    }

    /**
     * @return años antiguedad de la bicicleta.css
     */
    public String getAnio() {
        return anio;
    }

    /**
     * Metodo para actualizar años antiguedad de la bicicleta.css
     * @param anio
     */
    public void setAnio(String anio) {
        this.anio = anio;
    }

    /**
     * @return tipo de la bicicleta.css
     */
    public TipoBicicleta getTipo() {
        return tipo;
    }

    /**
     * Metodo para actualizar el tipo de la bicicleta.css
     * @param tipo
     */
    public void setTipo(TipoBicicleta tipo) {
        this.tipo = tipo;
    }

    /**
     * @return cliente dueño de la bicicleta.css
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Metodo para actualizar el cliente dueño de la bicicleta.css
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return taller asociado a la bicicletq
     */
    public Taller getTaller() {
        return taller;
    }

    /**
     * Metodo para actualizar el taller asociado a la bicicleta.css
     * @param taller
     */
    public void setTaller(Taller taller) {
        this.taller = taller;
    }
}
