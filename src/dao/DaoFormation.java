package dao;

import models.Formation;

import java.util.ArrayList;
import java.util.List;

public class DaoFormation implements IDao<Formation> {
    @Override
    public List<Formation> getAll() {
        System.out.println("test réussi");
        return new ArrayList<>();
    }

    @Override
    public Formation getOne(String id) {
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
