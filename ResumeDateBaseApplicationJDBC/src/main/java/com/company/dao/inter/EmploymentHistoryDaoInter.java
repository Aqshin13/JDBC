package com.company.dao.inter;

import com.compnay.beans.EmploymentHistory;
import java.util.List;

public interface EmploymentHistoryDaoInter {

    List<EmploymentHistory> getAllEmploymentHistory();

    EmploymentHistory getAllEmploymentHistoryById(int id);

    boolean removeEmploymentHistory(int id);

    boolean addEmploymentHistory(EmploymentHistory employmentHistory);

    boolean updateEmploymentHistory(EmploymentHistory employmentHistory);

}
