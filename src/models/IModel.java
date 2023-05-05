package models;

public interface IModel<T> {

    /**
     * Demande à l'utilisateur les informations de l'objet à créer et le retourne
     *
     * @return L'objet créé à partir des informations données par l'utilisateur
     */
    public T verifyCreationInput();

    /**
     * Demande à l'utilisateur les modifications qu'il souhaite apporter à un objet
     *
     * @return une instance de la classe de l'objet comportant les informations à modifier
     */
    public T verifyModificationInput();
}
