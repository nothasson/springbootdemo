package com.example.demo.entity;

import java.io.Serializable;

public class Shop implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long uid;
    private String name;
    private double score;
    private double price;
    private String address;
    private String phone;
    private boolean wifi;
    private String parking;
    private double lat;
    private double lon;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", price=" + price +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", wifi=" + wifi +
                ", parking='" + parking + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
