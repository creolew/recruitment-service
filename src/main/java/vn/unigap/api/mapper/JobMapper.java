package vn.unigap.api.mapper;

import org.modelmapper.ModelMapper;
import vn.unigap.api.dto.in.job.JobDto;
import vn.unigap.api.dto.out.JobByEmployerIdDto;
import vn.unigap.api.dto.out.JobResponseDto;
import vn.unigap.api.entity.Job;

public class JobMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static JobDto mapToJobDto(Job job) {
        JobDto jobDto = mapper.map(job, JobDto.class);
        return jobDto;
    }

    public static JobResponseDto mapToJobResponseDto(Job job){
        JobResponseDto jobResponseDto = mapper.map(job, JobResponseDto.class);
        return jobResponseDto;
    }

    public static JobByEmployerIdDto mapToJobResponseByEmployerIdDto(Job job){
        JobByEmployerIdDto jobResponseByEmployerIdDto = mapper.map(job, JobByEmployerIdDto.class);
        return jobResponseByEmployerIdDto;
    }
}
