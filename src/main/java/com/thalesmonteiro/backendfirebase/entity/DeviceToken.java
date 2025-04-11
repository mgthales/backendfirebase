package com.thalesmonteiro.backendfirebase.entity;




import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity

@Getter
@Setter
@Table(name = "devicetoken")
public class DeviceToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private String nomeaparelho;

    public DeviceToken() {}

    public DeviceToken(String token, String nomeaparelho) {
        this.token = token;
        this.nomeaparelho = nomeaparelho;
    }

    // getters e setters
}

