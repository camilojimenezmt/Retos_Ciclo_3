/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.Servicios;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import tutoria.Modelo.Reservacion;
import tutoria.Repositorio.ReservacionRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutoria.reportes.ContadorClientes;
import tutoria.reportes.StatusReservas;

/**
 *
 * @author USUARIO
 */
@Service
public class ServiciosReservacion {
    /**
    * creación de variable de tipo Repositorio con la anotación
    */
       @Autowired
    /**
    * Repositorio de Reservaciones
    */
    private ReservacionRepositorio metodosCrud;
    /**
    * Obtener todo lo guardado en la tabla de Reservation
    * @return lo elementos que contiene la tabla de reservaciones
    */
    public List<Reservacion> getAll(){
        return metodosCrud.getAll();
    }
    /**
     * Obtenemos un elemento de la tabla Reservation por medio de su id
     * @param reservationId es con la que identificaremos a un elemento en especifico
     * @return el objeto con el id
     */
    public Optional<Reservacion> getReservation(int reservationId) {
        return metodosCrud.getReservation(reservationId);
    }
    /**
     * Nos permite guardar un elemento en la tabla de Reservaciones
     * @param reservation elemento de la tabla resevation
     * @return Nos retorna la reservacion que creamos
     */
    public Reservacion save(Reservacion reservation){
        if(reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservacion> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(e.isEmpty()){
                return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }
    /**
     * Con este metodo podemos actualizar nuestra reservación mediante su 
     * id y despues salvar los cambios correspondientes
     * @param reservacion nos referimos a la reservación que creamos
     * @return la reservacion actualizada con los nuevos datos que ingresemos
     */
    public Reservacion update(Reservacion reservacion){
        if(reservacion.getIdReservation()!=null){
            Optional<Reservacion> e= metodosCrud.getReservation(reservacion.getIdReservation());
            if(!e.isEmpty()){

                if(reservacion.getStartDate()!=null){
                    e.get().setStartDate(reservacion.getStartDate());
                }
                if(reservacion.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservacion.getDevolutionDate());
                }
                if(reservacion.getStatus()!=null){
                    e.get().setStatus(reservacion.getStatus());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return reservacion;
            }
        }else{
            return reservacion;
        }
    }
    /**
     * Nos permite eliminar un elemento de la tabla reservation mediante su id
     * @param reservationId el id de uno de los elementos que nos permita identificalo para 
     *  poder eliminarlo unicamente a ese elemento
     * @return Nos indica si el objeto fue eliminado o no
     */
    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    /**
     * Nos permite tener un reporte del estado del servicio de reservaciones
     * indicandonos cuáles de las reservaciones fueron canceladas y cuales 
     * fueron completadas
     * @return Nos retorna un JSON con la cantidad de reservaciones que se
     * encuentran canceladas ya las que se encuentran completadas
     */
    public StatusReservas reporteStatusServicio (){
        List<Reservacion>completed= metodosCrud.ReservacionStatusRepositorio("completed");
        List<Reservacion>cancelled= metodosCrud.ReservacionStatusRepositorio("cancelled");
        
        return new StatusReservas(completed.size(), cancelled.size() );
    }
    /**
     * Nos permite obtener un reporte con la cantidad de tiempo que se utilizó
     * el servicio de las habitaciones por cliente 
     * @param datoA la fecha en la que adquiereron una habitacion
     * @param datoB la fecha en la que hicieron su check out de la 
     * habitacion anteriormente solicitada
     * @return nos devuvle la fecha y hora de inicio y fin de cada una de las
     * reservaciones
     */
    public List<Reservacion> reporteTiempoServicio (String datoA, String datoB){
        SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd");
        
        Date datoUno = new Date();
        Date datoDos = new Date();
        
        try{
             datoUno = parser.parse(datoA);
             datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return metodosCrud.ReservacionTiempoRepositorio(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        
        } 
    } 
    /**
     * Nos permite saber cuál es la cantidad de clientes que han solicitado los 
     * servicios en nuestra página de hotelería
     * @return nos retorna la cantidad de clientes y las reservas que se 
     * realizaron por cliente
     */
     public List<ContadorClientes> reporteClientesServicio(){
            return metodosCrud.getClientesRepositorio();
        } 
}
