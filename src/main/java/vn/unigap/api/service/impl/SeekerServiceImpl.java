package vn.unigap.api.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.seeker.CreateSeekerInDto;
import vn.unigap.api.dto.in.seeker.UpdateSeekerInDto;
import vn.unigap.api.dto.out.seeker.SeekerReponseGetAllDto;
import vn.unigap.api.dto.out.seeker.SeekerResponseBaseDto;
import vn.unigap.api.dto.out.seeker.SeekerGetResponseDto;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.entity.Province;
import vn.unigap.api.entity.Seeker;
import vn.unigap.api.exception.ResourceNotFoundException;
import vn.unigap.api.mapper.SeekerMapper;
import vn.unigap.api.repository.ProvinceRepository;
import vn.unigap.api.repository.SeekerRepository;
import vn.unigap.api.service.ProvinceService;
import vn.unigap.api.service.SeekerService;

import java.util.List;

@Service
@AllArgsConstructor
public class SeekerServiceImpl implements SeekerService {

    ProvinceService provinceService;

    ProvinceRepository provinceRepository;
    SeekerRepository seekerRepository;

    @Override
    public SeekerResponseBaseDto createSeeker(CreateSeekerInDto createSeekerInDto) {

        Province province = provinceRepository.findById(createSeekerInDto.getProvinceId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Province", "id", createSeekerInDto.getProvinceId())
                );
        Seeker seeker = Seeker.builder()
                .name(createSeekerInDto.getName())
                .address(createSeekerInDto.getAddress())
                .birthday(createSeekerInDto.getBirthday())
                .province(province)
                .build();

        Seeker savedSeeker = seekerRepository.save(seeker);


        return SeekerMapper.mapToSeekerResponseDto(savedSeeker);
    }

    @Override
    public SeekerResponseBaseDto updateSeeker(Long seekerId, UpdateSeekerInDto updateSeekerInDto) {

        Province province = provinceRepository.findById(updateSeekerInDto.getProvinceId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Province", "id", updateSeekerInDto.getProvinceId())
                );

        Seeker existingSeeker = seekerRepository.findById(seekerId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Seeker", "id", updateSeekerInDto.getId())
                );

        existingSeeker.setName(updateSeekerInDto.getName());
        existingSeeker.setBirthday(updateSeekerInDto.getBirthday());
        existingSeeker.setAddress(updateSeekerInDto.getAddress());
        existingSeeker.setProvince(province);

        Seeker savedSeeker = seekerRepository.save(existingSeeker);

        return SeekerMapper.mapToSeekerResponseDto(savedSeeker);
    }

    @Override
    public SeekerGetResponseDto getSeekerById(long seekerId) {
        Seeker existingSeeker = seekerRepository.findById(seekerId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Seeker", "id", seekerId)
                );

        SeekerGetResponseDto result = SeekerMapper.mapToSeekerResponseByIdDto(existingSeeker);

        return result;
    }

    @Override
    public SeekerReponseGetAllDto getAllSeekers(int page, int pageSize, int provinceId) {
        if (page < 1) {
            throw new IllegalArgumentException("Invalid page number. Page number must be greater than or equal to 1.");
        }

        Sort sort =Sort.by("name").ascending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);

        Page<Seeker> seekers = seekerRepository.filterByProvinceId(provinceId, pageable);

        // get content for page object
        List<Seeker> listOfSeekers = seekers.getContent();

        List<SeekerGetResponseDto> listSeekerResponseDto = listOfSeekers.stream()
                .map(seeker -> SeekerMapper.mapToSeekerResponseByIdDto(seeker))
                .toList();

        SeekerReponseGetAllDto result = SeekerReponseGetAllDto.builder()
                .data(listSeekerResponseDto)
                .last(seekers.isLast())
                .totalElements(seekers.getTotalElements())
                .totalPages(seekers.getTotalPages())
                .page(seekers.getNumber())
                .pageSize(seekers.getSize())
                .build();
        return result;
    }

    @Override
    public void deleteById(long id) {
        //check if the employer existed
        Seeker seeker = seekerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Seeker", "id", id)
        );

        seekerRepository.deleteById(id);
    }
}
