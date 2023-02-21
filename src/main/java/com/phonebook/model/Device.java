package com.phonebook.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Devices")
public class Device {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEVICES_SEQ" )
        @Column(name="DEVICE_ID")
        private int id;
        @Column(name="BRAND")
        private String brand;
        @Column(name="DEVICE")
        private String device;
        @Column(name="TECH")
        private String technology;
        @Column(name="G2")
        private String g2;
        @Column(name="G3")
        private String g3;
        @Column(name="G4")
        private String g4;
        @Column(name="TAKEN_BY")
        private String taken_by;
        @Column(name="TAKEN_AT")
        private Timestamp taken_at;

        private Device() {}

        public Device(String brand, String device, String technology, String G2, String G3, String G4) {
                this.brand = brand;
                this.device = device;
                this.technology = technology;
                this.g2 = G2;
                this.g3 = G3;
                this.g4 = G4;
        }
        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getBrand() {
                return brand;
        }

        public void setBrand(String brand) {
                this.brand = brand;
        }

        public String getDevice() {
                return device;
        }

        public void setDevice(String device) {
                this.device = device;
        }

        public String getTechnology() {
                return technology;
        }

        public void setTechnology(String technology) {
                this.technology = technology;
        }

        public String getG2() {
                return g2;
        }

        public void setG2(String g2) {
                this.g2 = g2;
        }

        public String getG3() {
                return g3;
        }

        public void setG3(String g3) {
                this.g3 = g3;
        }

        public String getG4() {
                return g4;
        }

        public void setG4(String g4) {
                this.g4 = g4;
        }

        public String getTakenBy() {
                return taken_by;
        }

        public void setTakenBy(String taken_by) {
                this.taken_by = taken_by;
        }

        public Timestamp getTakenAt() {
                return taken_at;
        }

        public void setTakenAt(Timestamp taken_at) {
                this.taken_at = taken_at;
        }
}
