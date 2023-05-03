package models;

import java.util.Date;

public class Formation {

    private int id;
    private String name;
    private Date begin_at;
    private int nbDays;
    private float price;
    private Level level;
    private boolean isOnline;
    private String program;
    private Category category;
    private Teacher teacher;

    public Formation(String name, Date begin_at, int nbDays, float price, Level level, boolean isOnline, String program, Category category, Teacher teacher) {
        this.name = name;
        this.begin_at = begin_at;
        this.nbDays = nbDays;
        this.price = price;
        this.level = level;
        this.isOnline = isOnline;
        this.program = program;
        this.category = category;
        this.teacher = teacher;
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

    public Date getBegin_at() {
        return begin_at;
    }

    public void setBegin_at(Date begin_at) {
        this.begin_at = begin_at;
    }

    public int getNbDays() {
        return nbDays;
    }

    public void setNbDays(int nbDays) {
        this.nbDays = nbDays;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return String.format("la formation %d de catégorie '%s' et de niveau '%s' a pour nom '%s' et pour programme '%s'",
                this.id, this.category.getName(), this.level.getName(), this.name, this.program) +
                "Elle commence le " + this.begin_at +
                String.format(" et dure %d jour(s) pour un prix total de %f. ", this.nbDays, this.price) +
                (this.isOnline ? "Elle se déroule en ligne" : "Elle se déroule sur place") +
                " avec le formateur " + this.teacher.getFirstName() + this.teacher.getLastName();
    }
}
