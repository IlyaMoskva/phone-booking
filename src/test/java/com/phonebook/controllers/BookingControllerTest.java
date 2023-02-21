package com.phonebook.controllers;

import com.phonebook.domain.DeviceDetails;
import com.phonebook.exceptions.DeviceAlreadyInUseException;
import com.phonebook.exceptions.DeviceNotFoundException;
import com.phonebook.exceptions.NotAuthorizedException;
import com.phonebook.services.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    private BookingController bc = new BookingController();

    private DeviceService deviceService = new DeviceService();

    private DeviceDetails freeDevice = new DeviceDetails(
            1,"", "nokia 2", "tech", "gsm800",null,null, null, null);
    private DeviceDetails takenDevice = new DeviceDetails(
            2,"", "nokia 2", "tech", "gsm800",null,null, "username", new Timestamp(System.currentTimeMillis()));

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        deviceService = mock(DeviceService.class);
    }

    @Test
    public void bookDevice_NoUserPassed_ExceptionThrown() {
        assertThrows(NotAuthorizedException.class, () -> {bc.bookDevice(123, "");} );
    }

    @Test
    void returnDevice() {
    }
}