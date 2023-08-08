package felipe.machert.gamefularchives.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

public record GameRecordDto(@NotBlank String name, String platform, Integer year) {

}
