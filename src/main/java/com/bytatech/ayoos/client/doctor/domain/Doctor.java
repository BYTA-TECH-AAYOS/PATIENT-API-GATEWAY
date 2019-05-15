package com.bytatech.ayoos.client.doctor.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Doctor.
 */
@Document(indexName = "doctor")
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;
    
   
    private Long id;

   
    private String doctorId;

   
    private String specialization;

    private String registerNumber;

   
    private ContactInfo contactInfo;

   
    private Set<Hospital> hospitals = new HashSet<>();
   
    private Set<Qualification> qualifications = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public Doctor doctorId(String doctorId) {
        this.doctorId = doctorId;
        return this;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public Doctor specialization(String specialization) {
        this.specialization = specialization;
        return this;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public Doctor registerNumber(String registerNumber) {
        this.registerNumber = registerNumber;
        return this;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public Doctor contactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
        return this;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Set<Hospital> getHospitals() {
        return hospitals;
    }

    public Doctor hospitals(Set<Hospital> hospitals) {
        this.hospitals = hospitals;
        return this;
    }

    public Doctor addHospital(Hospital hospital) {
        this.hospitals.add(hospital);
        hospital.setDoctor(this);
        return this;
    }

    public Doctor removeHospital(Hospital hospital) {
        this.hospitals.remove(hospital);
        hospital.setDoctor(null);
        return this;
    }

    public void setHospitals(Set<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    public Set<Qualification> getQualifications() {
        return qualifications;
    }

    public Doctor qualifications(Set<Qualification> qualifications) {
        this.qualifications = qualifications;
        return this;
    }

    public Doctor addQualification(Qualification qualification) {
        this.qualifications.add(qualification);
        qualification.setDoctor(this);
        return this;
    }

    public Doctor removeQualification(Qualification qualification) {
        this.qualifications.remove(qualification);
        qualification.setDoctor(null);
        return this;
    }

    public void setQualifications(Set<Qualification> qualifications) {
        this.qualifications = qualifications;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Doctor doctor = (Doctor) o;
        if (doctor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), doctor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Doctor{" +
            "id=" + getId() +
            ", doctorId='" + getDoctorId() + "'" +
            ", specialization='" + getSpecialization() + "'" +
            ", registerNumber='" + getRegisterNumber() + "'" +
            "}";
    }
}
