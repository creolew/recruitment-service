package vn.unigap.api.service;

import vn.unigap.api.dto.in.JobDto;
import vn.unigap.api.dto.out.JobByEmployerIdDto;
import vn.unigap.api.dto.out.JobResponseByEmployerIdDto;
import vn.unigap.api.dto.out.JobResponseDto;

import java.util.List;

public interface JobService {
    JobDto addJob(Long employerId, JobDto jobDto);

    JobDto updateJob(JobDto jobDto, long id);

    JobResponseDto getJobById(Long id);

    JobResponseByEmployerIdDto getAllJobsByEmployerId(int page, int pageSize, long employerId);

    void deleteByJobId(long jobId);

}
