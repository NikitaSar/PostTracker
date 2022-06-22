package PostTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostalItemDto {
    @NotNull
    private Long postalItemTypeId;
    @NotNull
    private Long recipientPostOfficeId;
    @NotEmpty
    private String recipientAddress;
    @NotEmpty
    private String recipientName;
    @NotNull
    private Long postalStatusId;
}
