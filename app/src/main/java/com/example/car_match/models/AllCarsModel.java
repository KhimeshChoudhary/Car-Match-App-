package com.example.car_match.models;

import java.io.Serializable;

public class AllCarsModel implements Serializable {
    String Name;
    String acceleration;
    String description;
    String fuel_type;
    String img_url;
    String price;
    String type;
    String engine;

    String transmission_type,fuel_tank_capacity,seating_capacity,power_torque,top_speed;
     String power_steering,power_window_front,anti_lock_breaking_system,air_conditioner,driver_airbag;


    public  AllCarsModel(){

    }


    public AllCarsModel(String transmission_type, String fuel_tank_capacity, String seating_capacity, String power_torque, String top_speed,String power_steering, String power_window_front, String anti_lock_breaking_system, String air_conditioner, String driver_airbag) {
        this.transmission_type = transmission_type;
        this.fuel_tank_capacity = fuel_tank_capacity;
        this.seating_capacity = seating_capacity;
        this.power_torque = power_torque;
        this.top_speed = top_speed;
        this.power_steering = power_steering;
        this.power_window_front = power_window_front;
        this.anti_lock_breaking_system = anti_lock_breaking_system;
        this.air_conditioner = air_conditioner;
        this.driver_airbag = driver_airbag;
    }

    public AllCarsModel(String name, String acceleration, String description, String fuel_type, String img_url, String price, String type, String engine) {
        Name = name;
        this.acceleration = acceleration;
        this.description = description;
        this.fuel_type = fuel_type;
        this.img_url = img_url;
        this.price = price;
        this.engine=engine;
        this.type=type;


    }

    public String getPower_steering() {
        return power_steering;
    }

    public void setPower_steering(String power_steering) {
        this.power_steering = power_steering;
    }

    public String getPower_window_front() {
        return power_window_front;
    }

    public void setPower_window_front(String power_window_front) {
        this.power_window_front = power_window_front;
    }

    public String getAnti_lock_breaking_system() {
        return anti_lock_breaking_system;
    }

    public void setAnti_lock_breaking_system(String anti_lock_breaking_system) {
        this.anti_lock_breaking_system = anti_lock_breaking_system;
    }

    public String getAir_conditioner() {
        return air_conditioner;
    }

    public void setAir_conditioner(String air_conditioner) {
        this.air_conditioner = air_conditioner;
    }

    public String getDriver_airbag() {
        return driver_airbag;
    }

    public void setDriver_airbag(String driver_airbag) {
        this.driver_airbag = driver_airbag;
    }

    public String getTransmission_type() {
        return transmission_type;
    }

    public void setTransmission_type(String transmission_type) {
        this.transmission_type = transmission_type;
    }

    public String getFuel_tank_capacity() {
        return fuel_tank_capacity;
    }

    public void setFuel_tank_capacity(String fuel_tank_capacity) {
        this.fuel_tank_capacity = fuel_tank_capacity;
    }

    public String getSeating_capacity() {
        return seating_capacity;
    }

    public void setSeating_capacity(String seating_capacity) {
        this.seating_capacity = seating_capacity;
    }

    public String getPower_torque() {
        return power_torque;
    }

    public void setPower_torque(String power_torque) {
        this.power_torque = power_torque;
    }

    public String getTop_speed() {
        return top_speed;
    }

    public void setTop_speed(String top_speed) {
        this.top_speed = top_speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(String acceleration) {
        this.acceleration = acceleration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
