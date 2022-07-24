package main.hibernate;

import main.hibernate.entity.UserEntity;
import main.hibernate.service.UserDaoImpl;
import main.hibernate.service.UserService;
import main.hibernate.utils.UserEntityBuilder;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class Main {
    public static void main(String... args){
        UserService userService = new UserService(new UserDaoImpl());
        System.out.println(userService.findUser(1));
    }
}
