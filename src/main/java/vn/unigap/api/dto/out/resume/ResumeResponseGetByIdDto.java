package vn.unigap.api.dto.out.resume;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResumeResponseGetByIdDto {

    private long id;
    private long seekerId;
    private String seekerName;
    private String career_obj;
    private String title;
    private int salary;
    private Map<Integer, String> mapJobField;
    private Map<Integer, String> mapJobProvince;


}
