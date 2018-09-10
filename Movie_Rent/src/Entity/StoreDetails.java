/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
public class StoreDetails {
    @Id @GeneratedValue 
    private int id;
    private String storeName;
    private String storeCity;
    private String contractNo;
    private String estaDate;
    private String storeUserName;

    public StoreDetails() {
    }

    public StoreDetails(int id, String storeName, String storeCity, String contractNo, String estaDate, String storeUserName) {
        this.id = id;
        this.storeName = storeName;
        this.storeCity = storeCity;
        this.contractNo = contractNo;
        this.estaDate = estaDate;
        this.storeUserName = storeUserName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public void setStoreCity(String storeCity) {
        this.storeCity = storeCity;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getEstaDate() {
        return estaDate;
    }

    public void setEstaDate(String estaDate) {
        this.estaDate = estaDate;
    }

     public String getStoreUserName() {
        return storeUserName;
    }

    public void setStoreUserName(String storeUserName) {
        this.storeUserName = storeUserName;
    }
    
    @Override
    public String toString() {
        return storeName;
    }

   
    
     
}
