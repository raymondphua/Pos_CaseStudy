package com.infosupport.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Raymond Phua on 19-9-2016.
 */
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Getter
    private int id;
    @Getter
    private String username;
    @Getter
    private String password;

    public boolean matches(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
