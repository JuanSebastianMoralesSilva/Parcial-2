import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Pokemon> equipoAsh = new ArrayList<>();
        Trainer ash = new Trainer("Ash Ketchum", "TR01", equipoAsh);

        Pokemon pikachu = new Pokemon("Pikachu", 25, 50, ash, type.ELECTRICO);
        Pokemon charizard = new Pokemon("Charizard", 6, 65, ash, type.FUEGO, type.VOLADOR);

        equipoAsh.add(pikachu);
        equipoAsh.add(charizard);

        ArrayList<String> medalla1 = new ArrayList<>();
        medalla1.add("Kanto");
        medalla1.add("Medalla Roca");
        ash.addMedals(medalla1);

    }
}