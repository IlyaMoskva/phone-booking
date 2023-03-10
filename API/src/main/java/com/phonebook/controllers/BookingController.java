package com.phonebook.controllers;

import com.phonebook.domain.DeviceDetails;
import com.phonebook.exceptions.DeviceAlreadyInUseException;
import com.phonebook.exceptions.DeviceNotFoundException;
import com.phonebook.exceptions.NotAuthorizedException;
import com.phonebook.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
    @Autowired
    DeviceService deviceService;

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceDetails bookDevice(@PathVariable int id, @RequestHeader String userId) throws DeviceNotFoundException, DeviceAlreadyInUseException, NotAuthorizedException {
        if (userId == null || userId.isBlank())
            throw new NotAuthorizedException();
        return deviceService.bookDevice(id, userId);
    }

    @RequestMapping(value = "/return/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceDetails returnDevice(@PathVariable int id) throws DeviceNotFoundException {
        return deviceService.returnDevice(id);
    }
}
