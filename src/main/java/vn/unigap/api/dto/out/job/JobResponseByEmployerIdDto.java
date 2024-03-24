package vn.unigap.api.dto.out.job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseByEmployerIdDto {
    private int page;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private List<JobByEmployerIdDto> data;
}
