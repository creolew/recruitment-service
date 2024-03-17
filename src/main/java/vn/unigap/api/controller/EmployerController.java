package vn.unigap.api.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.common.AppConstants;
import vn.unigap.api.common.GenericResponse;
import vn.unigap.api.common.HttpConstant;
import vn.unigap.api.dto.in.employer.EmployerDto;
import vn.unigap.api.dto.in.employer.EmployerResponseDto;
import vn.unigap.api.dto.out.EmployerResponse;
import vn.unigap.api.mapper.ApiCreatedResponseMapper;
import vn.unigap.api.dto.out.ApiCreatedResponse;
import vn.unigap.api.service.EmployerService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employers")
public class EmployerController {

    EmployerService employerService;

    @PostMapping
    public ResponseEntity<ApiCreatedResponse> addEmployer(@RequestBody EmployerDto employerDto) {

        EmployerDto savedEmployer = employerService.addEmployer(employerDto);
        GenericResponse genericResponse = new GenericResponse(HttpConstant.CREATED, savedEmployer);
        ApiCreatedResponse apiCreatedResponse= ApiCreatedResponseMapper.mapToGenericResponseDto(genericResponse);
        return  ResponseEntity.status(HttpStatus.CREATED).body(apiCreatedResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployerResponseDto> getEmployerById(@PathVariable(name = "id") long id){

        return ResponseEntity.ok(employerService.getEmployerById(id));

    }

    @GetMapping()
    public EmployerResponse getAllEmployers(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir

    ){

        return employerService.getAllEmployers(page, pageSize, sortBy, sortDir);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployerDto> updateEmployer(@Valid @RequestBody EmployerDto employerDto, @PathVariable(name = "id") long id){
        EmployerDto employerResponse = employerService.updateEmployer(employerDto, id);
        return new ResponseEntity<>(employerResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployerDto> deleteEmployer(@PathVariable(name = "id") long id){
        employerService.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
