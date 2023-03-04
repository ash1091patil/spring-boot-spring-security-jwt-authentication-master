package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.dto.CompanyData;

import java.util.List;

public interface CompanyService {

	CompanyData saveCompany(CompanyData company);
    boolean deleteCompany(final Long companyId);
    List<CompanyData> getAllCompanies();
    CompanyData getCompanyById(final Long companyId);
}
