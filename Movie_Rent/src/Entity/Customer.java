/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author User
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String contractNo;

    @ManyToMany
    private Set<StoreDetails> sd;

    public Customer() {
    }

    public Customer(int customerId, String customerName, String customerAddress, String contractNo, Set<StoreDetails> sd) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.contractNo = contractNo;
        this.sd = sd;
    }

    public int getCustomerId() {
        return customerId;
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

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    @Override
    public String toString() {
        return customerName;
    }

    public Set<StoreDetails> getSd() {
        return sd;
    }

    public void setSd(Set<StoreDetails> sd) {
        this.sd = sd;
    }

}
