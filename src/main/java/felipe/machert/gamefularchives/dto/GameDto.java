package felipe.machert.gamefularchives.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDto{

    @NotBlank
    private String name;
    private String platform;
    @Size(max = 4)
    private Integer year_finished;

}
