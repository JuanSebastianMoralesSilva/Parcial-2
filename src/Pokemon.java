public class Pokemon {
    private int id;
    public String name;
    private String type;
    private int level;
    private String trainer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type=type;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level=level;
    }

    public String getTrainer(){
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public Pokemon() {}

    public Pokemon(String nombre, int id, int nivel, String tipo, String entrenador) {
        this.name = nombre;
        this.setId(id);
        this.level = nivel;
        this.type = tipo;
        this.trainer =entrenador;
    }

    @Override
    public String toString() {
        return "Pokemon{nivel=" + level +
                ", tipo=" + type +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                "entrenador='" + trainer + '\'' +
                super.toString() + "}";
    }
}
