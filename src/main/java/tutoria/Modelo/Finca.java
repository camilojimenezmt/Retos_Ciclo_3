/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "farm")
public class Finca implements Serializable {
       @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * LLave tipo id unica que permite identificar un registro en especifico
     */
    private Integer id;
    /**
     * Atributo Name que contiene el nombre especifico de una Finca
     */
    private String name; 
    /**
     * Atributo Address contiene la dirección de la Finca Ingresada
     */
    private String address;
    /**
     * Atributo Extension contiene la extensión de la Finca Ingresada
     */
    private Integer extension;
    /**
     * Atributo Description contiene la descripción de la Finca que el 
     * usuario puede solicitar
     */
    private String description;
    
    @ManyToOne
    @JoinColumn(name="categoryid")
    @JsonIgnoreProperties("farms")
    /**
     * Atributo Categoria que nos permite hacer la relación entre la 
     * tabla de Fincas y la de Categorias
     */
    private Categoria category;
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "farm")
    @JsonIgnoreProperties({"farm","client"})
    /**
     *Atributo List Mensaje que nos permite hacer la relación de la tabla 
     * Fincas con una lista que contiene los elementos de la tabla menssage
     */
    private List<Mensaje> messages;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "farm")
    @JsonIgnoreProperties({"farm","message"})
    /**
     * Atributo List Reservacions que nos permite hacer la relación de la tabla 
     * con una lista que contiene los elementos de la tabla Reservations
     * 
     */
    public List<Reservacion> reservations;
    /**
     * Metodo que nos permite obtener cualquier id de la tabla Farm
     * @return id que nos identifica cada objeto de la tabla Farm
     */
    public Integer getId() {
        return id;
    }
    /**
     * Metodo que nos permite asignarle un valor a nuestra id
     * @param id el id que nos identifica cada objeto de la tabla Finca
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Metodo que permite obtener el atributo Nombre de la tabla Fincas
     * @return Nombre
     */
    public String getName() {
        return name;
    }
    /**
     * Metodo que permite modificicar el valor del Nombre 
     * @param name Nombre de la Finca
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Nos permite obtener el dato de la direccion
     * dela Finca que esta se encuentra almacenada en la tabla Farm
     * @return Direccion de la Finca
     */
    public String getAddress() {
        return address;
    }
    /**
     * Este metodo nos permite asignarle un valor a nuestro
     * elemento Dirección que contiene el la dirección de la Finca
     * @param address Dirección de la Finca
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Nos permite obtener el dato de la extension de la Finca 
     * ue se encuentra alamacenado dentro de la tabla Farm
     * @return el valor de la extension que tiene la finca
     */
    public Integer getExtension() {
        return extension;
    }
    /**
     * Este metodo nos permite asignarle un valor a nuestro
     * campo extension que contiene la extension de nuestra finca
     * @param extension extension de la finca
     */
    public void setExtension(Integer extension) {
        this.extension = extension;
    }
    /**
     * Nos permite obtener el dato de la descripcion que tiene una de nuestras
     * Fincas que se encuentra alamacenado dentro de la tabla Farm
     * @return descripcion de la finca
     */
    public String getDescription() {
        return description;
    }
    /**
     * Este metodo nos permite asignarle un valor a nuestro
     * campo description que contiene una breve descripcion de 
     * una de nuestras Fincas
     * @param description  descripcion de la finca que se indique
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Nos permite obtener el arreglo de category que es asignado 
     * a cada habitacion de la tabla Farm
     * @return Lista de elementos de la tabla categoria
     */
    public Categoria getCategory() {
        return category;
    }
    /**
     * Este metodo nos permite asignarle un valor a nuestro
     * arreglo category que contiene la categoria de la Finca
     * @param category  categoria de la finca 
     */
    public void setCategory(Categoria category) {
        this.category = category;
    }
    /**
     * Nos permite obtener el arreglo de messages que es asignado 
     * a cada habitacion de la tabla Farm
     * @return Lista de elementos de la tabla messange
     */
    public List<Mensaje> getMessages() {
        return messages;
    }
    /**
     * Este metodo nos permite asignarle un valor a nuestro
     * arreglo de messages que contiene cada Finca
     * @param messages mensajes de cada finca
     */
    public void setMessages(List<Mensaje> messages) {
        this.messages = messages;
    }
    /**
     * Este metodo nos permite asignarle un valor a nuestro
     * lista de reservation que se le asigna a cada Finca
     * @return Lista con los elementos de reservaciones
     */
    public List<Reservacion> getReservations() {
        return reservations;
    }
    /**
     * Nos permite obtener una lista de reservation que es asignado 
     * a cada habitacion de la tabla Farm cuando se realiza una 
     * reservación
     * @param reservations Nueva reservacion realizada
     */
    public void setReservations(List<Reservacion> reservations) {
        this.reservations = reservations;
    }
    
    
   
   
    
    
}
