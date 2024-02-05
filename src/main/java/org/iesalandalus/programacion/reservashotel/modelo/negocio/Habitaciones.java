package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;
import java.util.Objects;

public class Habitaciones {

    // Se crean los atributos con su visibilidad adecuada
    private int capacidad;
    private int tamano;
    private Habitacion[] coleccionHabitaciones;

    //Constructores
    public Habitaciones(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        coleccionHabitaciones = new Habitacion[capacidad];
        tamano = 0;
    }

    //M�todos de acceso y modificaci�n
    public Habitacion[] get() {
        return copiaProfundaHabitaciones();
    }

    public Habitacion[] get(TipoHabitacion tipoHabitacion) {
        Habitacion[] copiaHabitaciones = null;
        int j = 0;
        for (Habitacion habitacion : get()){
            if(!(habitacion == null) && habitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                copiaHabitaciones[j++] = new Habitacion(habitacion);
            }
        }
        return copiaHabitaciones;
    }

    private Habitacion[] copiaProfundaHabitaciones() {
        Habitacion[] copiaHabitaciones = new Habitacion[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaHabitaciones[i] = new Habitacion(coleccionHabitaciones[i]);
        }
        return copiaHabitaciones;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        Objects.requireNonNull(habitacion, "ERROR: No se puede insertar una habitaci�n nula.");
        int indice = buscarIndice(habitacion);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan m�s habitaciones.");
        }
        if (tamanoSuperado(indice)) {
            coleccionHabitaciones[indice] = new Habitacion(habitacion);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una habitaci�n con ese identificador.");
        }
    }

    private int buscarIndice(Habitacion habitacion) {
        Objects.requireNonNull(habitacion, "ERROR: No se puede buscar el �ndice de un hu�sped nulo.");

        int indice = 0;
        boolean habitacionEncontrada = false;
        while (!tamanoSuperado(indice) && !habitacionEncontrada) {
            if (habitacion.equals(get()[indice])) {
                habitacionEncontrada = true;
            } else {
                indice++;
            }
        }
        return indice;
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= getTamano();
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= getCapacidad();
    }

    public Habitacion buscar(Habitacion habitacion) {

        int indice = buscarIndice(habitacion);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Habitacion(get()[indice]);
        }
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        Objects.requireNonNull(habitacion, "ERROR: No se puede borrar una habitaci�n nula.");

        int indice = buscarIndice(habitacion);
        if (tamanoSuperado(indice)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitaci�n como la indicada.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; !tamanoSuperado(i); i++) {
            coleccionHabitaciones[i] = coleccionHabitaciones[i+1];
        }
        coleccionHabitaciones[i] = null;
        tamano--;
    }
}
