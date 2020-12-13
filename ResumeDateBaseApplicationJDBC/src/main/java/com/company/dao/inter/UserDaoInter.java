package com.company.dao.inter;

import com.compnay.beans.Users;
import java.util.List;

public interface UserDaoInter {

    List<Users> getAllUser(String name,String surname,String nationality);

    Users getUserById(int id);

    boolean removeUser(int id);

    boolean addUser(Users u);

    boolean updateUser(Users u);

}
