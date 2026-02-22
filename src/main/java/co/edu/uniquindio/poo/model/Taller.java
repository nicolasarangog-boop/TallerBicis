package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Taller {
    private String nombre;
    private String tel;
    private String direccion;
    private String id;
    private List<Servicio> listaServicios;
    private List<Persona> listaPersonas;
    private List<Bicicleta> listaBicicletas;
    private List<Repuesto> listaRepuestos;

    /**
     * constructor con los atributos
     * @param nombre del taller
     * @param tel del taller
     * @param direccion del taller
     * @param id del taller
     * @param listaServicios del taller
     * @param listaPersonas del taller
     * @param listaBicicletas del taller
     * @param listaRepuestos del taller
     */
    public Taller (String nombre, String tel, String direccion, String id,List<Servicio> listaServicios,List<Persona> listaPersonas,
                   List<Bicicleta> listaBicicletas,List<Repuesto> listaRepuestos ) {
        this.nombre=nombre;
        this.tel=tel;
        this.direccion=direccion;
        this.id=id;
        this.listaRepuestos=listaRepuestos;
        this.listaBicicletas=listaBicicletas;
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

    public List<Bicicleta> getListaBicicletas() {
        return listaBicicletas;
    }

    /**
     * Metodo para actualizar la lista de bicicletas del taller
     * @param listaBicicletas del taller
     */

    public void setListaBicicletas(List<Bicicleta> listaBicicletas) {
        this.listaBicicletas = listaBicicletas;
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

    /**
     * Agregar personas en el taller
     */
    public String agregarPersona(Persona nuevaPersona) {
        String mensaje = "";
        Optional<Persona> personaExistente = buscarPersona(nuevaPersona.getId());
        if (personaExistente.isPresent()) {
            mensaje = "la persona con el id: " + nuevaPersona.getId() + " ya existe en el taller";
        } else {
            listaPersonas.add(nuevaPersona);
            mensaje = "La persona " + nuevaPersona.getNombre() + " fue agregado exitosamente";
        }
        return mensaje;
    }

    /**
     * Metodo para buscar personas en el taller
     */
    public Optional buscarPersona(String id) {
        return listaPersonas.stream().filter(Persona -> Persona.getId() == id).findFirst();
    }

    /**
     * Metodo para actualizar personas en el taller
     */
    public String actualizarPersona(String id, String nuevoNombre, String nuevoId, String nuevaDireccion, String nuevoTel) {
        String mensaje = "";
        Optional<Persona> personaExistente = buscarPersona(id);
        if (personaExistente.isPresent()) {
            personaExistente.get().setNombre(nuevoNombre);
            personaExistente.get().setId(nuevoId);
            personaExistente.get().setDireccion(nuevaDireccion);
            personaExistente.get().setTel(nuevoTel);
            mensaje = "la persona con id: " + id + " fue actualizada correctamente";
        } else {
            mensaje = "la persona no existe en el taller";
        }
        return mensaje;
    }

    /**
     * Metodo para eliminar personas del taller
     */
    public String eliminarPersona(String id) {
        String mensaje = "";
        Optional<Persona> personaExistente = buscarPersona(id);
        if (personaExistente.isPresent()) {
            listaPersonas.remove(personaExistente.get());
            mensaje = "la persona con id " + id + " fue eliminada exitosamente";
        } else {
            mensaje = "la persona no existe en el taller";
        }
        return mensaje;
    }
    /**
     * Agregar bicicletas en el taller
     */
    public String agregarBiciceta(Bicicleta nuevaBicicleta) {
        String mensaje = "";
        Optional<Bicicleta> bicicletaExistente = buscarBicicleta(nuevaBicicleta.getNoSerial());
        if (bicicletaExistente.isPresent()) {
            mensaje = "la bicicleta con el noSerial: " + nuevaBicicleta.getNoSerial() + " ya existe en el taller";
        } else {
            listaBicicletas.add(nuevaBicicleta);
            mensaje = "La bicicleta con " + nuevaBicicleta.getNoSerial() + " fue agregado exitosamente";
        }
        return mensaje;
    }

    /**
     * Metodo para buscar bicicletas en el taller
     */
    public Optional buscarBicicleta(String noSerial) {
        return listaBicicletas.stream().filter(Bicicleta -> Bicicleta.getNoSerial() == noSerial).findFirst();
    }

    /**
     * Metodo para actualizar Bicicletas en el taller
     */
    public String actualizarBicicleta(String nuevoColor, String nuevaMarca, String noSerial, String nuevoNoSerial,
                                      String nuevoAnio) {
        String mensaje = "";
        Optional<Bicicleta> bicicletaExistente = buscarBicicleta(noSerial);
        if (bicicletaExistente.isPresent()) {
            bicicletaExistente.get().setColor(nuevoColor);
            bicicletaExistente.get().setMarca(nuevaMarca);
            bicicletaExistente.get().setNoSerial(nuevoNoSerial);
            bicicletaExistente.get().setAnio(nuevoAnio);
            mensaje = "la bicicleta con noSerial: " + noSerial + " fue actualizada correctamente";
        } else {
            mensaje = "la bicicleta no existe en el taller";
        }
        return mensaje;
    }

    /**
     * Metodo para eliminar bicicletas del taller
     */
    public String eliminarBicicleta(String noSerial) {
        String mensaje = "";
        Optional<Bicicleta> bicicletaExistente = buscarBicicleta(noSerial);
        if (bicicletaExistente.isPresent()) {
            listaBicicletas.remove(bicicletaExistente.get());
            mensaje = "la bicicleta con noSerial " + noSerial + " fue eliminada exitosamente";
        } else {
            mensaje = "la bicicleta no existe en el taller";
        }
        return mensaje;
    }
    /**
     * Agregar servicios en el taller
     */
    public String agregarServicio(Servicio nuevoServicio) {
        String mensaje = "";
        Optional<Servicio> servicioExistente = buscarServicio(nuevoServicio.getCodigo());
        if (servicioExistente.isPresent()) {
            mensaje = "la servicio con el codigo: " + nuevoServicio.getCodigo() + " ya existe en el taller";
        } else {
            listaServicios.add(nuevoServicio);
            mensaje = "El servicio con " + nuevoServicio.getCodigo() + " fue agregado exitosamente";
        }
        return mensaje;
    }

    /**
     * Metodo para buscar servicios en el taller
     */
    public Optional buscarServicio(String codigo) {
        return listaServicios.stream().filter(Servicio -> Servicio.getCodigo() == codigo).findFirst();
    }

    /**
     * Metodo para actualizar Servicios en el taller
     */
    public String actualizarServicio(LocalDate nuevaFecha, String codigo, String nuevoCodigo, String nuevoMotivo, String nuevoDiagnostico, int nuevoTrabajosRealizados, int nuevoCostoTotal) {
        String mensaje = "";
        Optional<Servicio> servicioExistente = buscarServicio(codigo);
        if (servicioExistente.isPresent()) {
            servicioExistente.get().setFecha(nuevaFecha);
            servicioExistente.get().setMotivo(nuevoMotivo);
            servicioExistente.get().setDiagnostico(nuevoDiagnostico);
            servicioExistente.get().setTrabajosRealizados(nuevoTrabajosRealizados);
            servicioExistente.get().setCostoTotal(nuevoCostoTotal);
            servicioExistente.get().setCodigo(nuevoCodigo);
            mensaje = "el servicio con codigo: " + codigo + " fue actualizado correctamente";
        } else {
            mensaje = "el servicio no existe en el taller";
        }
        return mensaje;
    }

    /**
     * Metodo para eliminar servicios del taller
     */
    public String eliminarServicio(String codigo) {
        String mensaje = "";
        Optional<Servicio> servicioExistente = buscarServicio(codigo);
        if (servicioExistente.isPresent()) {
            listaServicios.remove(servicioExistente.get());
            mensaje = "el servicio con codigo " + codigo + " fue eliminado exitosamente";
        } else {
            mensaje = "el servicio no existe en el taller";
        }
        return mensaje;
    }
    /**
     * Agregar Repuestos en el taller
     */
    public String agregarRepuesto(Repuesto nuevoRepuesto) {
        String mensaje = "";
        Optional<Repuesto> repuestoExistente = buscarRepuesto(nuevoRepuesto.getCodigo());
        if (repuestoExistente.isPresent()) {
            mensaje = "el repuesto con el codigo: " + nuevoRepuesto.getCodigo() + " ya existe en el taller";
        } else {
            listaRepuestos.add(nuevoRepuesto);
            mensaje = "El repuesto con " + nuevoRepuesto.getCodigo() + " fue agregado exitosamente";
        }
        return mensaje;
    }

    /**
     * Metodo para buscar repuesto en el taller
     */
    public Optional buscarRepuesto(String codigo) {
        return listaRepuestos.stream().filter(Repuesto -> Repuesto.getCodigo() == codigo).findFirst();
    }

    /**
     * Metodo para actualizar repuestos en el taller
     */
    public String actualizarRepuesto(String nuevoNombre, String nuevoCodigo, String codigo, String nuevaMarca,
                                     int nuevoStockDisponible) {
        String mensaje = "";
        Optional<Repuesto> repuestoExistente = buscarRepuesto(codigo);
        if (repuestoExistente.isPresent()) {
            repuestoExistente.get().setNombre(nuevoNombre);
            repuestoExistente.get().setCodigo(nuevoCodigo);
            repuestoExistente.get().setMarca(nuevaMarca);
            repuestoExistente.get().setStockDisponible(nuevoStockDisponible);
            mensaje = "el repuesto con codigo: " + codigo + " fue actualizado correctamente";
        } else {
            mensaje = "el repuesto no existe en el taller";
        }
        return mensaje;
    }

    /**
     * Metodo para eliminar repuestos del taller
     */
    public String eliminarRepuesto(String codigo) {
        String mensaje = "";
        Optional<Repuesto> repuestoExistente = buscarRepuesto(codigo);
        if (repuestoExistente.isPresent()) {
            listaRepuestos.remove(repuestoExistente.get());
            mensaje = "el repuesto con codigo " + codigo + " fue eliminado exitosamente";
        } else {
            mensaje = "el repuesto no existe en el taller";
        }
        return mensaje;
    }

    /**
     * metodo para consultar la orden programada en una fecha especifica
     * @param fecha de la orden programada
     * @return lista de ordenes programadas
     */
    public List<Servicio> consultarOrdenProgramada(String fecha){
        List<Servicio> listaOrdenes= new ArrayList<>();
        for(Servicio servicio :listaServicios){
            if(servicio.getFecha().equals(fecha)){
                listaOrdenes.add(servicio);
            }
        }
        return listaOrdenes;
    }

    /**
     * metodo para consultar el historial de la bicicleta
     * @param codigo de la bicicleta
     * @return el historial de la bicicleta
     */

    public List<Servicio> consultarHistorialBici(String codigo){
        List<Servicio> Historial=new ArrayList<>();
        for(Servicio servicio:listaServicios){
            if(servicio.getCodigo().equals(codigo)){
                Historial.add(servicio);
            }
        }
        return Historial;
    }




}
