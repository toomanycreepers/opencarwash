package com.example.opencarwash.utils.dtomappers;

import com.example.opencarwash.entities.Role;

import java.util.NoSuchElementException;

public class RoleMapper {
    private RoleMapper(){};

    public static Role numberToRole(int number) throws NoSuchElementException{
        switch(number){
            case(0):
                return new Role("ROLE_USER");

            case(1):
                return new Role("ROLE_EMPLOYEE");

            case(2):
                return new Role("ROLE_OWNER");

            case(3):
                return new Role("ROLE_GLOBAL_ADMIN");

            default:
                throw new NoSuchElementException("This number don't maps to any role");
        }
    }

    public static int roleToNumber(String role){
        switch(role){
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
