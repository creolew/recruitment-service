package vn.unigap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;

import java.time.Instant;

public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(name= "email", unique = true, nullable = false, length = 255)
    private String email;
    @Column(name= "name", nullable = false, length = 255)
    private String name;
    @Column(name= "provinceId", nullable = false)
    private int provinceId;
    @Column(name= "description", nullable = false)
    private String description;

    @Column(name= "created_at", nullable = false)
    private Instant created_at;

    @Column(name= "updated_at", nullable = false)
    private Instant updated_at;


}
