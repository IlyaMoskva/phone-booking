package com.phonebook.mappers;

import com.phonebook.domain.DeviceDetails;
import com.phonebook.model.Device;
import com.phonebook.responses.FonoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DevicesMapper {

    DeviceDetails deviceToDeviceDetails(Device source);

    DeviceDetails fonoResponseToDeviceDetails(FonoResponse response);
}
