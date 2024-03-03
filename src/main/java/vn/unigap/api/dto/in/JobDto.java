package vn.unigap.api.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {

    @NotBlank
    private String title;

    @NotNull
    private int quantity;

    @NotBlank
    private String description;

    @NotNull
    private int salary;

    @NotNull
    private Date expired_at;

}
