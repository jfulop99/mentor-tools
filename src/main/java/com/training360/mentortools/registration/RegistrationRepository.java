package com.training360.mentortools.registration;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<Registration> findRegistrationByTrainingClass_Id(Long id);

    Optional<Registration> findRegistrationByTrainingClass_IdAndStudent_Id(Long trainingClassId, Long studentId);

    List<Registration> findRegistrationByStudent_Id(Long id);
}
