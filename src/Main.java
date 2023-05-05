import connection.SQLConnection;
import dal.DalSQL;
import dal.IDal;
import dao.*;
import models.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Affichage du menu
        renderMenu();
    }

    /**
     * Affiche dans la console le menu principal de l'application et attend l'entrée d'une touche
     * par l'utilisateur.
     */
    private static void renderMenu() {
        String[] menuMsg = {"1. Réinitialiser la base", "2. Gérer formations", "3. Gérer catégories",
                "4. Gérer formateurs", "5. Gérer stagiaires", "6. Quitter l'application"};

        System.out.println("\n---- Menu ----");
        for (String msg : menuMsg) {
            System.out.println(msg);
        }

        // Le menu principal reste ouvert tant que l'utilisateur n'entre pas une commande connue
        String saisie;
        while (true) {
            Scanner clavier = new Scanner(System.in);
            saisie = clavier.nextLine();

            switch (saisie) {
                case "1" -> {
                    IDal dal = new DalSQL(
                            Map.of(
                                    Category.class, new DaoCategory(),
                                    Student.class, new DaoStudent(),
                                    Formation.class, new DaoFormation(),
                                    Teacher.class, new DaoTeacher()
                            ), new SQLConnection().getConnection()
                    );
                    dal.initializeDB();
                    renderMenu();
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

    /**
     * Affiche le sous-menu choisi par l'utilisateur depuis le menu principal
     *
     * @param chosenMenu La string correspondant au menu choisi
     */
    private static void renderDetails(String chosenMenu) {
        String[] choosenMsg = {"1. Afficher la liste des ", "2. Afficher ", "3. Créer ", "4. Modifier ",
                "5. Supprimer ", "6. Menu", "7. Quitter l'application"};

        // Permet de savoir quelle classe sera utilisée pour les méthodes
        Map<String, Class<?>> classTb = new HashMap<>();
        classTb.put("formation", Formation.class);
        classTb.put("catégorie", Category.class);
        classTb.put("formateur", Teacher.class);
        classTb.put("stagiaire", Student.class);

        // Initialisation du DAL
        IDal dal = new DalSQL(
                Map.of(
                        Category.class, new DaoCategory(),
                        Student.class, new DaoStudent(),
                        Formation.class, new DaoFormation(),
                        Teacher.class, new DaoTeacher()
                ), new SQLConnection().getConnection()
        );

        // Affichage des commandes possibles pour l'utilisateur
        System.out.println("\n---- " + chosenMenu + " ----");
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
                    saisie = askValidId(clavier);
                    System.out.println("\nAffichage de l'élément " + saisie + " de la liste :");
                    Object result = dal.getOne(classTb.get(chosenMenu), saisie);
                    if (result != null) {
                        System.out.println(result);
                    } else {
                        System.out.println("Aucun élément d'id " + saisie + " n'a été trouvé");
                    }
                    renderDetails(chosenMenu);
                }
                // Création d'un élément
                case "3" -> {
                    System.out.println("\nRemplissez tous ces champs obligatoires (Entrez '&' pour annuler la création)");
                    Class<?> clazz = classTb.get(chosenMenu);
                    try {
                        // Création d'une instance de la classe choisie pour le sous menu
                        IModel<?> entity = (IModel<?>) clazz.getDeclaredConstructor().newInstance();
                        IModel<?> verifiedInput = (IModel<?>) entity.verifyCreationInput();
                        // Si verifiedInput est null, alors l'utilisateur est sorti de la saisie
                        if (verifiedInput != null) {
                            dal.createOne(classTb.get(chosenMenu), verifiedInput);
                        } else {
                            System.out.println("création annulée");
                        }
                    } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                             IllegalAccessException e) {
                        System.out.println("Erreur création élément : " + e.getMessage());
                        System.exit(100);
                    }
                    renderDetails(chosenMenu);
                }
                // Modification d'un élément d'id donné
                case "4" -> {
                    saisie = askValidId(clavier);
                    // Vérification de l'existence de l'objet à modifier dans la base de données
                    Object foundedObject = dal.getOne(classTb.get(chosenMenu), saisie);
                    if (foundedObject == null) {
                        System.out.println("Modification annulée : l'élément d'id " + saisie + " n'existe pas");
                    } else {
                        System.out.println("\nRemplissez les champs à modifier (entrez '&' pour annuler la modification)");
                        Class<?> clazz = classTb.get(chosenMenu);
                        try {
                            IModel<?> entity = (IModel<?>) clazz.getDeclaredConstructor().newInstance();
                            IModel<?> verifiedInput = (IModel<?>) entity.verifyModificationInput();
                            // Si verifiedInput est null, alors l'utilisateur est sorti de la saisie
                            if (verifiedInput != null) {
                                dal.modifyOne(classTb.get(chosenMenu), saisie, verifiedInput);
                            } else {
                                System.out.println("Modification annulée");
                            }
                        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                                 IllegalAccessException e) {
                            System.out.println("Erreur Modification élément : " + e.getMessage());
                            System.exit(100);
                        }
                    }
                    renderDetails(chosenMenu);
                }
                // Suppression d'un élément d'id donné
                case "5" -> {
                    saisie = askValidId(clavier);
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

    /**
     * Écrit dans la console chaque item d'une liste (grâce à leur méthode toString())
     *
     * @param list la liste des éléments à écrire dans la console
     */
    private static void renderList(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

    /**
     * Demande à l'utilisateur un id et vérifie sa validité (input numérique)
     *
     * @param clavier Le scanner utilisé pour la console
     * @return L'id vérifié
     */
    private static String askValidId(Scanner clavier) {
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