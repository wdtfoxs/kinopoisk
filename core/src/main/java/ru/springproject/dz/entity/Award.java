package ru.springproject.dz.entity;

import javax.persistence.*;

@Entity
@Table
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
