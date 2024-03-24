package vn.unigap.api.dto.out.job;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobByEmployerIdDto extends JobResponseDto{

    @JsonIgnore
    String description;
}
