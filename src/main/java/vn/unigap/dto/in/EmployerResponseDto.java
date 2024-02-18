package vn.unigap.dto.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerResponseDto {
    private String email;
    private String name;
    private int provinceId;
    private String provinceName;
    private String description;
}
