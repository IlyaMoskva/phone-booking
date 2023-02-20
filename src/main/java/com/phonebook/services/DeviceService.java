package com.phonebook.services;

import com.phonebook.exceptions.DeviceAlreadyInUseException;
import com.phonebook.exceptions.DeviceNotFoundException;
import com.phonebook.model.Device;
import com.phonebook.repositories.DeviceRepository;
import com.phonebook.domain.DeviceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.FragmentNotImplementedException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    public Device getDeviceById(int id) throws DeviceNotFoundException {
        try {
            return deviceRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException nse) {
            throw new DeviceNotFoundException(String.valueOf(id));
        }
    }

    public boolean isAvailable(int id) throws DeviceNotFoundException {
        Device device = getDeviceById(id);
        return device.getTakenBy() == null;
    }

    public List<Device> getAllDevices() {
        List<Device> devices = new ArrayList<>();
        if (deviceRepository.count() > 0)
            deviceRepository.findAll().forEach(device -> devices.add(device));
        return devices;
    }

    public List<Device> getAllAvailableDevices() {
        List<Device> devices = new ArrayList<>();
        if (deviceRepository.count() > 0) {
            for (Device device : deviceRepository.findAll()) {
                if (device.getTakenBy() == null) {
                    devices.add(device);
                }
            }
        }
        return devices;
    }

    public Device addDevice(DeviceDetails dd) {
        Device savedDevice = deviceRepository.save(new Device(dd.brand(), dd.device(), dd.technology(), dd.g2(), dd.g3(), dd.g4()));
        return savedDevice;
    }

    public DeviceDetails bookDevice(int id, String userId) throws DeviceNotFoundException, DeviceAlreadyInUseException {

        Device device = getDeviceById(id);
        // TODO: can be validator
        if (!isAvailable(id))
            throw new DeviceAlreadyInUseException(" (id:" + id + ", " + device.getBrand() + " " + device.getDevice() + ") by " + device.getTakenBy() + " since " + device.getTakenAt());
        device.setTakenAt(new Timestamp(System.currentTimeMillis()));
        device.setTakenBy(userId);
        Device savedDevice = deviceRepository.save(device);
        //TODO: use mapper here
        return new DeviceDetails(savedDevice.getId(),
                savedDevice.getBrand(), savedDevice.getDevice(),
                savedDevice.getTechnology(),
                savedDevice.getG2(), savedDevice.getG3(), savedDevice.getG4(),
                savedDevice.getTakenBy(),
                savedDevice.getTakenAt());
    }

    public DeviceDetails returnDevice(int id) throws DeviceNotFoundException {
        Device device = getDeviceById(id);
        if (!isAvailable(id)) {
            device.setTakenAt(null);
            device.setTakenBy(null);
            device = deviceRepository.save(device);
        }
        return new DeviceDetails(device.getId(),
                device.getBrand(), device.getDevice(),
                device.getTechnology(),
                device.getG2(), device.getG3(), device.getG4(),
                device.getTakenBy(),
                device.getTakenAt());
    }
}
