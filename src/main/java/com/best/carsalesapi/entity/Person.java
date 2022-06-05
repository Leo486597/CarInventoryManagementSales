package com.best.carsalesapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private Enum Role;

    private String name;

    private String gender;

    private int age;
}
