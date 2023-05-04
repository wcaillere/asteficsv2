package dao;

import models.Teacher;

import java.util.ArrayList;
import java.util.List;

public class DaoTeacher implements IDao<Teacher> {
    @Override
    public List<Teacher> getAll() {
        System.out.println("test T");
        return new ArrayList<>();
    }

    @Override
    public Teacher getOne(String id) {
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
