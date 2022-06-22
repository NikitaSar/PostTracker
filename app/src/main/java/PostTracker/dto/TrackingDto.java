package PostTracker.dto;

import lombok.Data;

import java.util.List;

@Data
public class TrackingDto {
    private String recipientAddress;
    private String recipientName;
    private String postalCode;
    private String postalItemType;
    private List<HistoryDto> history;
}
