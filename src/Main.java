import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Interfaz ventana = new Interfaz();
            ventana.setVisible(true);
        });
    }
    public static void ejecutarConsola() {
        Scanner lector = new Scanner(System.in);

        ArrayList<Pokemon> pokedexG = Pokedex.cargarPrimeraGeneracion();
        ArrayList<Trainer> listatrainers = trainersIniciales(pokedexG);

        Trainer trainerActual = listatrainers.get(0);
        int opcion;

        do {
            System.out.println("\n====================================================");
            System.out.println(" POKÉMON - ENTRENADOR ACTUAL: " + trainerActual.name.toUpperCase());
            System.out.println("====================================================");
            System.out.println("1. Cambiar de Entrenador");
            System.out.println("2. Ver Equipo Pokémon y Medallas del Entrenador");
            System.out.println("3. Buscar Pokémon en el equipo por ID");
            System.out.println("4. Capturar Pokémon al equipo actual");
            System.out.println("5. Registrar Nuevo Entrenador");
            System.out.println("6. Actualizar Pokémon del equipo (Nombre / Nivel)");
            System.out.println("7. Liberar Pokémon del equipo");
            System.out.println("8. Ganar Medalla (Agregar medalla al entrenador)");
            System.out.println("9. Salir");
            System.out.print("Elija una opción: ");
            opcion = lector.nextInt();
            lector.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- SELECCIONE UN ENTRENADOR ---");
                    for (int i = 0; i < listatrainers.size(); i++) {
                        System.out.println((i + 1) + ". " + listatrainers.get(i).name + " (ID: " + listatrainers.get(i).getId() + ")");
                    }
                    System.out.print("Elija el número del entrenador: ");
                    int idx = lector.nextInt() - 1;
                    lector.nextLine();
                    if (idx >= 0 && idx < listatrainers.size()) {
                        trainerActual = listatrainers.get(idx);
                        System.out.println("Cambiado a " + trainerActual.name);
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- INFORMACIÓN DE " + trainerActual.name.toUpperCase() + " (ID: " + trainerActual.getId() + ") ---");
                    System.out.println("Medallas ganadas: " + trainerActual.getMedal());
                    System.out.println("Equipo Pokémon:");

                    if (trainerActual.getPokemons().isEmpty()) {
                        System.out.println("  (Este entrenador aún no tiene Pokémons)");
                    } else {
                        for (Pokemon p : trainerActual.getPokemons()) {
                            System.out.println("  - " + p);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID (Pokédex) del Pokémon a buscar: ");
                    int idBusqueda = lector.nextInt();
                    lector.nextLine();
                    Pokemon encontrado = null;

                    for (Pokemon p : trainerActual.getPokemons()) {
                        if (p.getId() == idBusqueda) {
                            encontrado = p;
                            break;
                        }
                    }
                    if (encontrado != null) {
                        System.out.println("Encontrado: " + encontrado);
                    } else {
                        System.out.println("No se encontró el Pokemon en el equipo de " + trainerActual.name + ".");
                    }
                    break;

                case 4:
                    System.out.println("\n--- AGREGAR POKÉMON A " + trainerActual.name.toUpperCase() + " ---");
                    System.out.println("1. Elegir uno de los 151 Pokemons de la Pokédex");
                    System.out.println("2. Crear un Pokémon personalizado");
                    System.out.print("Seleccione el método: ");
                    int miniOpcion = lector.nextInt();
                    lector.nextLine();

                    if (miniOpcion == 1) {
                        System.out.print("Ingrese el número de Pokédex (1 - 151): ");
                        int numPokedex = lector.nextInt();
                        lector.nextLine();

                        if (numPokedex >= 1 && numPokedex <= pokedexG.size()) {
                            Pokemon base = pokedexG.get(numPokedex - 1);
                            type[] tiposArray = base.getTypes().toArray(new type[0]);
                            Pokemon capturado = new Pokemon(base.name, base.getId(), base.getLevel(), trainerActual, tiposArray);
                            trainerActual.getPokemons().add(capturado);
                            System.out.println(capturado.name + " fue agregado al equipo de " + trainerActual.name);
                        } else {
                            System.out.println("Número de Pokédex inválido.");
                        }

                    } else if (miniOpcion == 2) {
                        System.out.print("ID del nuevo Pokémon: ");
                        int newId = lector.nextInt();
                        lector.nextLine();

                        System.out.print("Nombre del Pokémon: ");
                        String newName = lector.nextLine();

                        System.out.print("Nivel: ");
                        int newLev = lector.nextInt();
                        lector.nextLine();

                        Pokemon nuevoPokemon = new Pokemon(newName, newId, newLev, trainerActual);

                        System.out.print("¿Cuántos tipos tendrá el Pokémon?: ");
                        int cantTipos = lector.nextInt();
                        lector.nextLine();

                        type[] alltipos = type.values();

                        for (int i = 0; i < alltipos.length; i++) {
                            System.out.print((i + 1) + "." + alltipos[i] + "  ");

                            if ((i + 1) % 6 == 0) System.out.println();
                        }
                        System.out.println();

                        for (int i = 0; i < cantTipos; i++) {
                            System.out.print("Elija el número del tipo " + (i + 1) + ": ");
                            int idxTipo = lector.nextInt() - 1;
                            lector.nextLine();

                            if (idxTipo >= 0 && idxTipo < alltipos.length) {
                                nuevoPokemon.addType(alltipos[idxTipo]);
                            }
                        }

                        trainerActual.getPokemons().add(nuevoPokemon);
                        System.out.println("Pokémon personalizado agregado");
                    }
                    break;

                case 5:
                    System.out.println("\n--- REGISTRAR NUEVO ENTRENADOR ---");
                    System.out.print("Nombre del entrenador: ");
                    String namenewTrainer = lector.nextLine();
                    System.out.print("ID del entrenador: ");
                    String idNewtrainer = lector.nextLine();

                    Trainer nuevotrainer = new Trainer(namenewTrainer, idNewtrainer, new ArrayList<>());
                    listatrainers.add(nuevotrainer);
                    trainerActual = nuevotrainer;

                    System.out.println("Entrenador '" + namenewTrainer + "' creado y seleccionado como actual");
                    break;

                case 6:
                    System.out.print("Ingrese el ID del Pokémon a actualizar: ");
                    int idAct = lector.nextInt();
                    lector.nextLine();

                    Pokemon pokeAct = null;
                    for (Pokemon p : trainerActual.getPokemons()) {
                        if (p.getId() == idAct) {
                            pokeAct = p;
                            break;
                        }
                    }

                    if (pokeAct != null) {
                        System.out.print("Nuevo nombre (actual: " + pokeAct.name + "): ");
                        pokeAct.name = lector.nextLine();

                        System.out.print("Nuevo nivel (actual: " + pokeAct.getLevel() + "): ");
                        pokeAct.setLevel(lector.nextInt());
                        lector.nextLine();

                        System.out.println("Pokémon actualizado");

                    } else {
                        System.out.println("No se encontró un Pokémon con ese ID en el equipo.");
                    }
                    break;

                case 7:
                    System.out.print("Ingrese el ID del Pokémon que quiere liberar: ");
                    int idDel = lector.nextInt();
                    lector.nextLine();

                    boolean eliminado = false;
                    ArrayList<Pokemon> team = trainerActual.getPokemons();

                    for (int i = 0; i < team.size(); i++) {
                        if (team.get(i).getId() == idDel) {
                            team.remove(i);
                            eliminado = true;
                            break;
                        }
                    }
                    if (eliminado) {
                        System.out.println("Pokémon eliminado del equipo");
                    } else {
                        System.out.println("No se encontró el Pokémon.");
                    }
                    break;

                case 8:
                    System.out.println("\n--- OTORGAR MEDALLA A " + trainerActual.name.toUpperCase() + " ---");
                    Medal[] allMedals = Medal.values();

                    for (int i = 0; i < allMedals.length; i++) {
                        System.out.println((i + 1) + ". " + allMedals[i]);
                    }
                    System.out.print("Seleccione el número de la medalla: ");
                    int idxMedalla = lector.nextInt() - 1;
                    lector.nextLine();

                    if (idxMedalla >= 0 && idxMedalla < allMedals.length) {
                        Medal elegida = allMedals[idxMedalla];

                        if (!trainerActual.getMedal().contains(elegida)) {
                            trainerActual.getMedal().add(elegida);
                            System.out.println(trainerActual.name + " obtuvo la " + elegida);
                        } else {
                            System.out.println("El entrenador ya tiene esa medalla.");
                        }
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;

                case 9:
                    System.out.println("Saliendo de Pokémon...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 9);

        lector.close();
    }

    private static Pokemon clonPokedex(ArrayList<Pokemon> pokedex, int numeroPokedex, int nivel, Trainer entrenador) {
        Pokemon base = pokedex.get(numeroPokedex - 1);
        type[] tipos = base.getTypes().toArray(new type[0]);
        return new Pokemon(base.name, base.getId(), nivel, entrenador, tipos);
    }

    public static ArrayList<Trainer> trainersIniciales(ArrayList<Pokemon> pokedex) {
        ArrayList<Trainer> entrenadores = new ArrayList<>();

        Trainer ash = new Trainer("Ash Ketchum", "TR-01", new ArrayList<>());
        ash.getPokemons().add(clonPokedex(pokedex, 25, 50, ash));
        ash.getPokemons().add(clonPokedex(pokedex, 6, 45, ash));
        ash.getPokemons().add(clonPokedex(pokedex, 1, 30, ash));
        ash.getPokemons().add(clonPokedex(pokedex, 7, 30, ash));
        ash.getMedal().add(Medal.MedallaRoca);
        ash.getMedal().add(Medal.MedalladaCascada);
        ash.getMedal().add(Medal.MedallaTrueno);
        entrenadores.add(ash);

        Trainer gary = new Trainer("Gary Oak", "TR-02", new ArrayList<>());
        gary.getPokemons().add(clonPokedex(pokedex, 9, 52, gary));
        gary.getPokemons().add(clonPokedex(pokedex, 59, 48, gary));
        gary.getPokemons().add(clonPokedex(pokedex, 133, 40, gary));
        gary.getMedal().add(Medal.MedallaRoca);
        gary.getMedal().add(Medal.MedalladaCascada);
        gary.getMedal().add(Medal.MedallaArcoiris);
        gary.getMedal().add(Medal.MedallaVolcan);
        entrenadores.add(gary);

        Trainer misty = new Trainer("Misty", "TR-03", new ArrayList<>());
        misty.getPokemons().add(clonPokedex(pokedex, 121, 35, misty));
        misty.getPokemons().add(clonPokedex(pokedex, 54, 25, misty));
        misty.getPokemons().add(clonPokedex(pokedex, 130, 42, misty));
        misty.getMedal().add(Medal.MedalladaCascada);
        entrenadores.add(misty);

        Trainer brock = new Trainer("Brock", "TR-04", new ArrayList<>());
        brock.getPokemons().add(clonPokedex(pokedex, 95, 32, brock));
        brock.getPokemons().add(clonPokedex(pokedex, 74, 24, brock));
        brock.getPokemons().add(clonPokedex(pokedex, 37, 28, brock));
        brock.getMedal().add(Medal.MedallaRoca);
        entrenadores.add(brock);

        return entrenadores;
    }
}