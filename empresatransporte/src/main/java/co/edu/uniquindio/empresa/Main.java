package co.edu.uniquindio.empresa;

import co.edu.uniquindio.empresa.factory.ModelFactory;
import co.edu.uniquindio.empresa.model.Vehiculo;
import co.edu.uniquindio.empresa.model.VehiculoCarga;
import co.edu.uniquindio.empresa.model.VehiculoPasajero;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        ModelFactory modelFactory = ModelFactory.getInstance();
        modelFactory.inicializarDatos();

        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1 -> {
                    double capacidad = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la capacidad mínima de carga:"));
                    modelFactory.mostrarPropietariosPorCapacidad(capacidad);
                }
                case 2 -> {
                    String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo de pasajeros:");
                    modelFactory.mostrarUsuariosPorPlaca(placa);
                }
                case 3 -> modelFactory.mostrarPropietariosMayores40();
                case 4 -> {
                    String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo de carga:");
                    String resultado = modelFactory.buscarVehiculoCargaPlaca(placa);
                    JOptionPane.showMessageDialog(null, resultado.isEmpty() ? "No se encontró vehículo con esa placa." : resultado);
                }
                case 5 -> {
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del propietario:");
                    String resultado = modelFactory.buscarPropietarioNombre(nombre);
                    JOptionPane.showMessageDialog(null, resultado.isEmpty() ? "No se encontró propietario con ese nombre." : resultado);
                }
                case 6 -> {
                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    String id = JOptionPane.showInputDialog("Número identificación:");
                    String email = JOptionPane.showInputDialog("Email:");
                    String celular = JOptionPane.showInputDialog("Número celular:");
                    int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));

                    boolean agregado = modelFactory.agregarPropietario(nombre, id, email, celular, null, edad);
                    JOptionPane.showMessageDialog(null, agregado ? "Propietario agregado con éxito" : "Ya existe un propietario con ese ID.");
                }
                case 7 -> {
                    String id = JOptionPane.showInputDialog("Ingrese el número de identificación del propietario a buscar:");
                    var propietario = modelFactory.getEmpresaTransporte().obtenerPropietario(id);
                    JOptionPane.showMessageDialog(null, propietario == null ? "No se encontró." : propietario.toString());
                }
                case 8 -> {
                    String id = JOptionPane.showInputDialog("Ingrese el número de identificación del propietario a eliminar:");
                    boolean eliminado = modelFactory.getEmpresaTransporte().eliminarPropietario(id);
                    JOptionPane.showMessageDialog(null, eliminado ? "Propietario eliminado" : "No se encontró propietario con ese ID.");
                }
                case 9 -> {
                    String id = JOptionPane.showInputDialog("Número identificación del propietario a actualizar:");
                    String nombre = JOptionPane.showInputDialog("Nuevo nombre:");
                    String email = JOptionPane.showInputDialog("Nuevo email:");
                    String celular = JOptionPane.showInputDialog("Nuevo celular:");
                    int edad = Integer.parseInt(JOptionPane.showInputDialog("Nueva edad:"));

                    boolean actualizado = modelFactory.getEmpresaTransporte().actualizarPropietario(nombre, id, email, celular, null, edad);
                    JOptionPane.showMessageDialog(null, actualizado ? "Propietario actualizado con éxito" : "No se encontró propietario con ese ID.");
                }

                // ===== CRUD Vehículos =====
                case 10 -> {
                    String[] opciones = {"Carga", "Pasajero"};
                    int tipo = JOptionPane.showOptionDialog(null, "Tipo de vehículo:", "Tipo", 0, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                    String placa = JOptionPane.showInputDialog("Placa:");
                    String marca = JOptionPane.showInputDialog("Marca:");
                    String modeloV = JOptionPane.showInputDialog("Modelo:");
                    String color = JOptionPane.showInputDialog("Color:");
                    boolean agregado = modelFactory.agregarVehiculo(placa, modeloV, marca, color);
                    JOptionPane.showMessageDialog(null, agregado ? "Vehículo agregado con éxito" : "Ya existe un vehículo con esa placa.");
                }
                case 11 -> {
                    String placa = JOptionPane.showInputDialog("Ingrese la placa a buscar:");
                    Vehiculo v = modelFactory.obtenerVehiculo(placa);
                    JOptionPane.showMessageDialog(null, v == null ? "No se encontró vehículo." :
                            "Placa: " + v.getPlaca() + "\nMarca: " + v.getMarca() + "\nModelo: " + v.getModelo() + "\nColor: " + v.getColor());
                }
                case 12 -> {
                    String placa = JOptionPane.showInputDialog("Ingrese la placa a eliminar:");
                    boolean eliminado = modelFactory.eliminarVehiculo(placa);
                    JOptionPane.showMessageDialog(null, eliminado ? "Vehículo eliminado" : "No se encontró vehículo con esa placa.");
                }
                case 13 -> {
                    String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo a actualizar:");
                    Vehiculo existente = modelFactory.obtenerVehiculo(placa);
                    if (existente != null) {
                        String nuevaMarca = JOptionPane.showInputDialog("Nueva marca:", existente.getMarca());
                        String nuevoModelo = JOptionPane.showInputDialog("Nuevo modelo:", existente.getModelo());
                        String nuevoColor = JOptionPane.showInputDialog("Nuevo color:", existente.getColor());
                        boolean actualizado = modelFactory.actualizarVehiculo(
                            placa,
                            nuevoModelo,
                            nuevaMarca,
                            nuevoColor
                        );
                        JOptionPane.showMessageDialog(null, actualizado ? "Vehículo actualizado" : "No se pudo actualizar.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No existe un vehículo con esa placa.");
                    }
                }
                case 14 -> {
                    var empresa = modelFactory.getEmpresaTransporte();
                    var carga = empresa.getListaVehiculosCarga();
                    var pasajeros = empresa.getListaVehiculosPasajeros();

                    if ((carga == null || carga.isEmpty()) && (pasajeros == null || pasajeros.isEmpty())) {
                        JOptionPane.showMessageDialog(null, "No hay vehículos registrados.");
                    } else {
                        StringBuilder sb = new StringBuilder("Vehículos registrados:\n\n");
                        if (carga != null && !carga.isEmpty()) {
                            sb.append("=== Carga ===\n");
                            for (VehiculoCarga v : carga) {
                                sb.append("Placa: ").append(v.getPlaca())
                                        .append(" | Capacidad: ").append(v.getCapacidadCarga()).append(" kg\n");
                            }
                            sb.append("\n");
                        }
                        if (pasajeros != null && !pasajeros.isEmpty()) {
                            sb.append("=== Pasajeros ===\n");
                            for (VehiculoPasajero v : pasajeros) {
                                sb.append("Placa: ").append(v.getPlaca())
                                        .append(" | Máx. pasajeros: ").append(v.getNumeroMaximoPasajeros()).append("\n");
                            }
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                    }
                }

                case 0 -> JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                default -> JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        } while (opcion != 0);
    }

    private static int mostrarMenu() {
        String menu = """
                Menu Transporte
                1. Mostrar propietarios con vehículos de carga que superen capacidad mínima
                2. Mostrar número de pasajeros por placa de vehículo
                3. Mostrar propietarios mayores de 40 años
                4. Buscar vehículo de carga por placa
                5. Buscar propietario por nombre
                6. Agregar propietario
                7. Obtener propietario por identificación
                8. Eliminar propietario
                9. Actualizar propietario
                10. Agregar vehículo
                11. Obtener vehículo por placa
                12. Eliminar vehículo
                13. Actualizar vehículo
                14. Listar vehículos
                0. Salir
                """;
        return Integer.parseInt(JOptionPane.showInputDialog(menu));
    }
}
