package com.phonebook.mappers;

import com.phonebook.domain.DeviceDetails;
import com.phonebook.model.Device;
import com.phonebook.responses.FonoResponse;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class DevicesMapperTest {

    DevicesMapper mapper = new DevicesMapperImpl();
    @Test
    void deviceToDeviceDetails() {
        Device from = new Device("brand", "device", "tech", "2g", "3g", "4g");
        from.setTakenAt( new Timestamp(System.currentTimeMillis()) );
        from.setTakenBy("newOwner");
        DeviceDetails to = mapper.deviceToDeviceDetails(from);

        assertEquals(to.device(), from.getDevice());
        assertEquals(to.brand(), from.getBrand());
        assertEquals(to.technology(), from.getTechnology());
        assertEquals(to.g2(), from.getG2());
        assertEquals(to.g3(), from.getG3());
        assertEquals(to.g4(), from.getG4());
        assertEquals(to.taken_at(), from.getTakenAt());
        assertEquals(to.taken_by(), from.getTakenBy());
    }

    @Test
    void fonoResponseToDeviceDetails() {
        FonoResponse from = new FonoResponse("brand", "device", "tech", "2g", "3g", "4g");
        DeviceDetails to = mapper.fonoResponseToDeviceDetails(from);

        assertEquals(to.device(), from.device());
        assertEquals(to.brand(), from.brand());
        assertEquals(to.technology(), from.technology());
        assertEquals(to.g2(), from.g2());
        assertEquals(to.g3(), from.g3());
        assertEquals(to.g4(), from.g4());
        assertEquals(to.taken_at(), null);
        assertEquals(to.taken_by(), null);
        assertEquals(to.id(), 0);
    }
}