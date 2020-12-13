package com.company.dao.inter;

import com.compnay.beans.Country;
import java.util.List;

public interface CountryDaoInter {

    List<Country> getAllCountry();

    Country getCountryById(int id);

    boolean removeCountry(int id);

    boolean addCountry(Country country);

    boolean updateCountry(Country country);

}
