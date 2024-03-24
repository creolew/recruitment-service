package vn.unigap.api.service;

import vn.unigap.api.dto.in.job.JobDto;
import vn.unigap.api.dto.out.job.JobResponseByEmployerIdDto;
import vn.unigap.api.dto.out.job.JobResponseDto;

public interface JobService {
    JobDto addJob(Long employerId, JobDto jobDto);

    JobDto updateJob(JobDto jobDto, long id);

    JobResponseDto getJobById(Long id);

    JobResponseByEmployerIdDto getAllJobsByEmployerId(int page, int pageSize, long employerId);

    void deleteByJobId(long jobId);

}
