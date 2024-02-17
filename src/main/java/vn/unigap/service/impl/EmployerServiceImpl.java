package vn.unigap.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.unigap.dto.EmployerDto;
import vn.unigap.entity.Employer;
import vn.unigap.exception.EmailAlreadyExistException;
import vn.unigap.mapper.EmployerMapper;
import vn.unigap.repository.EmployerRepository;
import vn.unigap.service.EmployerService;

import java.time.Instant;

@Service
@AllArgsConstructor
public class EmployerServiceImpl implements EmployerService {

    EmployerRepository employerRepository;
    @Override
    public EmployerDto addEmployer(EmployerDto employerDto) {

        if(employerRepository.existsByEmail(employerDto.getEmail())){
            throw new EmailAlreadyExistException(HttpStatus.BAD_REQUEST, employerDto.getEmail());
        }

        Employer employer = EmployerMapper.mapToEmployer(employerDto);

        employer.setCreated_at(Instant.now());

        employer.setUpdated_at(Instant.now());

        Employer savedEmployer = employerRepository.save(employer);

        EmployerDto savedEmployerDto = EmployerMapper.mapToEmployerDto(savedEmployer);

        return savedEmployerDto;
    }
}
