package vn.unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.unigap.api.dto.in.JobDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseDto extends JobDto {
    Long id;

    Long employerId;

    String employerName;
}
