package com.personal.shadylady;

public class Client implements java.io.Serializable  {
    public String first;
    public String last;
    public String dob;
    public String gender;
    public String phone;
    public String email;
    public String epay;
    public String notes;

    public Client() {}

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEpay() {
        return epay;
    }

    public void setEpay(String epay) {
        this.epay = epay;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    //    public Client(
//        String first,
//        String last,
//        String dob,
//        String gender,
//        String phone,
//        String email,
//        String epay,
//        String notes
//    )
//    {
//        this.first = first;
//        this.last = last;
//        this.dob = dob;
//        this.gender = gender;
//        this.phone = phone;
//        this.email = email;
//        this.epay = epay;
//        this.notes = notes;
//    }
}
