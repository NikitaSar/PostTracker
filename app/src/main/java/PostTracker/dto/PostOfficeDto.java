package PostTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOfficeDto {
    @NotEmpty
    @Pattern(regexp = "\\d{6}")
    private String postalCode;
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
}
