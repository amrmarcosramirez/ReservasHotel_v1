package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Consola {

    //Constructor
    private Consola(){};

    //M�todos
    public static void mostrarMenu(){
        System.out.println(Arrays.toString(Opcion.values()));
    }

    public static Opcion elegirOpcion() {
        int ordinalOpcion;
        do {
            System.out.println("\nElige una opci�n: ");
            ordinalOpcion = Entrada.entero();
        } while (!(ordinalOpcion >= 0 && ordinalOpcion <= (Opcion.values().length - 1)));
        return Opcion.values()[ordinalOpcion];
    }

    public static Huesped leerHuesped() {

        Huesped huesped = null;
        String nombre = null;
        String telefono = null;
        String correo = null;
        String dni = null;

        try {
            do {
                System.out.print("Introduce el nombre del hu�sped: ");
                nombre = Entrada.cadena();
            } while (nombre.isEmpty());

            do {
                System.out.print("Introduce el tel�fono del hu�sped: ");
                telefono = Entrada.cadena();
            } while (telefono.isEmpty());

            do {
                System.out.print("Introduce el correo del hu�sped: ");
                correo = Entrada.cadena();
            } while (correo.isEmpty());

            do {
                System.out.print("Introduce el DNI del hu�sped: ");
                dni = Entrada.cadena();
            } while (dni.isEmpty());

            String mensaje = "Introduce la fecha de nacimiento del hu�sped (%s): ";
            LocalDate fechaNacimiento = leerFecha(mensaje);

            huesped = new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
        }
         catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
        return new Huesped(huesped);
    }

    public static Huesped leerClientePorDni() {

        Huesped huesped = null;
        String nombre = "Hu�sped Ficticio";
        String telefono = "666666666";
        String correo = "HuspedFicticio@gmail.com";
        String dni = null;
        LocalDate fechaNacimiento = LocalDate.now();

        try {
            do {
                System.out.print("Introduce el DNI del hu�sped: ");
                dni = Entrada.cadena();
            } while (dni.isEmpty());

            huesped = new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
        }
        catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
        return new Huesped(huesped);
    }

    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;

        do {
            try {
                System.out.printf(mensaje, Huesped.FORMATO_FECHA);
                fecha = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Huesped.FORMATO_FECHA));
                fechaValida = true;
            } catch (DateTimeParseException e) {
                fechaValida = false;
            }
        } while(!fechaValida);
        return fecha;
    }

    public static Habitacion leerHabitacion() {

        Habitacion habitacion = null;
        int planta = 0;
        int puerta = -1;
        double precio = 0.0;

        try {
            System.out.print("Introduce la planta de la habitaci�n: ");
            planta = Entrada.entero();

            System.out.print("Introduce la puerta de la habitaci�n: ");
            puerta = Entrada.entero();

            System.out.print("Introduce el precio de la habitaci�n: ");
            precio = Entrada.realDoble();

            TipoHabitacion tipoHabitacion = leerTipoHabitacion();

            habitacion = new Habitacion(planta, puerta, precio, tipoHabitacion);
        }
        catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
        return new Habitacion(habitacion);
    }

    public static Habitacion leerHabitacionPorIdentificador() {

        Habitacion habitacion = null;
        int planta = 0;
        int puerta = -1;
        double precio = 70.0;
        TipoHabitacion tipoHabitacion = TipoHabitacion.SIMPLE;

        try {
            System.out.print("Introduce la planta de la habitaci�n: ");
            planta = Entrada.entero();

            System.out.print("Introduce la puerta de la habitaci�n: ");
            puerta = Entrada.entero();

            habitacion = new Habitacion(planta, puerta, precio, tipoHabitacion);
        }
        catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
        return new Habitacion(habitacion);
    }

    public static TipoHabitacion leerTipoHabitacion(){
        int ordinalHabitacion;
        do {
            System.out.println(Arrays.toString(TipoHabitacion.values()));
            System.out.println("\nElige una opci�n: ");
            ordinalHabitacion = Entrada.entero();
        } while (!(ordinalHabitacion >= 0 && ordinalHabitacion <= (TipoHabitacion.values().length - 1)));
        return TipoHabitacion.values()[ordinalHabitacion];
    }

    public static Regimen leerRegimen(){
        int ordinalRegimen;
        do {
            System.out.println(Arrays.toString(Regimen.values()));
            System.out.println("\nElige un r�gimen: ");
            ordinalRegimen = Entrada.entero();
        } while (!(ordinalRegimen >= 0 && ordinalRegimen <= (Regimen.values().length - 1)));
        return Regimen.values()[ordinalRegimen];
    }

    public static Reserva leerReserva() {

        Reserva reserva = null;
        Huesped huesped = null;
        Habitacion habitacion = null;
        Regimen regimen = null;
        int numeroPersonas;

        try {

            huesped = leerHuesped();
            habitacion = leerHabitacion();
            regimen = leerRegimen();

            String mensaje = "Introduce la fecha de inicio de la reserva (%s): ";
            LocalDate fechaInicioReserva = leerFecha(mensaje);

            String mensaje2 = "Introduce la fecha de fin de la reserva (%s): ";
            LocalDate fechaFinReserva = leerFecha(mensaje2);

            System.out.print("Introduce el n�mero de personas: ");
            numeroPersonas = Entrada.entero();

            reserva = new Reserva(huesped, habitacion, regimen, fechaInicioReserva,
                    fechaFinReserva, numeroPersonas);
        }
        catch(IllegalArgumentException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
        return new Reserva(reserva);
    }

}