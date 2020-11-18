package com.rihua.jap.jpa02.dao;

import com.rihua.jap.jpa02.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyDao extends JpaRepository<Company,Integer>, JpaSpecificationExecutor<Company> {

}
