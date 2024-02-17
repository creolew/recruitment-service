package vn.unigap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDto {
    private String email;
    private String name;
    private int provinceId;
    private String description;

}
