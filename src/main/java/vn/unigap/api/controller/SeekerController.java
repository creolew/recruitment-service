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
import vn.unigap.api.dto.in.seeker.CreateSeekerInDto;
import vn.unigap.api.dto.in.seeker.UpdateSeekerInDto;
import vn.unigap.api.dto.out.ApiCreatedResponse;
import vn.unigap.api.dto.out.EmployerResponse;
import vn.unigap.api.dto.out.seeker.SeekerReponseGetAllDto;
import vn.unigap.api.dto.out.seeker.SeekerResponseBaseDto;
import vn.unigap.api.dto.out.seeker.SeekerGetResponseDto;
import vn.unigap.api.mapper.ApiCreatedResponseMapper;
import vn.unigap.api.service.SeekerService;



@RestController
@RequestMapping("/api/seekers")
@AllArgsConstructor
public class SeekerController {

    SeekerService seekerService;
    @PostMapping
    public ResponseEntity<ApiCreatedResponse> createSeeker(@RequestBody CreateSeekerInDto createSeekerInDto) {

        SeekerResponseBaseDto savedSeeker = seekerService.createSeeker(createSeekerInDto);

        GenericResponse genericResponse = new GenericResponse(HttpConstant.CREATED, savedSeeker);
        ApiCreatedResponse apiCreatedResponse= ApiCreatedResponseMapper.mapToGenericResponseDto(genericResponse);
        return  ResponseEntity.status(HttpStatus.CREATED).body(apiCreatedResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiCreatedResponse> updateSeeker(@Valid @RequestBody UpdateSeekerInDto updateSeekerInDto, @PathVariable(name = "id") long id){
        SeekerResponseBaseDto savedSeeker = seekerService.updateSeeker(id, updateSeekerInDto);

        GenericResponse genericResponse = new GenericResponse(HttpConstant.OK, savedSeeker);
        ApiCreatedResponse apiCreatedResponse= ApiCreatedResponseMapper.mapToGenericResponseDto(genericResponse);
        return  ResponseEntity.status(HttpStatus.OK).body(apiCreatedResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiCreatedResponse>getEmployerById(@PathVariable(name = "id") long id){
        SeekerGetResponseDto savedSeeker = seekerService.getSeekerById(id);
        GenericResponse genericResponse = new GenericResponse(HttpConstant.OK, savedSeeker);
        ApiCreatedResponse apiCreatedResponse= ApiCreatedResponseMapper.mapToGenericResponseDto(genericResponse);

        return ResponseEntity.status(HttpStatus.OK).body(apiCreatedResponse);

    }


    @GetMapping()
    public SeekerReponseGetAllDto getAllSeekers(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "provinceId") int provinceId
    ){

        return seekerService.getAllSeekers(page, pageSize, provinceId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SeekerGetResponseDto> deleteEmployer(@PathVariable(name = "id") long id){
        seekerService.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
