package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Company;
import com.bezkoder.springjwt.repository.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


import com.bezkoder.springjwt.dto.CompanyData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("companyService")
public class DefaultCompanyService implements CompanyService{

	@Autowired(required = false)
    private CompanyRepository companyRepository;

    
	@Override
	public CompanyData saveCompany(CompanyData company) {
		Company companyModel = populateCompanyEntity(company);
        return populateCompanyData(companyRepository.save(companyModel));
	}
	

    @Override
    public boolean deleteCompany(Long companyId) {
    	companyRepository.deleteById(companyId);
        return true;
    }


    @Override
    public List<CompanyData> getAllCompanies() {
        List<CompanyData> companies = new ArrayList<>();
        List<Company> companyList = companyRepository.findAll();
        companyList.forEach(company -> {
        	companies.add(populateCompanyData(company));
        });
        return companies;
    }

    @Override
    public CompanyData getCompanyById(Long companyId) {
        return populateCompanyData(companyRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException("Company not found")));
    }


    private CompanyData populateCompanyData(final Company company){
    	CompanyData companyData = new CompanyData();
    	companyData.setId(company.getId());
    	companyData.setName(company.getName());
    	companyData.setPassword(company.getPassword());
    	companyData.setCriteria(company.getCriteria());
    	companyData.setRole(company.getRole());
    	companyData.setPlacedStudCount(company.getPlacedStudCount());
        return companyData;
    }


    private Company populateCompanyEntity(CompanyData companyData){
        Company company = new Company();
        company.setName(companyData.getName());
        company.setPassword(companyData.getPassword());
        company.setRole(companyData.getRole());
        company.setCriteria(companyData.getCriteria());
        company.setPlacedStudCount(companyData.getPlacedStudCount());
        return company;
    }

}