package com.phonebook.services;

import com.phonebook.exceptions.ApiNotFoundException;
import com.phonebook.exceptions.DeviceAlreadyInUseException;
import com.phonebook.exceptions.DeviceNotFoundException;
import com.phonebook.exceptions.InternalException;
import com.phonebook.mappers.DevicesMapper;
import com.phonebook.model.Device;
import com.phonebook.repositories.DeviceRepository;
import com.phonebook.domain.DeviceDetails;
import com.phonebook.responses.FonoResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.FragmentNotImplementedException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    FonoService fonoService;

    @Autowired
    protected DevicesMapper mapper = Mappers.getMapper(DevicesMapper.class);

    private Device getDeviceById(int id) throws DeviceNotFoundException {
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

    public List<DeviceDetails> getAllDevices() {
        List<DeviceDetails> devices = new ArrayList<>();
        if (deviceRepository.count() > 0)
            deviceRepository.findAll().forEach(device -> devices.add(mapper.deviceToDeviceDetails(device)));
        return devices;
    }

    public List<DeviceDetails> getAllAvailableDevices() {
        List<DeviceDetails> devices = new ArrayList<>();
        if (deviceRepository.count() > 0) {
            for (Device device : deviceRepository.findAll()) {
                if (device.getTakenBy() == null) {
                    devices.add(mapper.deviceToDeviceDetails(device));
                }
            }
        }
        return devices;
    }

    public DeviceDetails addDevice(DeviceDetails dd) {
        Device device = deviceRepository.save(new Device(dd.brand(), dd.device(), dd.technology(), dd.g2(), dd.g3(), dd.g4()));
        return mapper.deviceToDeviceDetails(device);
    }

    public DeviceDetails bookDevice(int id, String userId) throws DeviceNotFoundException, DeviceAlreadyInUseException {

        Device device = getDeviceById(id);
        // TODO: can be validator
        if (!isAvailable(id))
            throw new DeviceAlreadyInUseException(" (id:" + id + ", " + device.getBrand() + " " + device.getDevice() + ") by " + device.getTakenBy() + " since " + device.getTakenAt());
        device.setTakenAt(new Timestamp(System.currentTimeMillis()));
        device.setTakenBy(userId);
        Device savedDevice = deviceRepository.save(device);
        return mapper.deviceToDeviceDetails(savedDevice);
    }

    public DeviceDetails returnDevice(int id) throws DeviceNotFoundException {
        Device device = getDeviceById(id);
        if (!isAvailable(id)) {
            device.setTakenAt(null);
            device.setTakenBy(null);
            Device savedDevice = deviceRepository.save(device);
            return mapper.deviceToDeviceDetails(savedDevice); // To ensure it's updated
        }
        return mapper.deviceToDeviceDetails(device);
    }

    public DeviceDetails loadExposedDevice(Optional<String> brand, String deviceName) throws ApiNotFoundException, InternalException {
        FonoResponse rs = fonoService.exposeData(brand, deviceName);
        DeviceDetails details = mapper.fonoResponseToDeviceDetails(rs);
        return details;
    }
}
