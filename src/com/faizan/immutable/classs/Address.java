package com.faizan.immutable.classs;

public class Address {
    private String city;

    public Address(String city) {
        this.city = city;
    }

    public Address(Address other) {
        this.city = other.city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
