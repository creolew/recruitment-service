package vn.unigap.api.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.employer.EmployerDto;
import vn.unigap.api.dto.in.employer.EmployerResponseDto;
import vn.unigap.api.dto.out.EmployerResponse;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.exception.EmailAlreadyExistException;
import vn.unigap.api.exception.ResourceNotFoundException;
import vn.unigap.api.mapper.EmployerMapper;
import vn.unigap.api.repository.EmployerRepository;
import vn.unigap.api.repository.ProvinceRepository;
import vn.unigap.api.service.EmployerService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import vn.unigap.api.service.ProvinceService;

@Service
@AllArgsConstructor
public class EmployerServiceImpl implements EmployerService {

    EmployerRepository employerRepository;

    ProvinceRepository provinceRepository;

    ProvinceService provinceService;

    @Override
    public EmployerDto addEmployer(EmployerDto employerDto) {

        if (employerRepository.existsByEmail(employerDto.getEmail())) {
            throw new EmailAlreadyExistException(HttpStatus.BAD_REQUEST, employerDto.getEmail());
        }

//        if(!provinceRepository.existsById(employerDto.getProvinceId())) {
//            throw new ResourceNotFoundException("Province", "id", employerDto.getProvinceId());
//        }
        if (!provinceService.checkExistProvince(employerDto.getProvinceId())) {
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
        EmployerResponseDto employerResponseDto = EmployerMapper.mapToEmployResponseDto(employerDto);
        employerResponseDto.setProvinceName(provinceService.getNameById(employerDto.getProvinceId()));
        return employerResponseDto;
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

        List<EmployerResponseDto> content = listOfEmployersDto.stream()
                .map(employer -> EmployerMapper.mapToEmployResponseDto(employer))
                .collect(Collectors.toList());

        for(EmployerResponseDto employerResponseDto : content){
            employerResponseDto.setProvinceName(provinceService.getNameById(employerResponseDto.getProvinceId()));
        }

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
