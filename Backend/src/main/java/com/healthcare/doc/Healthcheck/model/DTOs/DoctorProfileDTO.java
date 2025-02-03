package com.healthcare.doc.Healthcheck.model.DTOs;


public class DoctorProfileDTO {
    private String id; // Doctor ID
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String availability; // Example: "Monday to Friday, 9 AM - 5 PM"
    private String specialization;
    private String qualifications;
    private String experience;
    private String photoUrl;
    private String location; // New field to store location
    private String ratings;  // New field for doctor ratings (e.g., "4.5/5")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getLocation() {
        return location;
    }




    public void setLocation(String location) {
        this.location = location;
    }

    public String getRatings() {
        return ratings;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }
}
