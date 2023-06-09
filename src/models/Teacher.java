package models;

import java.util.Scanner;

public class Teacher implements IModel<Teacher> {

    private int id;
    private String firstName;
    private String lastName;

    public Teacher(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Teacher() {
    }

    public Teacher(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Le formateur %d est %s %s", this.id, this.firstName, this.lastName);
    }

    public Teacher verifyCreationInput() {
        Scanner clavier = new Scanner(System.in);

        String saisie = "";
        while (saisie.matches("^$")) {
            System.out.println("Prénom : ");
            saisie = clavier.nextLine();
            if (saisie.equals("&")) return null;
        }
        setFirstName(saisie);

        saisie = "";
        while (saisie.matches("^$")) {
            System.out.println("Nom de famille : ");
            saisie = clavier.nextLine();
            if (saisie.equals("&")) return null;
        }
        setLastName(saisie);

        return this;
    }

    @Override
    public Teacher verifyModificationInput() {
        Scanner clavier = new Scanner(System.in);

        System.out.println("Prénom : ");
        String saisie = clavier.nextLine();
        if (saisie.equals("&")) return null;
        if (!saisie.trim().equals("")) {
            setFirstName(saisie);
        }

        System.out.println("Nom de famille : ");
        saisie = clavier.nextLine();
        if (saisie.equals("&")) return null;
        if (!saisie.trim().equals("")) {
            setLastName(saisie);
        }

        return this;
    }
}
