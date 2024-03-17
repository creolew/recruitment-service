package vn.unigap.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "title", nullable = false)
    private String title;

    @Column(name= "quantity", nullable = false)
    private int quantity;

    @Column(name= "description", nullable = false)
    private String description;

    @Column(name= "salary", nullable = false)
    private int salary;

    @Column(name= "created_at")
    private Instant created_at;

    @Column(name= "updated_at")
    private Instant updated_at;

    @Column(name= "expired_at", nullable = false)
    private Date expired_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobField_id", nullable = false)
    private JobField jobField;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer;


}
