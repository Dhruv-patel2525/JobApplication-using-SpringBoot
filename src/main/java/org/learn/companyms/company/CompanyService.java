package org.learn.companyms.company;

import java.util.List;

public interface CompanyService  {

    List<Company> findAll();

    Company getCompanyById(Long id);

    void createCompany(Company company);

    boolean updateCompany(Long id, Company company);

    boolean deleteCompany(Long id);
}
