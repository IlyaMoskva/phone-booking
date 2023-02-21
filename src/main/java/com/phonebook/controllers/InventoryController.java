package com.phonebook.controllers;

import com.phonebook.exceptions.ApiNotFoundException;
import com.phonebook.exceptions.InternalException;
import com.phonebook.domain.DeviceDetails;
import com.phonebook.services.DeviceService;
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

    @RequestMapping(value = "/")
    public String welcome() {
        return "Welcome to Phone Booking api v 1.4.0";
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceDetails loadDevice(@RequestParam Optional<String> brand,
                                    @RequestParam String device,
                                    @RequestParam String tech,
                                    @RequestParam String g2bands,
                                    @RequestParam String g3bands,
                                    @RequestParam String g4bands) {
        return deviceService.addDevice(brand, device, tech, g2bands, g3bands, g4bands);
    }

    @RequestMapping(value = "/expose", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceDetails loadExposedDevice(@RequestParam Optional<String> brand,
                                    @RequestParam String device) throws ApiNotFoundException, InternalException {
        return deviceService.loadExposedDevice(brand, device);
    }
    @RequestMapping(value = "/available", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDetails> getAvailableDevices() {
        return deviceService.getAllAvailableDevices();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDetails> getAllDevices() {
        return deviceService.getAllDevices();
    }
}
