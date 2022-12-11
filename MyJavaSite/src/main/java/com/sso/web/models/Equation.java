package com.sso.web.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String equation, coefficients, surfaceType;

    public String getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(String coefficients) {
        this.coefficients = coefficients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }


    public String getSurfaceType() {
        return surfaceType;
    }

    public void setSurfaceType(String surfaceType) {
        this.surfaceType = surfaceType;
    }

    public Equation() {}

    public Equation(String equation, String coefficients, String surfaceType) {
        this.equation = equation;
        this.coefficients = coefficients;
        this.surfaceType = surfaceType;
    }
}
