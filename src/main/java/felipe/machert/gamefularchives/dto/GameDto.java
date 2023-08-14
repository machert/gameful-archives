package felipe.machert.gamefularchives.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDto{

    @NotBlank
    private String name;
    private String platform;
    @Max(value = 9999, message = "Year finished must be a positive value with maximum range of 9999")
    private Integer year_finished;

}
