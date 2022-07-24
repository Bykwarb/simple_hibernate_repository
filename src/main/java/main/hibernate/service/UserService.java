package main.hibernate.service;

import main.hibernate.entity.UserEntity;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService(UserDaoImpl userDao){
        this.userDao = userDao;
    }

    public UserEntity findUser(int id){
        return userDao.findById(id);
    }

    public List<UserEntity> findUserByProfession(String profession){
        return userDao.findByProfession(profession);
    }

    public List<UserEntity> findUserByAge(int age){
        return userDao.findByAge(age);
    }

    public UserEntity findByEmail(String email){
        return userDao.findByEmail(email);
    }

    public void saveUser(UserEntity user){
        userDao.save(user);
    }

    public void deleteUser(UserEntity user){
        userDao.delete(user);
    }

    public void updateUser(UserEntity user){
        userDao.update(user);
    }

    public void bulkSave(UserEntity... userEntities){
        userDao.bulkSave(userEntities);
    }

    public List<UserEntity> findUsersByName(String name){
        return userDao.findByName(name);
    }


    public List<UserEntity> findAllUsers(){
        return userDao.findAll();
    }
}
