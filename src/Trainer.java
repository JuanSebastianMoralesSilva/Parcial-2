import java.util.ArrayList;


public class Trainer {
    public String name;
    private String id ;
    private ArrayList<Pokemon> pokemons;
    private ArrayList<Medal> medals;

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

    public ArrayList<Medal> getMedal() {
        return medals;
    }

    public void setMedal(ArrayList<Medal> medals) {
        this.medals = medals;
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
