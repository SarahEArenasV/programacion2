package co.edu.uniquindio.empresa.services;


import co.edu.uniquindio.empresa.model.Vehiculo;

public interface IVehiculoServices {
    boolean agregarVehiculo(String placa, String modelo, String marca, String color);
    Vehiculo obtenerVehiculo(String placa);
    boolean eliminarVehiculo(String placa);
    boolean actualizarVehiculo(String placa, String modelo, String marca, String color);
}