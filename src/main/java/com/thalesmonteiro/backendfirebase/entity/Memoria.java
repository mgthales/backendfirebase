package com.thalesmonteiro.backendfirebase.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Memoria {
    @Id
    @GeneratedValue
    private Long id;

    private String titulo;
    private String descricao;
    private String data;
    private String categoria;
    private boolean favorito;

    // getters e setters
}

