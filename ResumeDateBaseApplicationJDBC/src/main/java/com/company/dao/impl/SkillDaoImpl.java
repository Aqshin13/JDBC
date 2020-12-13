package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.compnay.beans.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    @Override
    public List<Skill> getAllSkill() {
        List<Skill> skills = new ArrayList<>();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from skill");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                skills.add(new Skill(id, name));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return skills;
    }

    @Override
    public Skill getSkillById(int id) {
        Skill skill = null;
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from skill where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                int skillId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                skill = new Skill(id, name);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return skill;
    }

    @Override
    public boolean removeSkill(int id) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from skill where id=?");
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean updateSkill(Skill skill) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update skill set name=? where id=?");
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setInt(2, skill.getId());
            return preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean addSkill(Skill skill) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into skill (name) values(?)");
            preparedStatement.setString(1, skill.getName());
            return preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
