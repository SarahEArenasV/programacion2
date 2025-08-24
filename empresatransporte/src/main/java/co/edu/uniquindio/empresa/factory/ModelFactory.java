package co.edu.uniquindio.empresa.factory;

import co.edu.uniquindio.empresa.model.EmpresaTransporte;
import co.edu.uniquindio.empresa.model.Propietario;
import co.edu.uniquindio.empresa.model.Vehiculo;
import co.edu.uniquindio.empresa.model.VehiculoCarga;
import co.edu.uniquindio.empresa.model.VehiculoPasajero;
import co.edu.uniquindio.empresa.services.IModelFactoryServices;

import javax.swing.*;

public class ModelFactory implements IModelFactoryServices {

    private static ModelFactory instance;
    private EmpresaTransporte empresaTransporte;
    private double pesoMaximoPermitido;

    private ModelFactory() {}

    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }

    // Inicializar los datos
    public EmpresaTransporte inicializarDatos() {
        EmpresaTransporte empresa = new EmpresaTransporte();

        pesoMaximoPermitido = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad máxima permitida para vehículos de carga:"));

        String nombreEmpresa = JOptionPane.showInputDialog("Ingrese el nombre de la empresa:");
        empresa.setNombre(nombreEmpresa);

        int cantidadVehiculosCarga = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos vehículos de carga desea ingresar?"));
        for (int i = 0; i < cantidadVehiculosCarga; i++) {
            VehiculoCarga vehiculoCarga = new VehiculoCarga();
            vehiculoCarga.setPlaca(JOptionPane.showInputDialog("Placa del vehículo de carga #" + (i + 1) + ":"));
            vehiculoCarga.setMarca(JOptionPane.showInputDialog("Marca del vehículo de carga #" + (i + 1) + ":"));
            vehiculoCarga.setModelo(JOptionPane.showInputDialog("Modelo del vehículo de carga #" + (i + 1) + ":"));
            vehiculoCarga.setColor(JOptionPane.showInputDialog("Color del vehículo de carga #" + (i + 1) + ":"));
            double capacidad = Double.parseDouble(JOptionPane.showInputDialog("Capacidad de carga (kg) del vehículo de carga #" + (i + 1) + ":"));
            vehiculoCarga.setCapacidadCarga(capacidad);
            vehiculoCarga.setNumeroEjes(Integer.parseInt(JOptionPane.showInputDialog("Número de ejes del vehículo de carga #" + (i + 1) + ":")));
            empresa.getListaVehiculosCarga().add(vehiculoCarga);
        }

        int cantidadVehiculosPasajeros = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos vehículos de pasajeros desea ingresar?"));
        for (int i = 0; i < cantidadVehiculosPasajeros; i++) {
            VehiculoPasajero vehiculoPasajero = new VehiculoPasajero();
            vehiculoPasajero.setPlaca(JOptionPane.showInputDialog("Placa del vehículo de pasajeros #" + (i + 1) + ":"));
            vehiculoPasajero.setMarca(JOptionPane.showInputDialog("Marca del vehículo de pasajeros #" + (i + 1) + ":"));
            vehiculoPasajero.setModelo(JOptionPane.showInputDialog("Modelo del vehículo de pasajeros #" + (i + 1) + ":"));
            vehiculoPasajero.setColor(JOptionPane.showInputDialog("Color del vehículo de pasajeros #" + (i + 1) + ":"));
            vehiculoPasajero.setNumeroMaximoPasajeros(Integer.parseInt(JOptionPane.showInputDialog("Número máximo de pasajeros del vehículo de pasajeros #" + (i + 1) + ":")));
            empresa.getListaVehiculosPasajeros().add(vehiculoPasajero);
        }

        int cantidadPropietarios = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos propietarios desea ingresar?"));
        for (int i = 0; i < cantidadPropietarios; i++) {
            Propietario propietario = new Propietario();
            propietario.setNombre(JOptionPane.showInputDialog("Nombre del propietario #" + (i + 1) + ":"));
            propietario.setNumeroIdentificacion(JOptionPane.showInputDialog("Número de identificación del propietario #" + (i + 1) + ":"));
            propietario.setEmail(JOptionPane.showInputDialog("Email del propietario #" + (i + 1) + ":"));
            propietario.setNumeroCelular(JOptionPane.showInputDialog("Número celular del propietario #" + (i + 1) + ":"));
            propietario.setEdad(Integer.parseInt(JOptionPane.showInputDialog("Edad del propietario #" + (i + 1) + ":")));

            
            String[] opciones = {"Ninguno", "Vehículo de carga", "Vehículo de pasajeros"};
            int tipoVehiculo = JOptionPane.showOptionDialog(null, "¿Desea asociar un vehículo al propietario #" + (i + 1) + "?", "Asociar vehículo",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (tipoVehiculo == 1 && !empresa.getListaVehiculosCarga().isEmpty()) {
                String placaVehiculo = JOptionPane.showInputDialog("Placa del vehículo de carga a asociar:");
                VehiculoCarga vehiculoAsociado = null;
                for (VehiculoCarga v : empresa.getListaVehiculosCarga()) {
                    if (v.getPlaca().equalsIgnoreCase(placaVehiculo)) {
                        vehiculoAsociado = v;
                        break;
                    }
                }
                propietario.setVehiculo(vehiculoAsociado);
            } else if (tipoVehiculo == 2 && !empresa.getListaVehiculosPasajeros().isEmpty()) {
                String placaVehiculo = JOptionPane.showInputDialog("Placa del vehículo de pasajeros a asociar:");
                VehiculoPasajero vehiculoAsociado = null;
                for (VehiculoPasajero v : empresa.getListaVehiculosPasajeros()) {
                    if (v.getPlaca().equalsIgnoreCase(placaVehiculo)) {
                        vehiculoAsociado = v;
                        break;
                    }
                }
                propietario.setVehiculo(vehiculoAsociado);
            }
            empresa.getListaPropietarios().add(propietario);
        }

        JOptionPane.showMessageDialog(null, "Datos cargados correctamente en la empresa " + empresa.getNombre());

        this.empresaTransporte = empresa;

        return empresa;
    }

    // A) Recibiendo un valor de peso, obtener lista de propietarios cuyo vehículo de carga lo supera
    public void mostrarPropietariosPorCapacidad(double capacidadMinima) {
        StringBuilder sb = new StringBuilder("Propietarios con vehículos de carga que superan " + capacidadMinima + " kg:\n");
        boolean encontrado = false;

        for (Propietario propietario : empresaTransporte.getListaPropietarios()) {
            Vehiculo vehiculo = propietario.getVehiculo();
            if (vehiculo instanceof VehiculoCarga) {
                VehiculoCarga carga = (VehiculoCarga) vehiculo;
                if (carga.getCapacidadCarga() > capacidadMinima) {
                    encontrado = true;
                    sb.append("- ").append(propietario.getNombre())
                      .append(" (Vehículo: ").append(carga.getPlaca())
                      .append(", Capacidad: ").append(carga.getCapacidadCarga()).append(" kg)\n");
                }
            }
        }

        if (!encontrado) {
            sb.append("No se encontraron propietarios con vehículos que superen la capacidad.");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // B) Recibiendo la placa de un vehículo de pasajeros, obtener el número de usuarios transportados
    public void mostrarUsuariosPorPlaca(String placa) {
        for (VehiculoPasajero v : empresaTransporte.getListaVehiculosPasajeros()) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                JOptionPane.showMessageDialog(null,
                    "Vehículo con placa " + placa + " transportó " + v.getNumeroMaximoPasajeros() + " pasajeros.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontró un vehículo de pasajeros con la placa: " + placa);
    }

    // C) Obtener el número de propietarios mayores de 40 años
    public void mostrarPropietariosMayores40() {
        int contador = 0;
        StringBuilder sb = new StringBuilder("Propietarios mayores de 40 años:\n");

        for (Propietario p : empresaTransporte.getListaPropietarios()) {
            if (p.getEdad() > 40) {
                contador++;
                sb.append("- ").append(p.getNombre())
                  .append(" (").append(p.getEdad()).append(" años)\n");
            }
        }

        sb.append("Total propietarios mayores de 40 años: ").append(contador);

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Métodos interface
    public EmpresaTransporte getEmpresaTransporte() {
        return empresaTransporte;
    }

    @Override
    public String buscarVehiculoCargaPlaca(String placa) {
        return empresaTransporte.buscarVehiculoCargaPlaca(placa);
    }

    @Override
    public String buscarPropietarioNombre(String nombre) {
        return empresaTransporte.buscarPropietarioNombre(nombre);
    }

    @Override
    public boolean agregarPropietario(String nombre,
                                 String numeroIdentificacion,
                                 String email,
                                 String numeroCelular,
                                 Vehiculo vehiculo,
                                 int edad) {
        return empresaTransporte.agregarPropietario(nombre, numeroIdentificacion, email, numeroCelular, vehiculo, edad);
    }

    @Override
    public Propietario obtenerPropietario(String numeroIdentificacion) {
        return empresaTransporte.obtenerPropietario(numeroIdentificacion);
    }

    @Override
    public boolean eliminarPropietario(String numeroIdentificacion) {
        return empresaTransporte.eliminarPropietario(numeroIdentificacion);
    }

    @Override
    public boolean actualizarPropietario(String nombre,
                                 String numeroIdentificacion,
                                 String email,
                                 String numeroCelular,
                                 Vehiculo vehiculo,
                                 int edad) {
        return empresaTransporte.actualizarPropietario(nombre, numeroIdentificacion, email, numeroCelular, vehiculo, edad);
    }

    @Override
    public boolean agregarVehiculo(String placa, String modelo, String marca, String color) {
        return empresaTransporte.agregarVehiculo(placa, modelo, marca, color);
    }

    @Override
    public Vehiculo obtenerVehiculo(String placa) {
        return empresaTransporte.obtenerVehiculo(placa);
    }

    @Override
    public boolean eliminarVehiculo(String placa) {
        return empresaTransporte.eliminarVehiculo(placa);
    }

    @Override
    public boolean actualizarVehiculo(String placa, String modelo, String marca, String color) {
        return empresaTransporte.actualizarVehiculo(placa, modelo, marca, color);
    }
}

