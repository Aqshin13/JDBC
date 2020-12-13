package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.compnay.beans.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    private Country getCountry(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String nationality = resultSet.getString("nationality");
        return new Country(id, name, nationality);

    }

    @Override
    public List<Country> getAllCountry() {
        List<Country> countrys = new ArrayList<>();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from country");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Country country = getCountry(resultSet);
                countrys.add(country);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return countrys;
    }

    @Override
    public Country getCountryById(int id) {
        Country country = null;
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from country where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                country = getCountry(resultSet);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return country;
    }

    @Override
    public boolean removeCountry(int id) {

        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from country where id=?");
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean addCountry(Country country) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into country(name,nationality) values(?,?)");
            preparedStatement.setString(1, country.getName());
            preparedStatement.setString(2, country.getNationality());
            return preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean updateCountry(Country country) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update  country set name=?,nationality=? where id=?");
            preparedStatement.setString(1, country.getName());
            preparedStatement.setString(2, country.getNationality());
            preparedStatement.setInt(3, country.getId());
            return preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
