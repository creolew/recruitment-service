package vn.unigap.api.dto.out.employer;

import lombok.Getter;
import lombok.Setter;
import vn.unigap.api.dto.in.employer.EmployerResponseDto;

import java.util.List;

@Getter
@Setter
public class EmployerResponse {

    private int page;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private List<EmployerResponseDto> data;
}
