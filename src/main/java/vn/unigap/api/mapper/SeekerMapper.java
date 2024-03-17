package vn.unigap.api.mapper;

import org.modelmapper.ModelMapper;
import vn.unigap.api.dto.out.seeker.SeekerResponseBaseDto;
import vn.unigap.api.dto.out.seeker.SeekerGetResponseDto;
import vn.unigap.api.entity.Seeker;

public class SeekerMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static SeekerResponseBaseDto mapToSeekerResponseDto(Seeker seeker){
        SeekerResponseBaseDto seekerResponseDto = mapper.map(seeker, SeekerResponseBaseDto.class);
        return seekerResponseDto;
    }

    public static SeekerGetResponseDto mapToSeekerResponseByIdDto(Seeker seeker){
        SeekerGetResponseDto seekerResponseByIdDto = mapper.map(seeker, SeekerGetResponseDto.class);
        return seekerResponseByIdDto;
    }
}
