public class Usuario {
    public String name;
    private String id ;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id=id;
    }
    @Override
    public String toString() {
        return "Persona{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
