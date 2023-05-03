package models;

public class Level {

    private int id;
    private String name;

    public Level(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("le niveau %d a pour nom '%s'", this.id, this.name);
    }
}
