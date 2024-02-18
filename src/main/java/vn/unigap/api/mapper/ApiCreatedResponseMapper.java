package vn.unigap.api.mapper;

import org.modelmapper.ModelMapper;
import vn.unigap.api.common.GenericResponse;
import vn.unigap.api.dto.out.ApiCreatedResponse;

public class ApiCreatedResponseMapper {
    static ModelMapper mapper = new ModelMapper();

    public static ApiCreatedResponse mapToGenericResponseDto(GenericResponse genericResponse){
        ApiCreatedResponse apiCreatedResponse = mapper.map(genericResponse, ApiCreatedResponse.class);
        return apiCreatedResponse;
    }
}
