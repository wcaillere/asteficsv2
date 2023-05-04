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

    public Teacher verifyInput() {
        Scanner clavier = new Scanner(System.in);

        String saisie = "";
        while (saisie.matches("^$")) {
            System.out.println("Prénom (obligatoire) : ");
            saisie = clavier.nextLine();
        }
        setFirstName(saisie);

        saisie = "";
        while (saisie.matches("^$")) {
            System.out.println("Nom de famille (obligatoire) : ");
            saisie = clavier.nextLine();
        }
        setLastName(saisie);


        return this;
    }
}
