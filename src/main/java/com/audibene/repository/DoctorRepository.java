package com.audibene.repository;

import com.audibene.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
