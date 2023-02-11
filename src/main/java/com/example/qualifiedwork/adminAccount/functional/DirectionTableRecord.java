package com.example.qualifiedwork.adminAccount.functional;

public class DirectionTableRecord {
    String date_of_direction, type_of_direction, second_name, name, father_name, birth_date, polis_card;

    public DirectionTableRecord(String date_of_direction, String type_of_direction, String second_name, String name, String father_name, String birth_date, String polis_card) {
        this.date_of_direction = date_of_direction;
        this.type_of_direction = type_of_direction;
        this.second_name = second_name;
        this.name = name;
        this.father_name = father_name;
        this.birth_date = birth_date;
        this.polis_card = polis_card;
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

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getPolis_card() {
        return polis_card;
    }

    public void setPolis_card(String polis_card) {
        this.polis_card = polis_card;
    }
}
