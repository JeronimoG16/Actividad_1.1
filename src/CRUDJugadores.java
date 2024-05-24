//Paquete de clases a utilizar
import java.util.ArrayList; 
import java.util.Scanner;

//Definicion de la clase principal CRUDJugadores
public class CRUDJugadores {
    private static final ArrayList<Jugador> jugadores = new ArrayList<>();  //Se crea la lista estatica de Jugador
    private static final Scanner scanner = new Scanner(System.in);  //Escanner estatico para leer la entrada del usuario

    //Metodo principal 
    public static void main(String[] args) {
        int opcion;//Variable para almacenar la opcion seleccionada del menu
        do {
            mostrarMenu();
            opcion = scanner.nextInt(); //Lee la opcion seleccionada
            scanner.nextLine(); // Consume el salto de línea
            switch (opcion) { //Dependiendo de la opcion llama al metodo que le corresponde
                case 1:
                    agregarJugador(); 
                    break;
                case 2:
                    eliminarJugador();
                    break;
                case 3:
                    buscarJugador();
                    break;
                case 4:
                    editarJugador();
                    break;
                case 5:
                    listarJugadores();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }

    //Metodo para el menu de opciones
    private static void mostrarMenu() {
        System.out.println("\n--- Menú CRUD Jugadores ---");
        System.out.println("1. Agregar jugador");
        System.out.println("2. Eliminar jugador");
        System.out.println("3. Buscar jugador");
        System.out.println("4. Editar jugador");
        System.out.println("5. Listar jugadores");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    //Metodo para la opcion 1, agregar jugador
    private static void agregarJugador() {
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consume el salto de línea
        System.out.print("Ingrese posición: ");
        String posicion = scanner.nextLine();

        //Crea un objeto llamado Jugador, con los datos ingresados
        Jugador jugador = new Jugador(nombre, edad, posicion);
        jugadores.add(jugador);   //Agrega al nuevo jugadorca la lista
        System.out.println("Jugador agregado exitosamente.");
        listarJugadores();       //Muestra la lista de los jugadores
    }

    //Metodo para ejecutar la opcion 2 y eliminar un jugador de la lista
    private static void eliminarJugador() { 
        System.out.print("Ingrese ID del jugador a eliminar: ");
        int id = scanner.nextInt(); //Lee el ID del jugador que se quiere eliminar
        scanner.nextLine(); // Consumir el salto de línea

        Jugador jugador = buscarJugadorPorID(id); //Busca el jugador por medio del ID que se ingreso
        if (jugador != null) { //Condicional para validar que el jugador si existe en la lista
            System.out.print("¿Confirma la eliminación del jugador? (s/n): "); 
            String confirmacion = scanner.nextLine(); //Lee la confirmacion mencionada en la linea anterior
            if (confirmacion.equalsIgnoreCase("s")) { //Condicional para eliminar el jugador despues de la confirmacion
                jugadores.remove(jugador); //Elimina al jugador de la lista
                System.out.println("Jugador eliminado exitosamente.");
                listarJugadores(); //Se imprime la lista de jugadores, actualizada.
            } else {
                System.out.println("Eliminación cancelada.");
            }
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }

    //Metodo para ejecutar la opcion 3 y buscar un jugador en la lista 
    private static void buscarJugador() {
        System.out.print("Ingrese ID del jugador a buscar: ");
        int id = scanner.nextInt(); //Lee el ID del jugador a buscar
        scanner.nextLine(); // Consume el salto de línea

        Jugador jugador = buscarJugadorPorID(id);  //Busca al jugador por medio del ID
        if (jugador != null) { //Condicional para validar la existencia del jugador
            System.out.println(jugador); //Si el jugador existe imprime la informacion del jugador
        } else {
            System.out.println("Jugador no encontrado."); //Si el jugador no existe, imprime esta linea.
        }
    }

    //Metodo para ejecutar la opcion 4 y editar el jugador deseado
    private static void editarJugador() {
        System.out.print("Ingrese ID del jugador a editar: ");
        int id = scanner.nextInt(); //Lee el ID del jugador que se quiere editar
        scanner.nextLine(); // Consume el salto de línea

        Jugador jugador = buscarJugadorPorID(id); //Busca al jugador por medio de la ID
        if (jugador != null) { //Condicional si el jugador existe
            System.out.print("Ingrese nuevo nombre (actual: " + jugador.getNombre() + "): ");
            String nombre = scanner.nextLine(); //Lee el nuevo nombre
            System.out.print("Ingrese nueva edad (actual: " + jugador.getEdad() + "): ");
            int edad = scanner.nextInt(); //Lee la nueva edad
            scanner.nextLine(); // Consume el salto de línea 
            System.out.print("Ingrese nueva posición (actual: " + jugador.getPosicion() + "): "); //Lee la nueva posicion
            String posicion = scanner.nextLine();

            //Se imprime los datos actualizados del jugador que se edito
            System.out.println("Datos nuevos del jugador:");
            System.out.println("Nombre: " + nombre);
            System.out.println("Edad: " + edad);
            System.out.println("Posición: " + posicion);

            System.out.print("¿Confirma la actualización de los datos del jugador? (s/n): ");
            String confirmacion = scanner.nextLine(); //Lee la confirmacion del usuario
            if (confirmacion.equalsIgnoreCase("s")) { //Este metodo confirma cualquier forma de s
                jugador.setNombre(nombre); //Actualiza los datos 
                jugador.setEdad(edad);
                jugador.setPosicion(posicion);
                System.out.println("Jugador editado exitosamente.");
                listarJugadores();
            } else {
                System.out.println("Edición cancelada.");
            }
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }

    //Metodo para ejecutar la opcion 5 e imprimir el listado de los jugadores
    private static void listarJugadores() {
        if (jugadores.isEmpty()) { //Si la lista esta vacia, escribe el siguiente mensaje
            System.out.println("No hay jugadores en la lista.");
        } else { //Si no
            System.out.println("\nLista de jugadores:");
            for (Jugador jugador : jugadores) { //Recorre la lista de jugadores 
                System.out.println(jugador); //Imprime la informacion de cada jugador
            }
        }
    }

    //Mteodo para buscar a cada jugador por medio del ID
    private static Jugador buscarJugadorPorID(int id) {
        for (Jugador jugador : jugadores) { //Recorre la lista
            if (jugador.getId() == id) {
                return jugador; //Retorna el jugador encontrado
            }
        }
        return null; //retorna null si no encuentra el jugador
    }
}
