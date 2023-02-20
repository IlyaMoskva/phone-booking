package com.phonebook.controllers;

import com.phonebook.exceptions.ApiNotFoundException;
import com.phonebook.exceptions.InternalException;
import com.phonebook.model.Device;
import com.phonebook.domain.DeviceDetails;
import com.phonebook.responses.FonoResponse;
import com.phonebook.services.DeviceService;
import com.phonebook.services.FonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    DeviceService deviceService;

    @Autowired
    FonoService fonoService;

    @RequestMapping(value = "/")
    public String welcome() {
        return "Welcome to Phone Booking api v 0.3.0";
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Device loadDevice(@RequestParam String brand, @RequestParam String device) {
        return deviceService.addDevice(new DeviceDetails( 123, brand, device, "immortal", "1800/1900", "WCDMA", "LTE+", null, null));
    }

    @RequestMapping(value = "/expose", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Device loadExposedDevice(@RequestParam Optional<String> brand,
                                    @RequestParam String device) throws ApiNotFoundException, InternalException {
            FonoResponse rs = fonoService.exposeData(brand, device);
            return new Device(rs.brand(), rs.device(), rs.technology(), rs.g2(), rs.g3(), rs.g4());
    }
    @RequestMapping(value = "/available", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Device> getAvailableDevices() {
        return deviceService.getAllAvailableDevices();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }
}
