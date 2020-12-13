package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;
import com.compnay.beans.Skill;
import com.compnay.beans.Users;
import com.compnay.beans.UserSkills;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    @Override
    public List<UserSkills> getAllUserSkill() {
        List<UserSkills> list = new ArrayList<>();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT"
                    + "	u.*,"
                    + "	s.NAME as skill_name,"
                    + "s.id as skill_id,"
                    + "	us.id as uId,"
                    + "	us.power "
                    + "FROM"
                    + "	user_skill AS us"
                    + "	left JOIN USER AS u ON us.user_id = u.id"
                    + "	left JOIN skill AS s ON s.id = us.skill_id");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                int uId = resultSet.getInt("uId");
                int skillId = resultSet.getInt("skill_id");
                String skillName = resultSet.getString("skill_name");
                int power = resultSet.getInt("power");
                int id = resultSet.getInt("id");
                list.add(new UserSkills(uId, new Users(id), new Skill(skillId, skillName), power));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public List<UserSkills> getUserSkillById(int id) {
          List<UserSkills> list = new ArrayList<>();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT"
                    + "	u.*,"
                    + "	s.NAME as skill_name,"
                    + "s.id as skill_id,"
                    + "	us.id as uId,"
                    + "	us.power "
                    + "FROM"
                    + "	user_skill AS us"
                    + "	left JOIN USER AS u ON us.user_id = u.id"
                    + "	left JOIN skill AS s ON s.id = us.skill_id where us.user_id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                int uId = resultSet.getInt("uId");
                int skillId = resultSet.getInt("skill_id");
                String skillName = resultSet.getString("skill_name");
                int power = resultSet.getInt("power");
                int ID = resultSet.getInt("id");
                list.add(new UserSkills(uId, new Users(ID), new Skill(skillId, skillName), power));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public boolean removeUserSkill(int id) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from user_skill where id=?");
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean updateUserSkill(UserSkills userSkills) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update user_skill set user_id=?,skill_id=?,power=? where id=?");
            preparedStatement.setInt(1, userSkills.getUser().getId());
            preparedStatement.setInt(2, userSkills.getSkill().getId());
            preparedStatement.setInt(3, userSkills.getPower());
            return preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean addUserSkill(UserSkills skills) {
         try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user_skill(user_id,skill_id,power) values(?,?,?)");
            preparedStatement.setInt(1, skills.getUser().getId());
            preparedStatement.setInt(2, skills.getSkill().getId());
            preparedStatement.setInt(3, skills.getPower());
            return preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
