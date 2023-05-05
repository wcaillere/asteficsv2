package dal;

import dao.IDao;

import java.util.List;
import java.util.Map;

public interface IDal {

    /**
     * Permet d'obtenir un tableau de correspondance créé lors de l'initialisation du Dal,
     * contenant les DAO à utiliser en fonction du type de l'objet sur lequel une action du dal
     * est effectuée
     *
     * @return le tableau de correspondance des classes et des DAO
     */
    Map<Class<?>, IDao<?>> getDao();

    /**
     * Tronque toutes les tables de la base de données pour la vider complétement
     */
    void initializeDB();

    /**
     * Retourne la liste des objets demandés en utilisant le DAO correspondant à leur classe
     *
     * @param entity la classe des objets contenus dans la liste à retourner
     * @return la liste des objets de classe donnée dans la base de données
     */
    List<?> getAll(Class<?> entity);

    /**
     * Retourne un élément de classe et d'id donnés depuis la base de données en utilisant le bon DAO
     *
     * @param entity la classe de l'objet à retourner
     * @param id     l'id de l'objet à retourner
     * @return l'objet demandé récupéré depuis la base de données
     */
    Object getOne(Class<?> entity, String id);

    /**
     * Crée un objet de classe donnée et l'insère dans la bonne table de données en utilisant le DAO correspondant
     *
     * @param entity      la classe de l'objet à créer
     * @param information Instance de la classe de l'objet contenant les informations nécessaires à la création de l'objet
     */
    void createOne(Class<?> entity, Object information);

    /**
     * Modifie un objet de classe donnée dans la base de données en utilisant le bon DAO
     *
     * @param entity      la classe de l'objet à modifier
     * @param id          l'id de l'objet dans la base de données
     * @param information Instance de la classe de l'objet contenant les informations nécessaires à la modification de l'objet
     */
    void modifyOne(Class<?> entity, String id, Object information);

    /**
     * Supprime de la base de données un objet de classe et d'id donnés
     *
     * @param entity la classe de l'objet à supprimer
     * @param id     l'id de l'objet à supprimer dans la base de données
     */
    void suppressOne(Class<?> entity, String id);

}
