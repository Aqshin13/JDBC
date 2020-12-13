package com.company.dao.inter;

import com.compnay.beans.UserSkills;
import java.util.List;

public interface UserSkillDaoInter {

    List<UserSkills> getAllUserSkill();

    List<UserSkills> getUserSkillById(int id);

    boolean removeUserSkill(int id);

    boolean updateUserSkill(UserSkills userSkills);

    boolean addUserSkill(UserSkills skills);

}
