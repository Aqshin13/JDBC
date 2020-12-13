package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.compnay.beans.EmploymentHistory;
import com.compnay.beans.Users;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    @Override
    public List<EmploymentHistory> getAllEmploymentHistory() {
        return null;
    }

    @Override
    public EmploymentHistory getAllEmploymentHistoryById(int id) {
        EmploymentHistory u = null;
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from employment_history where user_id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                String header = resultSet.getString("header");
                Date beginDate = resultSet.getDate("begin_date");
                Date endDate = resultSet.getDate("end_date");
                String jobDescription = resultSet.getString("job_description");
                int uId = resultSet.getInt("user_id");
                u = new EmploymentHistory(id, header, beginDate, endDate, jobDescription, new Users(uId));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return u;
    }

    @Override
    public boolean removeEmploymentHistory(int id) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from employment_history where user_id=?");
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean addEmploymentHistory(EmploymentHistory employmentHistory) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into employment_history(header,begin_date,end_date,job_description,user_id) values(?,?,?,?,?) ");
            preparedStatement.setString(1, employmentHistory.getHeader());
            preparedStatement.setDate(2, employmentHistory.getBeginDate());
            preparedStatement.setDate(3, employmentHistory.getEndDate());
            preparedStatement.setString(4, employmentHistory.getJobDescription());
            preparedStatement.setInt(5, employmentHistory.getUser().getId());
            return preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean updateEmploymentHistory(EmploymentHistory employmentHistory) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update employment_history set header=?,begin_date=?,end_date=?,job_description=? where user_id=? ");
            preparedStatement.setString(1, employmentHistory.getHeader());
            preparedStatement.setDate(2, employmentHistory.getBeginDate());
            preparedStatement.setDate(3, employmentHistory.getEndDate());
            preparedStatement.setString(4, employmentHistory.getJobDescription());
            preparedStatement.setInt(5, employmentHistory.getUser().getId());
            return preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
