# phone-booking
There are 10 devices to choose.
Everyone has own characteristics.

This repo demonstrates an API example how to manage devices.
According to requirements must be:
- View the list of devices
- Every device must have attribute of availability
- Every device must have the following attibutes:
  - technology
  - 2G bands
  - 3G bands
  - 4G bands
- Name and date, who and when taken device
- API must be provided to take and return device

Characteristics must be taken from FonoAPI project
In case it's not available (and it is not), implement a workaround.

Solution is implemented with embedded database H2 to store devices and do tests.

API: 

GET: /api/v1/book{id}    - borrow device, if available. Header with username is required to update journal record.
GET: /api/v1/return/{id} - return device

Inventory management:

GET: /api/v1/inventory/all.      - return list of all devices
GET: /api/v1/inventory/available - return list of all available devices

GET: /api/v1/inventory/expose.   - make an attempt to find and populate device details from FonoApi and store it in DB. Required device name and optional brand.
GET: /api/v1/inventory/load      - save device details from request query parameters. Required device name and optional brand.

