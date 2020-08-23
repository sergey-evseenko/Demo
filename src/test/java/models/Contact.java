package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {
    String email;
    String firstName;
    String lastName;
    String company;

    public Contact(String email, String firstName, String lastName, String company) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
    }

}
