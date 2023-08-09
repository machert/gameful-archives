package felipe.machert.gamefularchives.dtos;

import jakarta.validation.constraints.NotBlank;

public record GameRecordDto(@NotBlank String name, String platform, Integer year) {

}
