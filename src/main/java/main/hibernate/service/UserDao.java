package main.hibernate.service;

import main.hibernate.entity.UserEntity;

import java.util.List;

public interface UserDao {
    UserEntity findById(int id);
    List<UserEntity> findByProfession(String value);
    List<UserEntity> findByAge(int age);
    List<UserEntity> findByName(String name);

    UserEntity findByEmail(String email);
    void save(UserEntity user);
    void update(UserEntity user);
    void delete(UserEntity user);

    void bulkSave(UserEntity... userEntities);
    List<UserEntity> findAll();
}
