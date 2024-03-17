package vn.unigap.api.dto.in.seeker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSeekerInDto extends SeekerBaseInDto{
    private transient long id;
}
