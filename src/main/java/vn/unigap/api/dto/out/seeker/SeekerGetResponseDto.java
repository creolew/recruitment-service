package vn.unigap.api.dto.out.seeker;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeekerGetResponseDto extends SeekerResponseBaseDto{
    private long id;

    private String provinceName;
}
