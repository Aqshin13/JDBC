package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import com.compnay.beans.Country;
import com.compnay.beans.Users;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private Users getUser(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String address = resultSet.getString("address");
        String profileDescription = resultSet.getString("profile_description");
        int nationalityId = resultSet.getInt("nationality_id");
        int birthplaceId = resultSet.getInt("birthplace_id");
        String nationalitySTR = resultSet.getString("nationality");
        String birthplaceSTR = resultSet.getString("birthplace");
        String password=resultSet.getString("password");
        Date birthdate = resultSet.getDate("birthdate");
        Country nationality = new Country(nationalityId, null, nationalitySTR);
        Country birthplace = new Country(birthplaceId, birthplaceSTR, null);

        return new Users(id, name, surname, id, email,password, phone, address, profileDescription, birthdate, birthplace, nationality);
    }

    @Override
    public List<Users> getAllUser(String name, String surname, String nationalityId) {
        List<Users> users = new ArrayList<Users>();
        int i = 1;
        String query = "SELECT"
                + "	u.*,"
                + "	c.`name` AS birthplace,"
                + "	n.nationality AS nationality "
                + "FROM"
                + "	USER AS u"
                + "	INNER JOIN country AS c ON u.birthplace_id = c.id"
                + "	INNER JOIN country AS n ON u.nationality_id = n.id where 1=1  ";
        try (Connection connection = connect()) {
            if (name != null && !name.trim().isEmpty()) {
                query += " and u.name=? ";
            }
            if (surname != null && !surname.trim().isEmpty()) {
                query += " and u.surname=?";
            }
            if (nationalityId != null) {
                query += " and n.nationality=?";
            }
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if (name != null && !name.trim().isEmpty()) {
                preparedStatement.setString(i, name);
                i++;
            }
            if (surname != null && !surname.trim().isEmpty()) {
                preparedStatement.setString(i, surname);
                i++;
            }
            if (nationalityId != null && !nationalityId.trim().isEmpty()) {
                preparedStatement.setString(i, nationalityId);
                i++;
            }

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Users u = getUser(resultSet);
                users.add(u);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return users;
    }

    @Override
    public Users getUserById(int id) {
        Users u = null;
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT"
                    + "	u.*,"
                    + "	c.`name` AS birthplace,"
                    + "	n.nationality AS nationality "
                    + "FROM"
                    + "	USER AS u"
                    + "	INNER JOIN country AS c ON u.birthplace_id = c.id"
                    + "	INNER JOIN country AS n ON u.nationality_id = n.id where u.id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                u = getUser(resultSet);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return u;
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id=?");
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    @Override
    public boolean addUser(Users u) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user"
                    + "(name,surname,phone,email,address,profile_description,age,birthdate,birthplace_id,nationality_id) values(?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, u.getName());
            preparedStatement.setString(2, u.getSurname());
            preparedStatement.setString(3, u.getPhone());
            preparedStatement.setString(4, u.getEmail());
            preparedStatement.setString(5, u.getAddress());
            preparedStatement.setString(6, u.getProfileDescription());
            preparedStatement.setInt(7, u.getAge());
            preparedStatement.setDate(8, u.getBirthdate());
            preparedStatement.setInt(9, u.getBirthplace().getId());
            preparedStatement.setInt(10, u.getNationality().getId());
            return preparedStatement.execute();

        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    public Users getEmail(String email) {
        Users u = null;
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement( "SELECT"
                    + "	u.*,"
                    + "	c.`name` AS birthplace,"
                    + "	n.nationality AS nationality "
                    + "FROM"
                    + "	USER AS u"
                    + "	INNER JOIN country AS c ON u.birthplace_id = c.id"
                    + "	INNER JOIN country AS n ON u.nationality_id = n.id where u.email=?  ");
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                u = getUser(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;

    }

    @Override
    public boolean updateUser(Users u) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update user set name=?,"
                    + "surname=?,"
                    + "age=?,"
                    + "address=?,"
                    + "profile_description=?,"
                    + "phone=?,"
                    + "email=?,"
                    + "birthdate=?,"
                    + "nationality_id=?,"
                    + "birthplace_id=?"
                    + " where id=?");

            preparedStatement.setString(1, u.getName());
            preparedStatement.setString(2, u.getSurname());
            preparedStatement.setInt(3, u.getAge());
            preparedStatement.setString(4, u.getAddress());
            preparedStatement.setString(5, u.getProfileDescription());
            preparedStatement.setString(6, u.getPhone());
            preparedStatement.setString(7, u.getEmail());
            preparedStatement.setDate(8, u.getBirthdate());
            preparedStatement.setInt(9, u.getNationality().getId());
            preparedStatement.setInt(10, u.getBirthplace().getId());
            preparedStatement.setInt(11, u.getId());
            return preparedStatement.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static void main(String[] args) {
        UserDaoImpl userDaoInter = new UserDaoImpl();
        System.out.println(userDaoInter.getEmail("aqsin@mail.ru"));

    }

}
