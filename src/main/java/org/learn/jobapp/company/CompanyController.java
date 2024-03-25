package org.learn.jobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll()
    {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id)
    {
        Company company = companyService.getCompanyById(id);
        if(company!=null) {
            return new ResponseEntity<>(company,HttpStatus.OK );
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Company company)
    {
        companyService.createCompany(company);
        return new ResponseEntity<>("New company created successfully",HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@RequestBody Company company,@PathVariable Long id)
    {
        boolean updated = companyService.updateCompany(id,company);
        if(updated)
        {
            return ResponseEntity.ok("Updated Successfully");
        }
        return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id)
    {
        boolean deleted = companyService.deleteCompany(id);
        if(deleted)
        {
            return ResponseEntity.ok("Deleted Successfully");
        }
        return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
    }
}
