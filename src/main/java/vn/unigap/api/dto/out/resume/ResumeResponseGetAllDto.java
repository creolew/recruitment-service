package vn.unigap.api.dto.out.resume;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResumeResponseGetAllDto {
    private int page;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private List<ResumeResponseDataDto> data;
}
