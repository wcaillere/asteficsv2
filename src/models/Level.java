package models;

public class Level implements IModel<Level> {

    private int id;
    private String name;

    public Level(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Level(int id) {
        this.id = id;
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

    @Override
    public Level verifyCreationInput() {
        return null;
    }

    @Override
    public Level verifyModificationInput() {
        return null;
    }
}
