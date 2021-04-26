package model;

import java.util.List;

public class Client {
    private int idClient;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;


    public Client( String name, String address, String email, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }
    public Client(List<String> fields){
        this.name = fields.get(0);
        this.address = fields.get(1);
        this.email = fields.get(2);
        this.phoneNumber = fields.get(3);
    }
    public Client(){

    }
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return
                "ID =" + idClient +
                ", Name: " + name;
    }
}
