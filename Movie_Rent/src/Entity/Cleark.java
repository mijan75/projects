/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author User
 */
@Entity
public class Cleark {
    @Id @GeneratedValue 
    private int clearkId;
    private String clearkName;
    private String clearkCity;
    private String clearkNo;
    private String clearkEntryDate;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<StoreDetails> sd ;

    public Cleark() {
    }

    public Cleark(int clearkId, String clearkName, String clearkCity, String clearkNo, String clearkEntryDate, Set<StoreDetails> sd) {
        this.clearkId = clearkId;
        this.clearkName = clearkName;
        this.clearkCity = clearkCity;
        this.clearkNo = clearkNo;
        this.clearkEntryDate = clearkEntryDate;
        this.sd = sd;
    }

   
   

  

    public int getClearkId() {
        return clearkId;
    }

    public void setClearkId(int clearkId) {
        this.clearkId = clearkId;
    }

    public String getClearkName() {
        return clearkName;
    }

    public void setClearkName(String clearkName) {
        this.clearkName = clearkName;
    }

    public String getClearkCity() {
        return clearkCity;
    }

    public void setClearkCity(String clearkCity) {
        this.clearkCity = clearkCity;
    }

    public String getClearkNo() {
        return clearkNo;
    }

    public void setClearkNo(String clearkNo) {
        this.clearkNo = clearkNo;
    }

    public String getClearkEntryDate() {
        return clearkEntryDate;
    }

    public void setClearkEntryDate(String clearkEntryDate) {
        this.clearkEntryDate = clearkEntryDate;
    }
    

    @Override
    public String toString() {
        return clearkName ;
    }

    public Set<StoreDetails> getSd() {
        return sd;
    }

    public void setSd(Set<StoreDetails> sd) {
        this.sd = sd;
    }

   

    
    
}
