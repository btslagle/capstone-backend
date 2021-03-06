package com.bslagle.easyxferapi.vehicle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import lombok.Data;

@Entity
@Data
@Table(name = "vehicle")
public class Vehicle {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  
  @Column(name = "image_url")
  private String imageUrl;
  
  @Column(name = "make")
  private String make;

  @Column(name = "model")
  private String model;

  @Column(name = "year")
  private String  year;

  @Column(name = "condition")
  private String condition;

  @Column(name = "term")
  private String term;

  @Column(name = "monthly")
  private  String  monthly;

  @Column(name = "mileage")
  private String mileage;

  @Column(name = "location")
  private String location;
}
