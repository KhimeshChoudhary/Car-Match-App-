package com.example.car_match.models;

public class MyCartModel
{

    String CarName;
    String CarPrice;
    String CurrentTime;
    String CurrentDate;




    public MyCartModel() {
    }



    public MyCartModel(String carName, String carPrice,  String currentTime, String currentDate) {
        this.CarName = carName;
        this.CarPrice = carPrice;
        this.CurrentTime = currentTime;
        this.CurrentDate = currentDate;
    }

    public String getCarName() {
        return CarName;
    }

    public void setCarName(String carName) {
        CarName = carName;
    }

    public String getCarPrice() {
        return CarPrice;
    }

    public void setCarPrice(String carPrice) {
        CarPrice = carPrice;
    }



    public String getCurrentTime() {
        return CurrentTime;
    }

    public void setCurrentTime(String currentTime) {
        CurrentTime = currentTime;
    }

    public String getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(String currentDate) {
        CurrentDate = currentDate;
    }



}
