package vn.unigap.api.dto.in.seeker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.unigap.api.dto.in.seeker.SeekerBaseInDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSeekerInDto extends SeekerBaseInDto {
    private transient long id;
}
