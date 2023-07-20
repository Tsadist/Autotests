package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Users {

    private Integer id;
    private String name;
    private String username;
    private Address address;
    private String phone;
    private String website;
    private Company company;
}
