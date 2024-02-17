package vn.unigap.mapper;

import org.modelmapper.ModelMapper;
import vn.unigap.common.GenericResponse;
import vn.unigap.response.ApiCreatedResponse;

public class ApiCreatedResponseMapper {
    static ModelMapper mapper = new ModelMapper();

    public static ApiCreatedResponse mapToGenericResponseDto(GenericResponse genericResponse){
        ApiCreatedResponse apiCreatedResponse = mapper.map(genericResponse, ApiCreatedResponse.class);
        return apiCreatedResponse;
    }
}
