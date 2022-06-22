package PostTracker.utils;

import PostTracker.dto.*;
import PostTracker.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Mapper
public interface DtoMapper {
    DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

    @Mapping(target = "id", ignore = true)
    PostOffice fromPostOfficeDto(PostOfficeDto postOfficeDto);
    @Mapping(target = "id", ignore = true)
    PostalItemStatus fromPostalItemStatusDto(PostalItemStatusDto statusDto);
    @Mapping(target = "id", ignore = true)
    PostalItemType fromPostalItemTypeDto(PostalItemTypeDto statusDto);

    default TrackingDto fromTrackingList(List<Tracking> trackingList) {
        if (null == trackingList || trackingList.size() == 0)
            throw new NoSuchElementException();
        var dto = new TrackingDto();
        dto.setPostalCode(trackingList.get(0).getPostalItem().getRecipientPostOffice().getPostalCode());
        dto.setRecipientAddress(trackingList.get(0).getPostalItem().getRecipientAddress());
        dto.setRecipientName(trackingList.get(0).getPostalItem().getRecipientName());
        dto.setPostalItemType(trackingList.get(0).getPostalItem().getType().getName());
        dto.setHistory(trackingList.stream().map(tracking ->
                new HistoryDto(new Date(tracking.getDate().getTime()), tracking.getStatus().getName()))
                .collect(Collectors.toList()));
        return dto;
    }

}
