package vn.unigap.service;

import vn.unigap.dto.in.EmployerDto;
import vn.unigap.dto.in.EmployerResponseDto;
import vn.unigap.dto.out.EmployerResponse;


public interface EmployerService {
    EmployerDto addEmployer(EmployerDto employerDto);

    EmployerResponseDto getEmployerById(long id);

    EmployerResponse getAllEmployers(int page, int pageSize, String sortBy, String sortDir);

    EmployerDto updateEmployer(EmployerDto employerDto, long id);

    void deleteById(long id);
}
