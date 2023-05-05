package dao;

import java.util.List;

public interface IDao<T> {

    /**
     * Récupère la liste complète des objets demandés dans la base de données
     *
     * @return la liste des objets
     */
    List<T> getAll();

    /**
     * Récupère un objet de type donné dans la base de données grâce à son id
     *
     * @param id L'id de l'objet à récupérer
     * @return L'objet récupéré depuis la base de données
     */
    T getOne(String id);

    /**
     * Crée un objet de type donné dans la base de données grâce aux informations fournies
     *
     * @param information Une instance de la classe de l'objet à créer contenant les informations nécessaires
     */
    void createOne(Object information);

    /**
     * Modifie un objet de type donné dans la base de données grâce aux informations fournies
     *
     * @param id          l'id de l'élément à modifier dans la base de données
     * @param information Une instance de la classe de l'objet à modifier contenant les informations à changer
     */
    void modifyOne(String id, Object information);

    /**
     * Supprime un objet de type donné dans la base de données
     *
     * @param id L'id de l'élément à modifier dans la base de données
     */
    void suppressOne(String id);
}
