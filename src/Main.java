import connection.SQLConnection;
import dal.DalSQL;
import dal.IDal;
import dao.*;
import models.*;

import java.sql.Connection;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Affichage du menu
        renderMenu();
    }

    private static void renderMenu() {
        String[] menuMsg = {"1. Initialiser la base", "2. Gérer formations", "3. Gérer catégories",
                "4. Gérer formateurs", "5. Gérer stagiaires", "6. Quitter l'application"};

        System.out.println("\n---- Menu ----");
        for (String msg : menuMsg) {
            System.out.println(msg);
        }

        String saisie;

        while (true) {
            Scanner clavier = new Scanner(System.in);

            saisie = clavier.nextLine();

            switch (saisie) {
                case "1" -> {

                }
                case "2" -> renderDetails("formation");
                case "3" -> renderDetails("catégorie");
                case "4" -> renderDetails("formateur");
                case "5" -> renderDetails("stagiaire");
                case "6" -> System.exit(0);
                default -> System.out.println("Commande inconnue, veuillez sélectionner un nombre correct");
            }

        }
    }

    private static void renderDetails(String chosenMenu) {
        String[] choosenMsg = {"1. Afficher la liste des ", "2. Afficher ", "3. Créer ", "4. Modifier ",
                "5. Supprimer ", "6. Menu", "7. Quitter l'application"};

        Map<String, Class<?>> classTb = new HashMap<>();
        classTb.put("formation", Formation.class);
        classTb.put("catégorie", Category.class);
        classTb.put("formateur", Teacher.class);
        classTb.put("stagiaire", Student.class);


        IDal dal = new DalSQL(
                Map.of(
                        Category.class, new DaoCategory(),
                        Student.class, new DaoStudent(),
                        Formation.class, new DaoFormation(),
                        Teacher.class, new DaoTeacher()
                ), new SQLConnection().getConnection()
        );

        System.out.println("\n---- " + chosenMenu + " ----");
        // Si un espace était laissé à la fin du message, on rajoute le nom de l'onglet
        // choisi dans le menu. Un "s" est rajouté au nom de l'onglet pour le msg 1
        for (String msg : choosenMsg) {
            System.out.println(msg.endsWith(" ") ? msg + chosenMenu + (msg.startsWith("1") ? "s" : "") : msg);
        }

        while (true) {
            Scanner clavier = new Scanner(System.in);

            String saisie = clavier.nextLine();

            switch (saisie) {
                // Affichage de tous les éléments de la table
                case "1" -> {
                    System.out.println("\n-------------------------\nListe des " + chosenMenu + "s : ");
                    List<?> result = dal.getAll(classTb.get(chosenMenu));
                    renderList(result);
                    renderDetails(chosenMenu);
                }
                // Affichage d'un élément d'id donné
                case "2" -> {
                    saisie = askValidInput(clavier);
                    System.out.println("\nAffichage de l'élément " + saisie + " de la liste :");
                    Object result = dal.getOne(classTb.get(chosenMenu), saisie);
                    if (result != null) {
                        System.out.println(result.toString());
                    } else {
                        System.out.println("Aucun élément d'id " + saisie + " n'a été trouvé");
                    }
                    renderDetails(chosenMenu);
                }
                // Création d'un élément
                case "3" -> {
                    System.out.println("\nVeuillez remplir ces champs");
                    renderDetails(chosenMenu);
                }
                // Modification d'un élément d'id donné
                case "4" -> {
                    System.out.println("\nModification d'un élément");
                    renderDetails(chosenMenu);
                }
                // Suppression d'un élément d'id donné
                case "5" -> {
                    saisie = askValidInput(clavier);
                    System.out.println("\nSuppression de l'élément " + saisie + " de la liste...");
                    dal.suppressOne(classTb.get(chosenMenu), saisie);
                    renderDetails(chosenMenu);
                }
                // Retour au menu
                case "6" -> renderMenu();
                // Quitter l'application
                case "7" -> System.exit(0);
                default -> System.out.println("Commande inconnue, veuillez sélectionner un nombre correct");
            }
        }
    }

    private static void renderList(List<?> list) {
        for (Object item : list) {
            System.out.println(item.toString());
        }
    }

    private static void renderHashMap(HashMap<String, Object> hm) {
        Object[] hashKeys = hm.keySet().toArray();
        for (Object key : hashKeys) {
            System.out.print(key + " : " + hm.get(key) + ", ");
        }
    }

    private static String askValidInput(Scanner clavier) {
        do {
            System.out.println("\nEntrez l'id de l'élément : ");
            String saisie = clavier.nextLine();

            if (saisie.matches("[0-9]+")) {
                return saisie;
            } else {
                System.out.println("\nEntrez un id valide (numérique)");
            }
        }
        while (true);
    }
}