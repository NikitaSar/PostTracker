package PostTracker.utils;

import PostTracker.dto.PostOfficeDto;
import PostTracker.dto.PostalItemStatusDto;
import PostTracker.dto.PostalItemTypeDto;
import PostTracker.models.PostOffice;
import PostTracker.models.PostalItemStatus;
import PostTracker.models.PostalItemType;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-22T20:58:09+0400",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.2.jar, environment: Java 11 (Oracle Corporation)"
)
public class DtoMapperImpl implements DtoMapper {

    @Override
    public PostOffice fromPostOfficeDto(PostOfficeDto postOfficeDto) {
        if ( postOfficeDto == null ) {
            return null;
        }

        PostOffice postOffice = new PostOffice();

        postOffice.setPostalCode( postOfficeDto.getPostalCode() );
        postOffice.setName( postOfficeDto.getName() );
        postOffice.setAddress( postOfficeDto.getAddress() );

        return postOffice;
    }

    @Override
    public PostalItemStatus fromPostalItemStatusDto(PostalItemStatusDto statusDto) {
        if ( statusDto == null ) {
            return null;
        }

        PostalItemStatus postalItemStatus = new PostalItemStatus();

        postalItemStatus.setName( statusDto.getName() );

        return postalItemStatus;
    }

    @Override
    public PostalItemType fromPostalItemTypeDto(PostalItemTypeDto statusDto) {
        if ( statusDto == null ) {
            return null;
        }

        PostalItemType postalItemType = new PostalItemType();

        postalItemType.setName( statusDto.getName() );

        return postalItemType;
    }
}
