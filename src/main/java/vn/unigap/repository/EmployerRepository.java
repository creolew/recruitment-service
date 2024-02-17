package vn.unigap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unigap.entity.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Boolean existsByEmail(String email);
}
