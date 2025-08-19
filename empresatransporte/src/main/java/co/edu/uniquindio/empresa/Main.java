package co.edu.uniquindio.empresa;

import co.edu.uniquindio.empresa.factory.*;

public class Main {
    public static void main(String[] args) {
        ModelFactory modelFactory = ModelFactory.getInstance();
        modelFactory.inicializarDatos();
        crearPropietarioVehiculoCarga(modelFactory);
        calcularTotalPasajerosTransportados(modelFactory);

        // puntos a, b y c
        modelFactory.mostrarPropietariosPorPeso(150);
        modelFactory.mostrarUsuariosPorPlaca("PPP222");
        modelFactory.mostrarCantidadPropietariosMayores40();

        modelFactory.mostrarUsuariosPorPlaca("PPP223");
    }

    private static void calcularTotalPasajerosTransportados(ModelFactory modelFactory) {
        modelFactory.calcularTotalPasajerosTransportados();
    }

    private static void crearPropietarioVehiculoCarga(ModelFactory modelFactory) {
        String propietario = "Pepe";
        String vehiculo = "ARM 2232";
        modelFactory.crearPropietarioVehiculoCarga(propietario, vehiculo);
    }
}