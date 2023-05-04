package dao;

import models.Level;

import java.util.ArrayList;
import java.util.List;

public class DaoLevel implements IDao<Level> {
    @Override
    public List<Level> getAll() {
        System.out.println("test réussi levelssss");
        return new ArrayList<>();
    }

    @Override
    public Level getOne(String id) {
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
