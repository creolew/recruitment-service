package vn.unigap.api.dto.out.seeker;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vn.unigap.api.dto.in.employer.EmployerResponseDto;

import java.util.List;

@Getter
@Setter
@Builder
public class SeekerReponseGetAllDto {
    private int page;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private List<SeekerGetResponseDto> data;
}
