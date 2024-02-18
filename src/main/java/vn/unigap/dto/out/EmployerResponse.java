package vn.unigap.dto.out;

import lombok.Getter;
import lombok.Setter;
import vn.unigap.dto.in.EmployerDto;
import vn.unigap.dto.in.EmployerResponseDto;

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
