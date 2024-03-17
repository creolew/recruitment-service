package vn.unigap.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String career_obj;

    private String title;

    private int salary;

    @Column(name= "created_at")
    private Instant created_at;

    @Column(name= "updated_at")
    private Instant updated_at;

    @PrePersist
    protected void onCreate() {
        created_at = Instant.now();
        updated_at = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = Instant.now();
    }



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seeker_id", nullable = false)
    private Seeker seeker;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "resumes_jobFields",
            joinColumns = @JoinColumn(name = "resume_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "jobField_id", referencedColumnName = "id")
    )
    private Set<JobField> fields;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "resumes_provinces",
            joinColumns = @JoinColumn(name = "resume_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "province_id", referencedColumnName = "id")
    )
    private Set<Province> provinces;






}
