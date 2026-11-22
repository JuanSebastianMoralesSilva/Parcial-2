import java.util.ArrayList;


public class Trainer {
    public String name;
    private String id ;
    private ArrayList<Pokemon> pokemons;
    private ArrayList<ArrayList<String>> medals;

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id=id;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void addMedals(ArrayList<String> medal) {
        this.medals.add(medal);
    }

    public ArrayList<ArrayList<String>> getMedals() {
        return medals;
    }

public Trainer(String name, String id, ArrayList<Pokemon> pokemons){
        this.name = name;
        this.setId(id);
        this.pokemons = pokemons;
        this.medals = new ArrayList<>();
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
