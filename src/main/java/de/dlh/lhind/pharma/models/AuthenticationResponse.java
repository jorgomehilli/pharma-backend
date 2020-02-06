package de.dlh.lhind.pharma.models;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private final String jwt;
    private final String firstName;

    public AuthenticationResponse(String jwt, String firstName) {
        this.jwt = jwt;
        this.firstName = firstName;
    }

    public String getJwt() {
        return jwt;
    }

    public String getFirstName() {
        return firstName;
    }
}