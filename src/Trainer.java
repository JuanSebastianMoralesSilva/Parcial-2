import java.util.ArrayList;


public class Trainer {
    public String name;
    private String id ;
    private ArrayList<Pokemon> pokemons;
    private ArrayList<String> medals;

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void addMedal(String medal) {
        medals.add(medal);
    }

    public ArrayList<String> getMedals() {
        return medals;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id=id;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                "Pokemons='" + pokemons + '\'' +
                ", medals='" + medals + '\'' +
                '}';
    }
}
