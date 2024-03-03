package vn.unigap.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employers")
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(name= "email", nullable = false, length = 255)
    private String email;
    @Column(name= "name", nullable = false, length = 255)
    private String name;
    @Column(name= "provinceId", nullable = false)
    private int provinceId;
    @Column(name= "description")
    private String description;

    @Column(name= "created_at")
    private Instant created_at;

    @Column(name= "updated_at")
    private Instant updated_at;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs;
}
