package com.phonebook.mappers;

import com.phonebook.domain.DeviceDetails;
import com.phonebook.model.Device;
import com.phonebook.responses.FonoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DevicesMapper {

    @Mapping(target="taken_by", source="takenBy")
    @Mapping(target="taken_at", source="takenAt")
    DeviceDetails deviceToDeviceDetails(Device source);

    DeviceDetails fonoResponseToDeviceDetails(FonoResponse response);
}
