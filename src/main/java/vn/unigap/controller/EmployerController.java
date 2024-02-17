package vn.unigap.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.unigap.common.GenericResponse;
import vn.unigap.common.HttpConstant;
import vn.unigap.dto.EmployerDto;
import vn.unigap.mapper.ApiCreatedResponseMapper;
import vn.unigap.response.ApiCreatedResponse;
import vn.unigap.service.EmployerService;

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
}
