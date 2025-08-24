package co.edu.uniquindio.empresa.model;

import co.edu.uniquindio.empresa.services.IEmpresaTransporteServices;
import co.edu.uniquindio.empresa.services.IVehiculoServices;

import java.util.ArrayList;
import java.util.List;

public class EmpresaTransporte implements IEmpresaTransporteServices, IVehiculoServices {

    private String nombre;

    private List<VehiculoCarga> listaVehiculosCarga = new ArrayList<>();
    private List<VehiculoPasajero> listaVehiculosPasajeros = new ArrayList<>();
    private List<Propietario> listaAsociados = new ArrayList<>();

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
        return listaAsociados;
    }

    public void setListaAsociados(List<Propietario> listaAsociados) {
        this.listaAsociados = listaAsociados;
    }

    public void mostrarVehiculosCargaMayorEje(int numeroEjes) {
        for (Propietario propietario: getListaPropietarios()) {
            for (VehiculoCarga vehiculo: propietario.getListaVehiculosAsociados()) {
                if(vehiculo.getNumeroEjes() >= numeroEjes){
                    System.out.println(vehiculo.toString());
                }
            }
        }
    }

    public String buscarVehiculoCargaPlaca(String placa) {
        String resultado = "";
        for (VehiculoCarga vehiculo: getListaVehiculosCarga()) {
            if(vehiculo.getPlaca().equalsIgnoreCase(placa)){
                resultado = vehiculo.toString();
                break;
            }
        }

        return resultado;
    }

    // CRUD propietario
    public String buscarPropietarioNombre(String nombre) {
        String resultado = "";
        for (Propietario propietario: getListaPropietarios()) {
            if(propietario.getNombre().equalsIgnoreCase(nombre)){
                resultado = propietario.toString();
                break;
            }
        }

        return resultado;
    }

    @Override
    public boolean agregarPropietario(String nombre, String numeroIdentificacion, String email, String numeroCelular, Vehiculo vehiculo, int edad) {
        Propietario propietario = obtenerPropietario(numeroIdentificacion);
        if(propietario == null){
            propietario = new Propietario();
            propietario.setNombre(nombre);
            propietario.setNumeroIdentificacion(numeroIdentificacion);
            propietario.setEmail(email);
            propietario.setNumeroCelular(numeroCelular);
            propietario.setEdad(edad);
            getListaPropietarios().add(propietario);

            return true;
        }else{
            return false;
        }
    }

    @Override
    public Propietario obtenerPropietario(String numeroIdentificacion) {
        Propietario propietarioEncontrado = null;
        for (Propietario propietario: getListaPropietarios()) {
            if(propietario.getNumeroIdentificacion().equalsIgnoreCase(numeroIdentificacion)){
                propietarioEncontrado = propietario;
                break;
            }
        }

        return propietarioEncontrado;
    }

    @Override
    public boolean eliminarPropietario(String numeroIdentificacion) {
        Propietario propietario = obtenerPropietario(numeroIdentificacion);
        if(propietario != null){
            getListaPropietarios().remove(propietario);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean actualizarPropietario(String nombre, String numeroIdentificacion, String email, String numeroCelular, Vehiculo vehiculo, int edad) {
        Propietario propietario = obtenerPropietario(numeroIdentificacion);
        if(propietario != null){
            propietario.setNombre(nombre);
            propietario.setNumeroIdentificacion(numeroIdentificacion);
            propietario.setEmail(email);
            propietario.setNumeroCelular(numeroCelular);
            propietario.setEdad(edad);
            propietario.setVehiculo(vehiculo);

            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean agregarVehiculo(String placa, String modelo, String marca, String color) {
        if (obtenerVehiculo(placa) == null) {
            VehiculoCarga vehiculo = new VehiculoCarga(placa, modelo, marca, color, 0);
            vehiculo.setNumeroEjes(0);
            listaVehiculosCarga.add(vehiculo);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Vehiculo obtenerVehiculo(String placa) {
        for (VehiculoCarga vehiculo : listaVehiculosCarga) {
            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                return vehiculo;
            }
        }
        for (VehiculoPasajero vehiculo : listaVehiculosPasajeros) {
            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                return vehiculo;
            }
        }
        return null;
    }

    @Override
    public boolean eliminarVehiculo(String placa) {
        for (int i = 0; i < listaVehiculosCarga.size(); i++) {
            VehiculoCarga vehiculo = listaVehiculosCarga.get(i);
            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                listaVehiculosCarga.remove(i);
                return true;
            }
        }
        for (int i = 0; i < listaVehiculosPasajeros.size(); i++) {
            VehiculoPasajero vehiculo = listaVehiculosPasajeros.get(i);
            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                listaVehiculosPasajeros.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean actualizarVehiculo(String placa, String modelo, String marca, String color) {
        for (VehiculoCarga vehiculo : listaVehiculosCarga) {
            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                vehiculo.setModelo(modelo);
                vehiculo.setMarca(marca);
                vehiculo.setColor(color);
                return true;
            }
        }
        for (VehiculoPasajero vehiculo : listaVehiculosPasajeros) {
            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                vehiculo.setModelo(modelo);
                vehiculo.setMarca(marca);
                vehiculo.setColor(color);
                return true;
            }
        }
        return false;
    }


}