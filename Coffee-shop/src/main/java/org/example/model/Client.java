package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date dateOfBirth;
    private String telephone;
    private String address;
    private int discountPercent;
}
