package PostTracker.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostalItemStatusDto {
    @NotNull
    private String name;
}
