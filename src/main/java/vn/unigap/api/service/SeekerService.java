package vn.unigap.api.service;


import vn.unigap.api.dto.in.seeker.CreateSeekerInDto;
import vn.unigap.api.dto.in.seeker.UpdateSeekerInDto;
import vn.unigap.api.dto.out.EmployerResponse;
import vn.unigap.api.dto.out.seeker.SeekerReponseGetAllDto;
import vn.unigap.api.dto.out.seeker.SeekerResponseBaseDto;
import vn.unigap.api.dto.out.seeker.SeekerGetResponseDto;

import java.util.List;

public interface SeekerService {
    SeekerResponseBaseDto createSeeker(CreateSeekerInDto createSeekerInDto);

    SeekerResponseBaseDto updateSeeker(Long seekerId, UpdateSeekerInDto updateSeekerInDto);

    SeekerGetResponseDto getSeekerById(long id);

    SeekerReponseGetAllDto getAllSeekers(int page, int pageSize, int provinceId);

    void deleteById(long id);

}
