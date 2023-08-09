package felipe.machert.gamefularchives.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDto{

    @NotBlank
    private String name;
    private String platform;
    private Integer year_finished;

}
