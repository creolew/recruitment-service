package vn.unigap.api.dto.in.seeker;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeekerBaseInDto {

    private long id;

    @NotNull
    private String name;

    @NotNull
    private LocalDate birthday;

    @NotNull
    private String address;

    @NotNull
    private int provinceId;
}
