package vn.unigap.api.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.resume.ResumeBaseInDto;
import vn.unigap.api.dto.out.resume.ResumeResponseBaseDto;
import vn.unigap.api.dto.out.resume.ResumeResponseDataDto;
import vn.unigap.api.dto.out.resume.ResumeResponseGetAllDto;
import vn.unigap.api.dto.out.resume.ResumeResponseGetByIdDto;
import vn.unigap.api.dto.out.seeker.SeekerReponseGetAllDto;
import vn.unigap.api.entity.JobField;
import vn.unigap.api.entity.Province;
import vn.unigap.api.entity.Resume;
import vn.unigap.api.entity.Seeker;
import vn.unigap.api.exception.ResourceNotFoundException;
import vn.unigap.api.mapper.ResumeMapper;
import vn.unigap.api.repository.JobFieldRepository;
import vn.unigap.api.repository.ProvinceRepository;
import vn.unigap.api.repository.ResumeRepository;
import vn.unigap.api.repository.SeekerRepository;
import vn.unigap.api.service.ResumeService;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    ProvinceRepository provinceRepository;

    SeekerRepository seekerRepository;

    JobFieldRepository jobFieldRepository;

    ResumeRepository resumeRepository;


    @Override
    public ResumeResponseBaseDto createResume(ResumeBaseInDto resumeBaseInDto) {

        Set<Long> setProvinceIds = resumeBaseInDto.getProvinceId();
        Set<Integer> convertedProvinceIds = setProvinceIds.stream()
                .map(Long::intValue) // Chuyển đổi từ Long sang Integer
                .collect(Collectors.toSet());

        Set<Province> setProvinces = convertedProvinceIds.stream()
                .map(
                        provinceId -> provinceRepository.findById(provinceId)
                                .orElseThrow(() -> new ResourceNotFoundException("Province", "id", provinceId))
                )
                .collect(Collectors.toSet());


        Seeker seeker = seekerRepository.findById(resumeBaseInDto.getSeekerId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Seeker", "id", resumeBaseInDto.getSeekerId())
                );

        Set<Long> setJobFieldIds = resumeBaseInDto.getJobFieldId();
        Set<JobField> setJobFields = setJobFieldIds.stream()
                .map(
                        fieldId -> jobFieldRepository.findById(fieldId)
                                .orElseThrow(() -> new ResourceNotFoundException("Job Field", "id", fieldId))
                )
                .collect(Collectors.toSet());

        Resume resume = Resume.builder()
                .career_obj(resumeBaseInDto.getCareerObj())
                .title(resumeBaseInDto.getTitle())
                .salary(resumeBaseInDto.getSalary())
                .seeker(seeker)
                .provinces(setProvinces)
                .fields(setJobFields)
                .build();

        Resume savedResume = resumeRepository.save(resume);


        return ResumeMapper.mapToResumeResponseBaseDto(savedResume);
    }

    @Override
    public ResumeResponseBaseDto updateResume(ResumeBaseInDto resumeBaseInDto, Long id) {

        Resume existingResume = resumeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume", "id", id)
                );

        Set<Long> setProvinceIds = resumeBaseInDto.getProvinceId();
        Set<Integer> convertedProvinceIds = setProvinceIds.stream()
                .map(Long::intValue) // Chuyển đổi từ Long sang Integer
                .collect(Collectors.toSet());

        Set<Province> setProvinces = convertedProvinceIds.stream()
                .map(
                        provinceId -> provinceRepository.findById(provinceId)
                                .orElseThrow(() -> new ResourceNotFoundException("Province", "id", provinceId))
                )
                .collect(Collectors.toSet());


        Seeker seeker = seekerRepository.findById(resumeBaseInDto.getSeekerId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Seeker", "id", resumeBaseInDto.getSeekerId())
                );

        Set<Long> setJobFieldIds = resumeBaseInDto.getJobFieldId();
        Set<JobField> setJobFields = setJobFieldIds.stream()
                .map(
                        fieldId -> jobFieldRepository.findById(fieldId)
                                .orElseThrow(() -> new ResourceNotFoundException("Job Field", "id", fieldId))
                )
                .collect(Collectors.toSet());

        existingResume.setSalary(resumeBaseInDto.getSalary());
        existingResume.setCareer_obj(resumeBaseInDto.getCareerObj());
        existingResume.setTitle(resumeBaseInDto.getTitle());
        existingResume.setFields(setJobFields);
        existingResume.setProvinces(setProvinces);
        existingResume.setSeeker(seeker);
        Resume savedResume = resumeRepository.save(existingResume);

        return ResumeMapper.mapToResumeResponseBaseDto(savedResume);
    }

    @Override
    public ResumeResponseGetByIdDto getResumeById(Long id) {
        Resume existingResume = resumeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume", "id", id)
                );
        return ResumeMapper.mapToResumeResponseGetByIdDto(existingResume);
    }

    @Override
    public ResumeResponseGetAllDto getAllResumes(int page, int pageSize, int seekerId) {
        if (page < 1) {
            throw new IllegalArgumentException("Invalid page number. Page number must be greater than or equal to 1.");
        }
        Sort sort = Sort.by( "title").ascending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);

        Page<Resume> resumes = resumeRepository.filterBySeekerId(seekerId, pageable);


        // get content for page object
        List<Resume> listOfResumes = resumes.getContent();

        List<Resume> sortedList = listOfResumes.stream()
                .sorted(Comparator.comparing(resume -> resume.getSeeker().getName()))
                .collect(Collectors.toList());

        List<ResumeResponseDataDto> resumeResponseGetAllDtos = sortedList.stream()
                .map(resume -> ResumeMapper.mapToResumeResponseGetAllDto(resume))
                .toList();


        ResumeResponseGetAllDto result = ResumeResponseGetAllDto.builder()
                .data(resumeResponseGetAllDtos)
                .last(resumes.isLast())
                .totalElements(resumes.getTotalElements())
                .totalPages(resumes.getTotalPages())
                .page(resumes.getNumber())
                .pageSize(resumes.getSize())
                .build();
        return result;
    }

    @Override
    public void deleteById(long id) {
        Resume resume = resumeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resume", "id", id)
        );

        resumeRepository.deleteById(id);
    }
}
