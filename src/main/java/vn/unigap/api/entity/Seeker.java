package vn.unigap.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seekers")
@Builder
public class Seeker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name= "name", nullable = false)
    private String name;

    @Column(name= "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name= "address", nullable = false)
    private String address;

    @Column(name= "updated_at")
    private Instant updated_at;

    @Column(name= "created_at", nullable = false)
    private Instant created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @OneToMany(mappedBy = "seeker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resume> resumes;

    @PrePersist
    protected void onCreate() {
        created_at = Instant.now();
        updated_at = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = Instant.now();
    }
}
