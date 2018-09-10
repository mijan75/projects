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
public class Distributor {
    @Id @GeneratedValue 
    private int distributorId;
    private String distributorName;
    private String distributorCity;
    private String contractNo;
    private String hiredDate;

    public Distributor() {
    }

    public Distributor(int distributorId, String distributorName, String distributorCity, String contractNo, String hiredDate) {
        this.distributorId = distributorId;
        this.distributorName = distributorName;
        this.distributorCity = distributorCity;
        this.contractNo = contractNo;
        this.hiredDate = hiredDate;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public String getDistributorCity() {
        return distributorCity;
    }

    public void setDistributorCity(String distributorCity) {
        this.distributorCity = distributorCity;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(String hiredDate) {
        this.hiredDate = hiredDate;
    }

    @Override
    public String toString() {
        return  distributorName ;
    }
    
    
    
}
