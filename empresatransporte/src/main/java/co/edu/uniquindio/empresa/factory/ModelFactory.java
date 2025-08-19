package co.edu.uniquindio.empresa.factory;


import co.edu.uniquindio.empresa.model.EmpresaTransporte;
import co.edu.uniquindio.empresa.model.Propietario;
import co.edu.uniquindio.empresa.model.VehiculoCarga;
import co.edu.uniquindio.empresa.model.VehiculoPasajero;

public class ModelFactory {
    private static ModelFactory instance;

    EmpresaTransporte empresaTransporte;

    private ModelFactory() {
    }

    public static ModelFactory getInstance() {
        if(instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }

    public EmpresaTransporte inicializarDatos() {
        EmpresaTransporte empresa = new EmpresaTransporte();
        empresa.setNombre("La carreta");

        VehiculoCarga vehiculoCarga = new VehiculoCarga();
        vehiculoCarga.setCapacidadCarga(200);

        VehiculoCarga vehiculoCarga2 = new VehiculoCarga();
        vehiculoCarga2.setCapacidadCarga(500);

        VehiculoPasajero vehiculoPasajero = new VehiculoPasajero();
        vehiculoPasajero.setNumeroMaximoPasajeros(10);
        vehiculoPasajero.setPlaca("PPP222");


        Propietario propietario = new Propietario();
        propietario.setNombre("Pedro");
        propietario.setVehiculo(vehiculoCarga);
        propietario.setEdad(56);
        propietario.getListaVehiculosAsociados().add(vehiculoCarga2);

        empresa.getListaVehiculosCarga().add(vehiculoCarga);
        empresa.getListaVehiculosPasajeros().add(vehiculoPasajero);
        empresa.getListaPropietarios().add(propietario);
        this.empresaTransporte = empresa;


         //otro vehiculo pasajero para placa 
        VehiculoPasajero vehiculoPasajero2 = new VehiculoPasajero();
        vehiculoPasajero2.setNumeroMaximoPasajeros(15);
        vehiculoPasajero2.setPlaca("PPP223");

        empresa.getListaVehiculosPasajeros().add(vehiculoPasajero2);

        return empresa;
    }

    public EmpresaTransporte getEmpresaTransporte() {
        return empresaTransporte;
    }


    public void crearPropietarioVehiculoCarga(String nombrePropietario, String placaVehiculo) {
        if (empresaTransporte == null) {
            System.out.println("Debe inicializar los datos primero.");
            return;
        }
        VehiculoCarga vehiculo = new VehiculoCarga();
        vehiculo.setPlaca(placaVehiculo);
        vehiculo.setCapacidadCarga(100); 

        Propietario propietario = new Propietario();
        propietario.setNombre(nombrePropietario);
        propietario.setVehiculo(vehiculo);

        empresaTransporte.getListaPropietarios().add(propietario);
        empresaTransporte.getListaVehiculosCarga().add(vehiculo);

        System.out.println("Propietario y vehiculo de carga creados: " + nombrePropietario + " - " + placaVehiculo);
    }
 //Ejercicio 3: Calcular el total de pasajeros transportados en un día.
    public void calcularTotalPasajerosTransportados() {
        int total = 0;
        if (empresaTransporte != null) {
            for (VehiculoPasajero v : empresaTransporte.getListaVehiculosPasajeros()) {
                total += v.getNumeroMaximoPasajeros();
            }
        }
        System.out.println("Total de pasajeros transportados: " + total);
    }
       // punto A propietarios con vehículos con limite de peso 
        public void mostrarPropietariosPorPeso(double pesoMinimo) {
        if (empresaTransporte == null) {
            System.out.println("Debe inicializar los datos .");
            return;
        }
        System.out.println("Propietarios con vehículos de carga mayores a " + pesoMinimo + "kg:");
        boolean alguno = false;
        for (Propietario p : empresaTransporte.getListaPropietarios()) {
            if (p.getVehiculo() instanceof VehiculoCarga) {
                VehiculoCarga v = (VehiculoCarga) p.getVehiculo();
                if (v.getCapacidadCarga() > pesoMinimo) {
                    System.out.println("- " + p.getNombre() + " (" + v.getCapacidadCarga() + "kg)");
                    alguno = true;
                }
            }
        }
        if (!alguno) {
            System.out.println("Ningún propietario supera ese peso.");
        }
    }

    // punto B usuarios movilizados en un vehículo de pasajeros por placa
    public void mostrarUsuariosPorPlaca(String placa) {
        if (empresaTransporte == null) {
            System.out.println("Debe inicializar los datos primero.");
            return;
        }
        boolean encontrado = false;
        for (VehiculoPasajero v : empresaTransporte.getListaVehiculosPasajeros()) {
            if (v.getPlaca() != null && v.getPlaca().equalsIgnoreCase(placa)) {
                System.out.println("Número de usuarios movilizados en el vehículo " + placa + ": " + v.getNumeroMaximoPasajeros());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró un vehículo de pasajeros con la placa " + placa);
        }
    }

 // punto C numero de propietarios mayores de 40 años
    public void mostrarCantidadPropietariosMayores40() {
        if (empresaTransporte == null) {
            System.out.println("Debe inicializar los datos primero.");
            return;
        }
        int cantidad = 0;
        for (Propietario p : empresaTransporte.getListaPropietarios()) {
            if (p.getEdad() > 40) {
                cantidad++;
            }
        }
        System.out.println("Cantidad de propietarios mayores de 40 años: " + cantidad);
    }



}
