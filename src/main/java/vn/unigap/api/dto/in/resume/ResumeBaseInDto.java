package vn.unigap.api.dto.in.resume;



import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResumeBaseInDto {

    @NotNull
    private String careerObj;

    @NotNull
    private String title;

    @NotNull
    private int salary;

    @NotNull
    private Long seekerId;

    @NotNull
    private Set<Long> jobFieldId;

    @NotNull
    private Set<Long> provinceId;



}
