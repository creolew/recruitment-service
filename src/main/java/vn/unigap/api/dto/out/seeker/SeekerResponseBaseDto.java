package vn.unigap.api.dto.out.seeker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeekerResponseBaseDto {


    private String name;

    private LocalDate birthday;

    private String address;

    private int provinceId;
}
