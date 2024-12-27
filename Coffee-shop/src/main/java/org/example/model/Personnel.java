package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personnel {
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String telephone;
    private String address;
    private long positionId;
}
