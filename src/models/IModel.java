package models;

public interface IModel<T> {

    public T verifyCreationInput();

    public T verifyModificationInput();
}
