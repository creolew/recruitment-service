package vn.unigap.api.dto.out.resume;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.unigap.api.entity.JobField;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResumeResponseBaseDto {

    private String career_obj;

    private String title;

    private int salary;

    private int provine_id;

    private int seeker_id;

    private Set<String> nameJobField;

    private Set<String> nameProvince;
}
