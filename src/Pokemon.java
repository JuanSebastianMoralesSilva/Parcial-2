import java.util.ArrayList;

public class Pokemon {
    private int id;
    public String name;
    private ArrayList<type> types;
    private int level;
    private Trainer trainer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public ArrayList<type> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<type> types) {
        this.types = types;
    }

    public void addType(type newType) {
        if (this.types == null) {
            this.types = new ArrayList<>();
        }
        if (!this.types.contains(newType)) {
            this.types.add(newType);
        }
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level=level;
    }

    public Trainer getTrainer(){
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Pokemon() {}

    public Pokemon(String name, int id, int level, Trainer trainer, type... types) {
        this.name = name;
        this.setId(id);
        this.setLevel(level);
        this.setTrainer(trainer);
        this.types = new ArrayList<>();
        for (type t : types) {
            this.addType(t);
        }
    }

    @Override
    public String toString() {
        String nametrainer = (trainer != null) ? trainer.name : "Salvaje / Sin entrenador";

        return "Pokemon{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", type='" + types + '\'' +
                ", level=" + level +
                ", entrenador='" + nametrainer + '\'' +
                '}';
    }
}
