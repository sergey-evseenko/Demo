package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {
    String companyName;
    String companyDomain;

    public Company(String companyName, String companyDomain) {
        this.companyName = companyName;
        this.companyDomain = companyDomain;
    }

}


