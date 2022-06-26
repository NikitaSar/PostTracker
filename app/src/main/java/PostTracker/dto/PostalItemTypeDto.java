package PostTracker.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostalItemTypeDto {
    @NotNull
    private String name;
}
