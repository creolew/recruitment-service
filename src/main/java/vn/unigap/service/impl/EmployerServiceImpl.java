package vn.unigap.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.unigap.common.ProvinceEnum;
import vn.unigap.dto.in.EmployerDto;
import vn.unigap.dto.in.EmployerResponseDto;
import vn.unigap.dto.out.EmployerResponse;
import vn.unigap.entity.Employer;
import vn.unigap.exception.EmailAlreadyExistException;
import vn.unigap.exception.ResourceNotFoundException;
import vn.unigap.mapper.EmployerMapper;
import vn.unigap.repository.EmployerRepository;
import vn.unigap.service.EmployerService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
@Service
@AllArgsConstructor
public class EmployerServiceImpl implements EmployerService {

    EmployerRepository employerRepository;

    @Override
    public EmployerDto addEmployer(EmployerDto employerDto) {

        if(employerRepository.existsByEmail(employerDto.getEmail())){
            throw new EmailAlreadyExistException(HttpStatus.BAD_REQUEST, employerDto.getEmail());
        }

        //check if provinceId exists
        boolean isProvinceIdValid = ProvinceEnum.getProvinceMap().containsKey(employerDto.getProvinceId());
        if(!isProvinceIdValid){
            throw new ResourceNotFoundException("Province", "id", employerDto.getProvinceId());
        }

        Employer employer = EmployerMapper.mapToEmployer(employerDto);

        employer.setCreated_at(Instant.now());

        employer.setUpdated_at(Instant.now());

        Employer savedEmployer = employerRepository.save(employer);

        EmployerDto savedEmployerDto = EmployerMapper.mapToEmployerDto(savedEmployer);

        return savedEmployerDto;
    }

    @Override
    public EmployerResponseDto getEmployerById(long id) {

        Employer employer = employerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employer", "id", id)
        );

        EmployerDto employerDto = EmployerMapper.mapToEmployerDto(employer);
        return EmployerMapper.mapToEmployResponseDto(employerDto);
    }

    @Override
    public EmployerResponse getAllEmployers(int page, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Page<Employer> employers = employerRepository.findAll(pageable);

        // get content for page object
        List<Employer> listOfEmployers = employers.getContent();

        List<EmployerDto> listOfEmployersDto = listOfEmployers.stream()
                .map(employer -> EmployerMapper.mapToEmployerDto(employer))
                .collect(Collectors.toList());

        List<EmployerResponseDto> content= listOfEmployersDto.stream()
                .map(employer -> EmployerMapper.mapToEmployResponseDto(employer))
                .collect(Collectors.toList());

        EmployerResponse employerResponse = new EmployerResponse();
        employerResponse.setData(content);
        employerResponse.setPage(employers.getNumber());
        employerResponse.setPageSize(employers.getSize());
        employerResponse.setTotalElements(employers.getTotalElements());
        employerResponse.setTotalPages(employers.getTotalPages());
        employerResponse.setLast(employers.isLast());


        return employerResponse;
    }

    @Override
    public EmployerDto updateEmployer(EmployerDto employerDto, long id) {
        //check if the employer existed
        Employer employer = employerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employer", "id", id)
        );

        employer.setId(id);
        employer.setName(employerDto.getName());
        employer.setDescription(employerDto.getDescription());
        employer.setProvinceId(employerDto.getProvinceId());
        employer.setUpdated_at(Instant.now());


        Employer updatedEmployer = employerRepository.save(employer);

        return EmployerMapper.mapToEmployerDto(updatedEmployer);
    }

    @Override
    public void deleteById(long id) {
        //check if the employer existed
        Employer employer = employerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employer", "id", id)
        );

        employerRepository.deleteById(id);
    }


}
