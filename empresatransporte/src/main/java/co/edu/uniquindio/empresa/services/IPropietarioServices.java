package co.edu.uniquindio.empresa.services;
 import co.edu.uniquindio.empresa.model.Propietario;
 import co.edu.uniquindio.empresa.model.Vehiculo;
public interface IPropietarioServices {


    boolean agregarPropietario(String nombre, 
                              String numeroIdentificacion, 
                              String email, 
                              String numeroCelular, 
                              Vehiculo vehiculo, 
                              int edad);

    Propietario obtenerPropietario(String numeroIdentificacion);

    boolean eliminarPropietario(String numeroIdentificacion);


    boolean actualizarPropietario(String nombre, 
                                 String numeroIdentificacion, 
                                 String email, 
                                 String numeroCelular, 
                                 Vehiculo vehiculo, 
                                 int edad);

}