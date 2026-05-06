package com.example.registrasi._0420230009.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProgramStudi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getNama(){return nama;}
    public void setNama(String nama){this.nama=nama;}
}