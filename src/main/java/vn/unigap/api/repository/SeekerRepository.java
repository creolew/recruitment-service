package vn.unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.unigap.api.entity.Seeker;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


import java.util.List;


public interface SeekerRepository extends JpaRepository<Seeker, Long> {

    @Query("SELECT s FROM Seeker s WHERE (:provinceId = -1 OR s.province.id = :provinceId)")
    Page<Seeker> filterByProvinceId(@Param("provinceId") int provinceId, Pageable pageable);
}
