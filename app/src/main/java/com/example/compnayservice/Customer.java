package com.example.compnayservice;

public class Customer {

    private int customerId;
    private String customerName;
    private String typeOfWork;
    private String time;
    private int companyId;
    private String customerEmail;
    private int userCustomerId;



    /**
     * Constructor for customer that order services.
     * @param customerName
     * @param work
     * @param time
     * @param companyId
     */
    public Customer(int userCustomerId, String customerName, String customerEmail,  String work, String time, int companyId){
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.userCustomerId = userCustomerId;
        this.typeOfWork = work;
        this.time = time;
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", typeOfWork='" + typeOfWork + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public int getUserCustomerId() {
        return userCustomerId;
    }

    public void setUserCustomerId(int userCustomerId) {
        this.userCustomerId = userCustomerId;
    }

    /**
     * Getters and setters
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getCustomerId(){
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
