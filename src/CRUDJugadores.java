import java.util.ArrayList;
import java.util.Scanner;

public class CRUDJugadores {
    private static final ArrayList<Jugador> jugadores = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            switch (opcion) {
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

    private static void agregarJugador() {
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese posición: ");
        String posicion = scanner.nextLine();

        Jugador jugador = new Jugador(nombre, edad, posicion);
        jugadores.add(jugador);
        System.out.println("Jugador agregado exitosamente.");
        listarJugadores();
    }

    private static void eliminarJugador() {
        System.out.print("Ingrese ID del jugador a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Jugador jugador = buscarJugadorPorID(id);
        if (jugador != null) {
            System.out.print("¿Confirma la eliminación del jugador? (s/n): ");
            String confirmacion = scanner.nextLine();
            if (confirmacion.equalsIgnoreCase("s")) {
                jugadores.remove(jugador);
                System.out.println("Jugador eliminado exitosamente.");
                listarJugadores();
            } else {
                System.out.println("Eliminación cancelada.");
            }
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }

    private static void buscarJugador() {
        System.out.print("Ingrese ID del jugador a buscar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Jugador jugador = buscarJugadorPorID(id);
        if (jugador != null) {
            System.out.println(jugador);
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }

    private static void editarJugador() {
        System.out.print("Ingrese ID del jugador a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Jugador jugador = buscarJugadorPorID(id);
        if (jugador != null) {
            System.out.print("Ingrese nuevo nombre (actual: " + jugador.getNombre() + "): ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese nueva edad (actual: " + jugador.getEdad() + "): ");
            int edad = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            System.out.print("Ingrese nueva posición (actual: " + jugador.getPosicion() + "): ");
            String posicion = scanner.nextLine();

            System.out.println("Datos nuevos del jugador:");
            System.out.println("Nombre: " + nombre);
            System.out.println("Edad: " + edad);
            System.out.println("Posición: " + posicion);

            System.out.print("¿Confirma la actualización de los datos del jugador? (s/n): ");
            String confirmacion = scanner.nextLine();
            if (confirmacion.equalsIgnoreCase("s")) {
                jugador.setNombre(nombre);
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

    private static void listarJugadores() {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores en la lista.");
        } else {
            System.out.println("\nLista de jugadores:");
            for (Jugador jugador : jugadores) {
                System.out.println(jugador);
            }
        }
    }

    private static Jugador buscarJugadorPorID(int id) {
        for (Jugador jugador : jugadores) {
            if (jugador.getId() == id) {
                return jugador;
            }
        }
        return null;
    }
}
