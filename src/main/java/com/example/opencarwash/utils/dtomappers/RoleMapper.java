package com.example.opencarwash.utils.dtomappers;

import java.util.NoSuchElementException;

public class RoleMapper {
    private RoleMapper(){};

    public static String numberToRoleName(int number) throws NoSuchElementException{
        switch(number){
            case(0):
                return "ROLE_USER";

            case(1):
                return "ROLE_EMPLOYEE";

            case(2):
                return "ROLE_OWNER";

            case(3):
                return "ROLE_GLOBAL_ADMIN";

            default:
                throw new NoSuchElementException("This number don't maps to any role");
        }
    }

    public static int roleNameToNumber(String roleName){
        switch(roleName){
            case("ROLE_USER"):
                return 0;

            case("ROLE_EMPLOYEE"):
                return 1;

            case("ROLE_OWNER"):
                return 2;

            case("ROLE_GLOBAL_ADMIN"):
                return 3;

            default:
                throw new NoSuchElementException("This role doesn't exists");
        }
    }

}
