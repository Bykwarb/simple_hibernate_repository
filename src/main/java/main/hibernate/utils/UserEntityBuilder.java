package main.hibernate.utils;

import main.hibernate.entity.UserEntity;


public class UserEntityBuilder {
    private UserEntity user;

    public UserEntityBuilder(){
        user = new UserEntity();
    }

    public UserEntityBuilder setId(int id){
        user.setId(Long.valueOf(id));
        return this;
    }

    public UserEntityBuilder setName(String name) {
        user.setName(name);
        return this;
    }


    public UserEntityBuilder setAge(int age) {
        user.setAge(age);
        return this;
    }

    public UserEntityBuilder setProfession(String profession) {
        user.setProfession(profession);
        return this;
    }
    public UserEntityBuilder setEmail(String email){
        user.setLoginEmail(email);
        return this;
    }
    public UserEntityBuilder setPassword(String password){
        user.setLoginPassword(password);
        return this;
    }

    public UserEntity build(){
        return user;
    }
}