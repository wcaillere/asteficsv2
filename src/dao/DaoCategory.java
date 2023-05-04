package dao;

import models.Category;

import java.util.ArrayList;
import java.util.List;

public class DaoCategory implements IDao<Category> {
    @Override
    public List<Category> getAll() {
        System.out.println("test réussi catégoriesss");
        return new ArrayList<>();
    }

    @Override
    public Category getOne(String id) {
        return null;
    }

    @Override
    public void createOne() {

    }

    @Override
    public void modifyOne(String id) {

    }

    @Override
    public void suppressOne(String id) {

    }
}
