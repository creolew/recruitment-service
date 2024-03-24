package vn.unigap.api.service;

import vn.unigap.api.dto.in.resume.ResumeBaseInDto;
import vn.unigap.api.dto.out.resume.ResumeResponseBaseDto;
import vn.unigap.api.dto.out.resume.ResumeResponseDataDto;
import vn.unigap.api.dto.out.resume.ResumeResponseGetAllDto;
import vn.unigap.api.dto.out.resume.ResumeResponseGetByIdDto;

public interface ResumeService {
    ResumeResponseBaseDto createResume(ResumeBaseInDto resumeBaseInDto);

    ResumeResponseBaseDto updateResume(ResumeBaseInDto resumeBaseInDto , Long id);

    ResumeResponseGetByIdDto getResumeById(Long id);

    ResumeResponseGetAllDto getAllResumes(int page, int pageSize, int seekerId);

    void deleteById(long id);

}
