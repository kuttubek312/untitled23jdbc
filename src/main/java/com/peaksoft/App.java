package com.peaksoft;

import com.peaksoft.Model.City;
import com.peaksoft.Model.Country;
import com.peaksoft.Model.Person;
import com.peaksoft.util.Db;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 3 таблица бар - Шаарлар, Олколор, Шаар башчылары

 * Ар бир таблицага жок дегенде 5тен маалымат киргизуу керек

 * Statement жана PreparedStatement-ти колдонуу

 * Шаарларды жана олколорду ArrayList-ке сактоо

 * Базадан шаарды id менен алып консолго чыгарышыбыз керек
 *
 */
public class App 
{
    public static void main( String[] args )
    {


       //   Db.getById(2);
//
//        creatTablePeople();
//        creatTableCity();
//        creatTableCountry();
//
//
////
//        insertTablePeople(9,"Садыр","Жапаров",45,"управляещий");
//        insertTablePeople(8,"Шавкат","Мирзаев",45,"управляещий");
//        insertTablePeople(7,"Нурсултан","Назарбаев",45,"управляещий");
//
//        insertTableCities(1,4,"Бишкек","1.2миллион");
//        insertTableCities(2,5,"Ташкент","1.9миллион");
//        insertTableCities(3,6,"Алмата","1.5миллион");

//        insertTableCountries(5,"Кыргызстан","Кыргыз",212);
//        insertTableCountries(6,"Узбекистан","узбек",2121);
//        insertTableCountries(4,"Казакстан","казх",2122);
////
//        List<City> allCities = getAllCity();
//        System.out.println(allCities);
////
//        List<Country> allCountry = getAllCountry();
//        System.out.println(allCountry);



    }
    public static void creatTablePeople(){
        String SQL = "CREATE TABLE IF NOT EXISTS people(" +
                " id INT PRIMARY KEY ," +
                " name VARCHAR(50) NOT NULL ," +
                " surname VARCHAR(50) NOT NULL," +
                " age INT," +
                " position VARCHAR(50) NOT NULL" +
                ");";
        try (Connection conn = Db.connect();
             Statement statement = conn.createStatement()){
            statement.executeUpdate(SQL);
            System.out.println("таблица успешно тузулду1");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void insertTablePeople(int id, String name, String surname, int age, String position){
            String SQL = "INSERT INTO people (id,name,surname,age,position ) VALUES(?,?,?,?,?)";
            try(Connection connect = Db.connect();
                PreparedStatement statement = connect.prepareStatement(SQL)){
                statement.setInt(1,id);
                statement.setString(2,name);
                statement.setString(3,surname);
                statement.setInt(4,age);
                statement.setString(5,position);
                statement.executeUpdate();
                System.out.println("маалымат кошулду1");
            }catch (SQLException e ){
                System.out.println(e.getMessage());
            }
        }



    public static void creatTableCity(){
        String SQL = "CREATE TABLE  city(" +
                "id INT NOT NULL," +
                "countryId INT PRIMARY KEY, " +
                "name VARCHAR (50) NOT NULL UNIQUE ," +
                "city_measures VARCHAR(90) NOT NULL)";
        try (Connection conn = Db.connect();
             Statement statement = conn.createStatement()){
            statement.executeUpdate(SQL);
            System.out.println("таблица успешно тузулду2");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void insertTableCities(int id,int countryId,String name, String city_measures){
        String SQL2 = "INSERT INTO city(id,name,countryId ,city_measures) VALUES(?,?,?,?)";
        try(Connection connect = Db.connect();
            PreparedStatement statement = connect.prepareStatement(SQL2)){
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.setInt(3,countryId);
            statement.setString(4,city_measures);
            statement.executeUpdate();
            System.out.println("маалымат кошулду2");
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }
    public static List<City> getAllCity() {
        String sql = "SELECT * FROM city";
        List<City> cities = new ArrayList<>();
        try (Connection conn = Db.connect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                City city = new City();
                int id = resultSet.getInt("id");
                int countryId = resultSet.getInt("countryId");
                String name = resultSet.getString("name");
                String city_measures = resultSet.getString("city_measures");
                int peopleId = resultSet.getInt("peopleId" + name);
                city.setId(id);
                city.setCountryId(countryId);
                city.setName(name);
                city.setCity_measures(city_measures);
                city.setPeopleId(peopleId);
                cities.add(city);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cities;
    }
    public static void creatTableCountry(){
        String SQL =  "CREATE TABLE IF NOT EXISTS country(" +
                " id INT PRIMARY KEY ," +
                "tongue VARCHAR (100) NOT NULL," +
                "square INTEGER," +
                " name VARCHAR (50) NOT NULL )";
        try (Connection conn = Db.connect();
             Statement statement = conn.createStatement()){
            statement.executeUpdate(SQL);
            System.out.println("таблица успешно тузулду3");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void insertTableCountries(int id,String name, String tongue, int square){
        String SQL = "INSERT INTO country(id,name, tongue,square) VALUES(?,?,?,?)";
        try(Connection connect = Db.connect();
            PreparedStatement statement = connect.prepareStatement(SQL)){
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.setString(3,tongue);
            statement.setInt(4,square);
            statement.executeUpdate();
            System.out.println("маалымат кошулду3");
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }
    public static List<Country> getAllCountry() {
        String sql = "SELECT * FROM country";
        List<Country> countries = new ArrayList<>();
        try (Connection conn = Db.connect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Country country = new Country();
                int id = resultSet.getInt("id");
                int tongue = resultSet.getInt("tongue");
                String name = resultSet.getString("name");
                String square = resultSet.getString("square");
                country.setId(id);
                country.setTongue(String.valueOf(tongue));
                country.setName(name);
                country.setSquare(Integer.parseInt(square));
                countries.add(country);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return countries;
    }
}

