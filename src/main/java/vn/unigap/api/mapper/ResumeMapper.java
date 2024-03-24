package vn.unigap.api.mapper;

import org.modelmapper.ModelMapper;
import vn.unigap.api.dto.out.resume.ResumeResponseBaseDto;
import vn.unigap.api.dto.out.resume.ResumeResponseDataDto;
import vn.unigap.api.dto.out.resume.ResumeResponseGetByIdDto;
import vn.unigap.api.entity.JobField;
import vn.unigap.api.entity.Province;
import vn.unigap.api.entity.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ResumeMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static ResumeResponseBaseDto mapToResumeResponseBaseDto(Resume resume){
        ResumeResponseBaseDto resumeResponseBaseDto = mapper.map(resume, ResumeResponseBaseDto.class);
        resumeResponseBaseDto.setNameJobField(resume.getFields().stream()
                .map(
                       jobField -> jobField.getName()
                ).collect(Collectors.toSet())

        );
        resumeResponseBaseDto.setNameProvince(resume.getProvinces().stream().map(
                province -> province.getName()
                ).collect(Collectors.toSet())
        );
        return resumeResponseBaseDto;
    }

    public static ResumeResponseGetByIdDto mapToResumeResponseGetByIdDto(Resume resume){
        ResumeResponseGetByIdDto resumeResponseGetByIdDto = mapper.map(resume, ResumeResponseGetByIdDto.class);
        //get mapJobField
        Set<JobField> setJobFields= resume.getFields();
        Map<Integer, String> mapJobFields = new HashMap<>();

        for(JobField jf : setJobFields){
            mapJobFields.put(jf.getId(), jf.getName());
        }

        //get mapJobProvince
        Set<Province> setProvinces = resume.getProvinces();
        Map<Integer, String> mapJobProvinces = new HashMap<>();
        for(Province province : setProvinces){
            mapJobProvinces.put(province.getId(), province.getName());
        }

        resumeResponseGetByIdDto.setMapJobField(mapJobFields);
        resumeResponseGetByIdDto.setMapJobProvince(mapJobProvinces);

        return resumeResponseGetByIdDto;
    }

    public static ResumeResponseDataDto mapToResumeResponseGetAllDto(Resume resume){
        ResumeResponseDataDto resumeResponseGetAllDto = mapper.map(resume, ResumeResponseDataDto.class);


        return resumeResponseGetAllDto;
    }

}
