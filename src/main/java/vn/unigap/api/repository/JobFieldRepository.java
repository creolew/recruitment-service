package vn.unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.entity.JobField;

public interface JobFieldRepository extends JpaRepository<JobField, Long> {
}
