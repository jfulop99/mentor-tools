package com.training360.mentortools.registration;

import com.training360.mentortools.student.Student;
import com.training360.mentortools.student.StudentService;
import com.training360.mentortools.trainingclass.TrainingClass;
import com.training360.mentortools.trainingclass.TrainingClassService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private TrainingClassService trainingClassService;
    private StudentService studentService;

    private ModelMapper modelMapper;

    public List<RegisteredStudentDto> getStudentRegistrations(Long id) {
        List<Registration> registrations = registrationRepository.findRegistrationByTrainingClass_Id(id);
        return registrations.stream()
                .map(registration -> modelMapper.map(registration, RegisteredStudentDto.class))
                .toList();
    }

    @Transactional
    public RegisteredStudentDto createRegistrationWithStudent(Long id, CreateRegistrationCommand command) {
        Optional<Registration> registrationOptional = registrationRepository.findRegistrationByTrainingClass_IdAndStudent_Id(id, command.getStudentId());
        if (registrationOptional.isPresent()){
            throw new RecordAlreadyExistsException(URI.create("registration/exists"), "Registration already exists");
        }
        TrainingClass trainingClass = trainingClassService.findTrainingClassById(id);
        Student student = studentService.findStudentById(command.getStudentId());
        Registration registration = new Registration(RegistrationStatus.ACTIVE);
        trainingClass.addRegistration(registration);
        student.addRegistration(registration);
        registrationRepository.save(registration);
        return modelMapper.map(registration, RegisteredStudentDto.class);
    }

    public List<RegisteredClassDto> getClassRegistration(Long id) {
        List<Registration> registrations = registrationRepository.findRegistrationByStudent_Id(id);
        return registrations.stream()
                .map(registration -> modelMapper.map(registration.getTrainingClass(), RegisteredClassDto.class))
                .toList();
    }

    @Transactional
    public RegisteredStudentDto updateRegistration(Long id, UpdateRegistrationCommand command) {
        Optional<Registration> registrationOptional = registrationRepository.findRegistrationByTrainingClass_IdAndStudent_Id(id, command.getStudentId());
        Registration registration = registrationOptional.orElseThrow(() -> new RecordNotFoundException(URI.create("registration/not-found"), "Registration not found"));
        registration.setRegistrationStatus(command.getStatus());
        registrationRepository.save(registration);
        return modelMapper.map(registration, RegisteredStudentDto.class);
    }
}
