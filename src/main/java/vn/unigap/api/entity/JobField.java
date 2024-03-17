package vn.unigap.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_fields")
public class JobField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "name", length = 255)
    private String name;

    @Column(name= "slug", length = 255)
    private String slug;

    @OneToMany(mappedBy = "jobField", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs;
}
