package models;

import dao.DaoCategory;
import dao.DaoTeacher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Formation implements IModel<Formation> {

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

    public Formation(int id, String name, Date begin_at, int nbDays, float price, Level level, boolean isOnline, String program, Category category, Teacher teacher) {
        this.id = id;
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

    public Formation() {

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
                ". Elle commence le " + this.begin_at +
                String.format(" et dure %d jour(s) pour un prix total de %f€. ", this.nbDays, this.price) +
                (this.isOnline ? "Elle se déroule en ligne" : "Elle se déroule sur place") +
                " avec le formateur " + this.teacher.getFirstName() + " " + this.teacher.getLastName();
    }

    @Override
    public Formation verifyInput() {
        Scanner clavier = new Scanner(System.in);

        String saisie = "";
        while (saisie.matches("^$")) {
            System.out.println("Nom : ");
            saisie = clavier.nextLine();
        }
        setName(saisie);

        saisie = "";
        while (!saisie.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            System.out.println("Date de type (AAAA-MM-JJ) : ");
            saisie = clavier.nextLine();
        }
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            setBegin_at(f.parse(saisie));
        } catch (Exception e) {
            System.out.println("Erreur création Date " + e.getMessage());
            System.exit(105);
        }

        saisie = "";
        while (!saisie.matches("[0-9]+")) {
            System.out.println("Nombre de jours : ");
            saisie = clavier.nextLine();
        }
        setNbDays(Integer.parseInt(saisie));

        saisie = "";
        while (!saisie.matches("[0-9]+[.]?[0-9]{0,2}")) {
            System.out.println("Prix (en euros) : ");
            saisie = clavier.nextLine();
        }
        setPrice(Float.parseFloat(saisie));

        saisie = "";
        while (!saisie.matches("[1-3]")) {
            System.out.println("Id de la difficulté (1-basique, 2-intermédiaire, 3-difficile) : ");
            saisie = clavier.nextLine();
        }
        setLevel(new Level(Integer.parseInt(saisie)));

        saisie = "";
        while (!saisie.matches("[0-1]")) {
            System.out.println("la formation est-elle en ligne ? (0-non, 1-oui) : ");
            saisie = clavier.nextLine();
        }
        setOnline(!saisie.equals("0"));

        saisie = "";
        while (saisie.matches("^$")) {
            System.out.println("Programme : ");
            saisie = clavier.nextLine();
        }
        setProgram(saisie);

        saisie = "";
        while (!saisie.matches("[0-9]+") || new DaoCategory().getOne(saisie) == null) {
            System.out.println("Id de la catégorie (Entrez un id existant dans la base) : ");
            saisie = clavier.nextLine();
        }
        setCategory(new Category(Integer.parseInt(saisie)));

        saisie = "";
        while (!saisie.matches("[0-9]+") || new DaoTeacher().getOne(saisie) == null) {
            System.out.println("Id du formateur (Entrez un id existant dans la base) : ");
            saisie = clavier.nextLine();
        }
        setTeacher(new Teacher(Integer.parseInt(saisie)));

        return this;
    }
}

/*
        this.category = category;
        this.teacher = teacher;
 */