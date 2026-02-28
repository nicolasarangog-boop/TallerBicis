package co.edu.uniquindio.poo.model;

public abstract class Persona {
    protected String nombre;
    protected String tel;
    protected String direccion;
    protected String id;
    protected Taller ownedByTaller;

    /**
     * constructor con atributos
     * @param nombre de la persona
     * @param tel de la persona
     * @param direccion de la persona
     * @param id de la persona
     * @param ownedByTaller
     */
    public Persona (String nombre, String tel, String direccion, String id, Taller ownedByTaller){
        this.nombre=nombre;
        this.tel=tel;
        this.direccion=direccion;
        this.id=id;
        this.ownedByTaller = ownedByTaller;
    }

    /**
     * @return telefono de la persona
     */

    public String getTel() {
        return tel;
    }

    /**
     * Metodo para actualizar el telefono de la persona
     * @param tel de la persona
     */

    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return nombre de la persona
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para actuaalizar el nombre de la persona
     * @param nombre de la persona
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return la direccion de la persona
     */

    public String getDireccion() {
        return direccion;
    }

    /**
     * Metodo para actualizar la direccion de la persona
     * @param direccion de la persona
     */

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return del id de la persona
     */

    public String getId() {
        return id;
    }

    /**
     * Metodo para actualizar el id de la persona
     * @param id de la persona
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return el taller
     */

    public Taller getOwnedByTaller() {
        return ownedByTaller;
    }

    /**
     * Metodo para actualizar el taller
     * @param ownedByTaller
     */

    public void setOwnedByTaller(Taller ownedByTaller) {
        this.ownedByTaller = ownedByTaller;
    }
}
