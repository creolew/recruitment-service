package vn.unigap.mapper;

import org.modelmapper.ModelMapper;
import vn.unigap.common.ProvinceEnum;
import vn.unigap.dto.in.EmployerDto;
import vn.unigap.dto.in.EmployerResponseDto;
import vn.unigap.entity.Employer;


public class EmployerMapper {

    private static ModelMapper mapper = new ModelMapper();;
    public static EmployerDto mapToEmployerDto(Employer employer){
        EmployerDto employerDto = mapper.map(employer, EmployerDto.class);
        return employerDto;
    }

    public static Employer mapToEmployer(EmployerDto employerDto){
        Employer employer = mapper.map(employerDto, Employer.class);
        return employer;
    }


    public static EmployerResponseDto mapToEmployResponseDto(EmployerDto employerDto){
        EmployerResponseDto employerResponseDto = mapper.map(employerDto, EmployerResponseDto.class);
        String provinceName = ProvinceEnum.getProvinceMap().get(employerResponseDto.getProvinceId());
        employerResponseDto.setProvinceName(provinceName);
        return employerResponseDto;
    }
}
