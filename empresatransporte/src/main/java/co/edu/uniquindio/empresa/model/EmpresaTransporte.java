package co.edu.uniquindio.empresa.model;

import java.util.ArrayList;
import java.util.List;
public class EmpresaTransporte {
    private String nombre;
    private List<VehiculoCarga> listaVehiculosCarga = new ArrayList<>();
    private List<VehiculoPasajero> listaVehiculosPasajeros = new ArrayList<>();
    private List<Propietario> listaPropietarios = new ArrayList<>();

    public EmpresaTransporte() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<VehiculoCarga> getListaVehiculosCarga() {
        return listaVehiculosCarga;
    }

    public void setListaVehiculosCarga(List<VehiculoCarga> listaVehiculosCarga) {
        this.listaVehiculosCarga = listaVehiculosCarga;
    }

    public List<VehiculoPasajero> getListaVehiculosPasajeros() {
        return listaVehiculosPasajeros;
    }

    public void setListaVehiculosPasajeros(List<VehiculoPasajero> listaVehiculosPasajeros) {
        this.listaVehiculosPasajeros = listaVehiculosPasajeros;
    }

    public List<Propietario> getListaPropietarios() {
        return listaPropietarios;
    }

    public void setListaPropietarios(List<Propietario> listaPropietarios) {
        this.listaPropietarios = listaPropietarios;
    }

public void crearPropietarioVehiculoCarga(String nombrePropietario, String placaVehiculo) {
    VehiculoCarga vehiculo = new VehiculoCarga();
    vehiculo.setPlaca(placaVehiculo);
    Propietario propietario = new Propietario();
    propietario.setNombre(nombrePropietario);
    propietario.setVehiculo(vehiculo);
    this.getListaPropietarios().add(propietario);
    this.getListaVehiculosCarga().add(vehiculo);
    System.out.println("Propietario y vehículo de carga creados: " + nombrePropietario + " - " + placaVehiculo);
}
}

