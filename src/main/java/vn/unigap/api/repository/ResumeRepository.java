package vn.unigap.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.unigap.api.entity.Resume;
import vn.unigap.api.entity.Seeker;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query("SELECT r FROM Resume r WHERE (:seekerId = -1 OR r.seeker.id = :seekerId)")
    Page<Resume> filterBySeekerId(@Param("seekerId") int seekerId, Pageable pageable);
}
