package com.example.compnayservice;

public class Company {

    private int companyId;
    private String companyName;
    private String VATnumber;
    private String country;
    private String password;
    private String email;
    private String work;

    public String getPassword() {
        return password;
    }

    public Company(int companyId, String companyName, String VATnumber, String country, String password, String email, String work) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.VATnumber = VATnumber;
        this.country = country;
        this.password = password;
        this.email = email;
        this.work = work;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getWork() {
        return work;
    }


    public String getEmail() {
        return email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getCompanyName() {
        return this.companyName;
    }

    public String getCountry() {
        return this.country;
    }

    public String getVATnumber() {
        return this.VATnumber;
    }
}
