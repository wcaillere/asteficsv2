package models;

import java.util.Scanner;

public class Category implements IModel<Category> {

    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {

    }

    public Category(int id) {
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
        return String.format("La catégorie %d a pour nom '%s'", this.id, this.name);
    }

    @Override
    public Category verifyCreationInput() {
        Scanner clavier = new Scanner(System.in);

        String saisie = "";
        while (saisie.matches("^$")) {
            System.out.println("Nom de la catégorie (obligatoire) : ");
            saisie = clavier.nextLine();
            if (saisie.equals("&")) return null;
        }
        setName(saisie);

        return this;
    }

    @Override
    public Category verifyModificationInput() {
        Scanner clavier = new Scanner(System.in);

        System.out.println("Nom de la catégorie : ");
        String saisie = clavier.nextLine();
        if (saisie.equals("&")) return null;
        if (!saisie.trim().equals("")) {
            setName(saisie);
        }

        return this;
    }
}
