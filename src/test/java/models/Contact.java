package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {
    String email;
    String firstName;
    String lastName;
    String company;
}
