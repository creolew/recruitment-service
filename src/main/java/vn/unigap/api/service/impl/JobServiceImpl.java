package vn.unigap.api.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.job.JobDto;
import vn.unigap.api.dto.out.JobByEmployerIdDto;
import vn.unigap.api.dto.out.JobResponseByEmployerIdDto;
import vn.unigap.api.dto.out.JobResponseDto;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.entity.Job;
import vn.unigap.api.exception.InvalidParamException;
import vn.unigap.api.exception.ResourceNotFoundException;
import vn.unigap.api.mapper.JobMapper;
import vn.unigap.api.repository.EmployerRepository;
import vn.unigap.api.repository.JobRepository;
import vn.unigap.api.service.JobService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    private EmployerRepository employerRepository;

    @Override
    public JobDto addJob(Long employerId, JobDto jobDto) {
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employer", "id", employerId)
                );

        Job job = Job.builder()
                .title(jobDto.getTitle())
                .employer(employer)
                .quantity(jobDto.getQuantity())
                .description(jobDto.getDescription())
                .salary(jobDto.getSalary())
                .expired_at(jobDto.getExpired_at())
                .build();
        jobRepository.save(job);
        return jobDto;
    }

    @Override
    public JobDto updateJob(JobDto jobDto, long id) {
        Job existingJob = jobRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Job", "id", id)
        );

        existingJob.setTitle(jobDto.getTitle());
        existingJob.setQuantity(jobDto.getQuantity());
        existingJob.setDescription(jobDto.getDescription());
        existingJob.setSalary(jobDto.getSalary());
        existingJob.setExpired_at(jobDto.getExpired_at());

        jobRepository.save(existingJob);

        return jobDto;
    }

    @Override
    public JobResponseDto getJobById(Long id) {
        Job existingJob = jobRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Job", "id", id)
        );

        JobResponseDto jobResponseDto = JobMapper.mapToJobResponseDto(existingJob);

        return jobResponseDto;
    }

    @Override
    public JobResponseByEmployerIdDto getAllJobsByEmployerId(int page,
                                                           int pageSize,
                                                           long employerId) {
        if(page <= 0){
            throw new InvalidParamException("Page","page", page);
        }
        if(pageSize >= 500){
            throw new InvalidParamException("PageSize","pageSize", pageSize);
        }

        //get job list
        List<Job> jobs = new ArrayList<>();
        List<Job> sortedJobs = new ArrayList<>();
        if(employerId == -1){
            jobs = jobRepository.findAll();
            Comparator<Job> byExpiredAtDescending = Comparator.comparing(Job::getExpired_at).reversed();
            Comparator<Job> byEmployerNameAscending = Comparator.comparing(job -> job.getEmployer().getName());
            sortedJobs = jobs.stream()
                                    .sorted(byExpiredAtDescending
                                    .thenComparing(byEmployerNameAscending))
                                    .collect(Collectors.toList());
        }else{
            jobs = jobRepository.findByEmployerId(employerId);
            Comparator<Job> byExpiredAtDescending = Comparator.comparing(Job::getExpired_at).reversed();
            sortedJobs = jobs.stream()
                    .sorted(byExpiredAtDescending)
                    .collect(Collectors.toList());
        }

        //paging
        if(pageSize >= jobs.size()){
            pageSize = jobs.size();
        }
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize - 1, sortedJobs.size());

        if (startIndex >= jobs.size()) {
            throw new InvalidParamException("_","_", -1); // Trang không hợp lệ
        }

        if(endIndex >= jobs.size() -1 ){
            endIndex = jobs.size() - 1;
        }

        int totalPages = (int) Math.ceil((double) jobs.size() / pageSize); // Tính toán số trang tổng
        List<Job> jobPaging= sortedJobs.subList(startIndex,endIndex + 1);

        List<JobByEmployerIdDto> dataList = jobPaging.stream()
                .map(jp -> JobMapper.mapToJobResponseByEmployerIdDto(jp))
                .toList();

        JobResponseByEmployerIdDto jobResponseByEmployerIdDto = new JobResponseByEmployerIdDto();
        jobResponseByEmployerIdDto.setData(dataList);
        jobResponseByEmployerIdDto.setPage(page);
        jobResponseByEmployerIdDto.setPageSize(endIndex - startIndex + 1);
        jobResponseByEmployerIdDto.setTotalElements(jobs.size());
        jobResponseByEmployerIdDto.setTotalPages(totalPages);


        return jobResponseByEmployerIdDto;
    }

    @Override
    public void deleteByJobId(long jobId) {
        Job existingJob = jobRepository.findById(jobId).orElseThrow(
                () -> new ResourceNotFoundException("Job", "id", jobId)
        );
        jobRepository.deleteById(jobId);
    }


}
