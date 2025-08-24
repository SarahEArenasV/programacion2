package co.edu.uniquindio.empresa.services;

public interface IModelFactoryServices extends IPropietarioServices, IVehiculoServices{

    String buscarVehiculoCargaPlaca(String placa);
    String buscarPropietarioNombre(String nombre);
}