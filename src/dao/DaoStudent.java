package dao;

import models.Student;

import java.util.ArrayList;
import java.util.List;

public class DaoStudent implements IDao<Student> {
    @Override
    public List<Student> getAll() {
        System.out.println("test STS");
        return new ArrayList<>();
    }

    @Override
    public Student getOne(String id) {
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
