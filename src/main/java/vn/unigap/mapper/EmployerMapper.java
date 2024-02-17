package vn.unigap.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.unigap.dto.EmployerDto;
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
}
