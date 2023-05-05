package models;

import java.util.Scanner;

public class Student implements IModel<Student> {

    private int id;
    private String firstName;
    private String lastName;

    public Student(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student() {

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
        return String.format("L'étudiant %d est %s %s", this.id, this.firstName, this.lastName);
    }

    public Student verifyCreationInput() {
        Scanner clavier = new Scanner(System.in);

        String saisie = "";
        while (saisie.matches("^$")) {
            System.out.println("Prénom (obligatoire) : ");
            saisie = clavier.nextLine();
            if (saisie.equals("&")) return null;
        }
        setFirstName(saisie);

        saisie = "";
        while (saisie.matches("^$")) {
            System.out.println("Nom de famille (obligatoire) : ");
            saisie = clavier.nextLine();
            if (saisie.equals("&")) return null;
        }
        setLastName(saisie);

        return this;
    }

    @Override
    public Student verifyModificationInput() {
        return null;
    }
}
