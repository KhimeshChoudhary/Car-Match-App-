package com.example.car_match.models;

public class AddressModel {
    String Name,Address,Phone_Number,Postal_Code,City;
    boolean isSelected;

    public AddressModel() {
    }

    public AddressModel(String name, String address, String phone_Number, String postal_Code, String city) {
        Name = name;
        Address = address;
        Phone_Number = phone_Number;
        Postal_Code = postal_Code;
        City = city;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public String getPostal_Code() {
        return Postal_Code;
    }

    public void setPostal_Code(String postal_Code) {
        Postal_Code = postal_Code;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}

