package com.example.qualifiedwork.adminAccount;

public class PatDirectionRecord {
    String date_of_direction, type_of_direction, second_name, name, father_name, respons_status;

    public PatDirectionRecord(String date_of_direction, String type_of_direction, String second_name, String name, String father_name, String respons_status) {
        this.date_of_direction = date_of_direction;
        this.type_of_direction = type_of_direction;
        this.second_name = second_name;
        this.name = name;
        this.father_name = father_name;
        this.respons_status = respons_status;
    }

    public String getDate_of_direction() {
        return date_of_direction;
    }

    public void setDate_of_direction(String date_of_direction) {
        this.date_of_direction = date_of_direction;
    }

    public String getType_of_direction() {
        return type_of_direction;
    }

    public void setType_of_direction(String type_of_direction) {
        this.type_of_direction = type_of_direction;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getRespons_status() {
        return respons_status;
    }

    public void setRespons_status(String respons_status) {
        this.respons_status = respons_status;
    }
}
