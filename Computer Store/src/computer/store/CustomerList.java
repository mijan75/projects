/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.store;

/**
 *
 * @author nazmul
 */
public class CustomerList {
    private int id;
    private String name;
    private String address;
    private String password;
    private String userName;
    private String cellNumber;

    public CustomerList() {
    }

    public CustomerList(int id, String name, String address, String password, String userName, String cellNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.password = password;
        this.userName = userName;
        this.cellNumber = cellNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    @Override
    public String toString() {
        return "CustomerList{" + "id=" + id + ", name=" + name + ", address=" + address + ", password=" + password + ", userName=" + userName + ", cellNumber=" + cellNumber + '}';
    }

    
    
    
}
