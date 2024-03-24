package vn.unigap.api.dto.out.resume;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResumeResponseDataDto {
    private long id;
    private long seekerId;
    private String seekerName;
    private String career_obj;
    private String title;
    private int salary;

}
