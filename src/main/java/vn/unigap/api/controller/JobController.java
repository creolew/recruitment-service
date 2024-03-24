package vn.unigap.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.common.GenericResponse;
import vn.unigap.api.common.HttpConstant;
import vn.unigap.api.dto.in.employer.EmployerDto;
import vn.unigap.api.dto.in.job.JobDto;
import vn.unigap.api.dto.out.ApiCreatedResponse;
import vn.unigap.api.dto.out.job.JobResponseByEmployerIdDto;
import vn.unigap.api.dto.out.job.JobResponseDto;
import vn.unigap.api.mapper.ApiCreatedResponseMapper;
import vn.unigap.api.service.JobService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/jobs")
public class JobController {
    private JobService jobService;

    @PostMapping("/{employer_id}")
    public ResponseEntity<ApiCreatedResponse> addJob(@RequestBody JobDto jobDto,
                                                     @PathVariable("employer_id") Long employer_id) {

       JobDto savedJob = jobService.addJob(employer_id, jobDto);
       GenericResponse genericResponse = new GenericResponse(HttpConstant.CREATED, savedJob);
       ApiCreatedResponse apiCreatedResponse= ApiCreatedResponseMapper.mapToGenericResponseDto(genericResponse);
       return  ResponseEntity.status(HttpStatus.CREATED).body(apiCreatedResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<JobDto> updateJob(@Valid @RequestBody JobDto jobDto, @PathVariable(name = "id") long id){
        JobDto jobResponse = jobService.updateJob(jobDto, id);
        return new ResponseEntity<>(jobResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDto> getJobById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(jobService.getJobById(id));

    }

    @GetMapping()
    public JobResponseByEmployerIdDto getAllJobs(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "employerId") long employerId

    ){

        return jobService.getAllJobsByEmployerId(page, pageSize, employerId);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployerDto> deleteEmployer(@PathVariable(name = "id") long id){
        jobService.deleteByJobId(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
