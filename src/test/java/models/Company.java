package models;

public class Company {
    String companyName;
    String companyDomain;

    public Company(String companyName, String companyDomain) {
        this.companyName = companyName;
        this.companyDomain = companyDomain;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDomain() {
        return companyDomain;
    }

    public void setCompanyDomain(String companyDomain) {
        this.companyDomain = companyDomain;
    }
}


