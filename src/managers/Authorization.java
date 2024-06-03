package managers;

import exceptions.NullValueException;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Authorization {
    public Authorization() {
    }
    Scanner scanner = AllManagers.getManagers().getScanner();
    MD2HASH hash = new MD2HASH();
    public static String username;
    private String password;
    public void login(){
        while(true){
            try{
                System.out.println("Если вы новый пользователь - введите 1, если вы уже зарегистрированы - введите 2");
                String income = scanner.nextLine();
                try{
                    if(income.trim().isEmpty()){
                        throw new NullValueException();
                    }else if(income.trim().equals("1")){
                        registerUsername();
                        registerPassword();
                        sendUserToDB(username, getPassword());
                        System.out.println("Успешно зарегистрировались");
                        break;
                    } else if (income.trim().equals("2")) {
                        signUser();
                        break;
                    }else {
                        System.out.println("Введите предложенные значения");
                    }
                }catch (NullValueException e){
                    System.out.println("Введите предложенные значения");
                }
            } catch (NoSuchElementException e){
                System.out.println("Так и быть, выходи из игры");
                System.exit(1);
            }
        }
    }
    public void registerUsername(){
        while(true){
            System.out.println("Введите логин");
            String income = scanner.nextLine();
            try{
                if(income.trim().isEmpty()){
                    throw new NullValueException();
                }else if(checkUserName(income)){
                    System.out.println("Этот логин уже занят");
                }else {
                   setUsername(income.toUpperCase());
                   break;
                }
            }catch (NullValueException e){
                System.out.println("Логин не может быть пустым");
            }
        }
    }
    public void registerPassword(){
        while(true){
            System.out.println("Введите пароль");
            String income = scanner.nextLine();
            try{
                if(income.trim().isEmpty()){
                    throw new NullValueException();
                }else {
                    System.out.println("Введите пароль повторно");
                    String ps = income;
                    while(true){
                        String ps2 = scanner.nextLine();
                        try{
                            if(ps2.trim().isEmpty()){
                                throw new NullValueException();
                            }else if(ps2.equals(ps)){
                                setPassword(income);
                                break;
                            }else {
                                System.out.println("Пароли должны совпадать!");
                            }
                        }catch (NullValueException e){
                            System.out.println("Пароль не может быть пустым");
                        }
                    }
                    break;
                }
            }catch (NullValueException e){
                System.out.println("Пароль не может быть пустым");
            }
        }
    }
    public void signUser(){
        while(true){
            System.out.println("Введите логин");
            String income = scanner.nextLine();
            try{
                if(income.trim().isEmpty()){
                    throw new NullValueException();
                }else if(checkUserName(income)){
                    System.out.println("Такой юзер правда найден");
                    while (true){
                        System.out.println("Введите пароль");
                        String ps = scanner.nextLine();
                        try{
                            if(ps.trim().isEmpty()){
                                throw new NullValueException();
                            }else if(checkPassword(income,ps)){
                                System.out.println("Пароль верен");
                                setUsername(income.toUpperCase());
                                setPassword(ps);
                                break;
                            }else {
                                System.out.println("Пароль неверен");
                            }
                        }catch (NullValueException e){
                            System.out.println("Пароль не может быть пустым");
                        }
                    }
                    break;
                }else {
                    System.out.println("Такой пользователь не найден");
                }
            }catch (NullValueException e){
                System.out.println("Логин не может быть пустым");
            }
        }
    }
    public boolean checkUserName(String nameToCheck){
        String query = "SELECT EXISTS (SELECT 1 FROM s409403.\"USERS\" WHERE \"USERNAME\" =?)";
        try {
            DBConnector dbConnector = new DBConnector();
            PreparedStatement statement = dbConnector.connect().prepareStatement(query);
            statement.setString(1, nameToCheck.toUpperCase());
            ResultSet set = statement.executeQuery();
                if(set.next()){
                return set.getBoolean(1);
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean checkPassword(String username, String password){
        String query = "SELECT \"PASSWORD\" FROM s409403.\"USERS\" WHERE \"USERNAME\" =?";
        try{
            DBConnector dbConnector = new DBConnector();
            PreparedStatement statement = dbConnector.connect().prepareStatement(query);
            statement.setString(1, username.toUpperCase());
            ResultSet set = statement.executeQuery();
            if(set.next()){
                String passwordToCheck = set.getString("PASSWORD");
                String hashPassword = hash.encryptThisString(password);
                return passwordToCheck.equals(hashPassword);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public void sendUserToDB(String username, String password){
        String query = "INSERT INTO s409403.\"USERS\" (\"USERNAME\", \"PASSWORD\") VALUES(?, ?)";
        try{
            DBConnector dbConnector = new DBConnector();
            PreparedStatement statement = dbConnector.connect().prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, hash.encryptThisString(password));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setUsername(String username) {
        Authorization.username = username;
    }
    public void setPassword(String password) {
       this.password = password;
    }

    public String getPassword() {
        return password;
    }
}