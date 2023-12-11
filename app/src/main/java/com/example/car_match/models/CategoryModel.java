package com.example.car_match.models;

import java.io.Serializable;

public class CategoryModel implements Serializable {

//   here what the variable name is there it should be same in your fire base store variable name also
    String img_url;
    String name;
    String type;
    public CategoryModel() {

    }
    public CategoryModel(String img_url,String name,String type){
        this.img_url=img_url;
        this.name=name;
        this.type=type;
    }
//    this is method is implemented

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
