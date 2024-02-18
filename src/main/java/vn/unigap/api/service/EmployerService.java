package vn.unigap.api.service;

import vn.unigap.api.dto.in.EmployerDto;
import vn.unigap.api.dto.in.EmployerResponseDto;
import vn.unigap.api.dto.out.EmployerResponse;


public interface EmployerService {
    EmployerDto addEmployer(EmployerDto employerDto);

    EmployerResponseDto getEmployerById(long id);

    EmployerResponse getAllEmployers(int page, int pageSize, String sortBy, String sortDir);

    EmployerDto updateEmployer(EmployerDto employerDto, long id);

    void deleteById(long id);
}
