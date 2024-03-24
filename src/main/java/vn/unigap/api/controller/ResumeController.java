package vn.unigap.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.common.GenericResponse;
import vn.unigap.api.common.HttpConstant;
import vn.unigap.api.dto.in.resume.ResumeBaseInDto;
import vn.unigap.api.dto.in.seeker.CreateSeekerInDto;
import vn.unigap.api.dto.in.seeker.UpdateSeekerInDto;
import vn.unigap.api.dto.out.ApiCreatedResponse;
import vn.unigap.api.dto.out.resume.ResumeResponseBaseDto;
import vn.unigap.api.dto.out.resume.ResumeResponseGetAllDto;
import vn.unigap.api.dto.out.resume.ResumeResponseGetByIdDto;
import vn.unigap.api.dto.out.seeker.SeekerGetResponseDto;
import vn.unigap.api.dto.out.seeker.SeekerReponseGetAllDto;
import vn.unigap.api.dto.out.seeker.SeekerResponseBaseDto;
import vn.unigap.api.mapper.ApiCreatedResponseMapper;
import vn.unigap.api.service.ResumeService;

@RestController
@RequestMapping("/api/resumes")
@AllArgsConstructor
public class ResumeController {
    ResumeService resumeService;

    @PostMapping
    public ResponseEntity<ApiCreatedResponse> createResume(@RequestBody ResumeBaseInDto resumeBaseInDto) {

        ResumeResponseBaseDto savedResume = resumeService.createResume(resumeBaseInDto);

        GenericResponse genericResponse = new GenericResponse(HttpConstant.CREATED, savedResume);
        ApiCreatedResponse apiCreatedResponse= ApiCreatedResponseMapper.mapToGenericResponseDto(genericResponse);
        return  ResponseEntity.status(HttpStatus.CREATED).body(apiCreatedResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiCreatedResponse> updateResume(@Valid @RequestBody ResumeBaseInDto resumeBaseInDto,
                                                           @PathVariable(name = "id") long id){
        ResumeResponseBaseDto savedResume = resumeService.updateResume(resumeBaseInDto, id);

        GenericResponse genericResponse = new GenericResponse(HttpConstant.OK, savedResume);
        ApiCreatedResponse apiCreatedResponse= ApiCreatedResponseMapper.mapToGenericResponseDto(genericResponse);
        return  ResponseEntity.status(HttpStatus.OK).body(apiCreatedResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiCreatedResponse>getResumeById(@PathVariable(name = "id") long id){
        ResumeResponseGetByIdDto savedResume = resumeService.getResumeById(id);
        GenericResponse genericResponse = new GenericResponse(HttpConstant.OK, savedResume);
        ApiCreatedResponse apiCreatedResponse= ApiCreatedResponseMapper.mapToGenericResponseDto(genericResponse);

        return ResponseEntity.status(HttpStatus.OK).body(apiCreatedResponse);

    }

    @GetMapping()
    public ResumeResponseGetAllDto getAllResumes(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "seekerId") int seekerId
    ){

        return resumeService.getAllResumes(page, pageSize, seekerId);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResumeResponseBaseDto> deleteResume(@PathVariable(name = "id") long id){
        resumeService.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
