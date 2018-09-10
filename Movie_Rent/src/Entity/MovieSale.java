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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Fetch;

/**
 *
 * @author User
 */
@Entity

public class MovieSale {
    @Id @GeneratedValue 
    private int id;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<OfferedMovie> om;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Customer> customer;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Cleark> cleark;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Price> price;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<StoreDetails> sd;
    
    private String date;

    public MovieSale() {
    }

    public MovieSale(int id, Set<OfferedMovie> om, Set<Customer> customer, Set<Cleark> cleark, Set<Price> price, Set<StoreDetails> sd, String date) {
        this.id = id;
        this.om = om;
        this.customer = customer;
        this.cleark = cleark;
        this.price = price;
        this.sd = sd;
        this.date = date;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<OfferedMovie> getOm() {
        return om;
    }

    public void setOm(Set<OfferedMovie> om) {
        this.om = om;
    }

    public Set<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(Set<Customer> customer) {
        this.customer = customer;
    }

    public Set<Cleark> getCleark() {
        return cleark;
    }

    public void setCleark(Set<Cleark> cleark) {
        this.cleark = cleark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    @Override
    public String toString() {
        return "MovieSale{" + "id=" + id + ", om=" + om + ", customer=" + customer + ", cleark=" + cleark + ", date=" + date + '}';
    }

    public Set<Price> getPrice() {
        return price;
    }

    public void setPrice(Set<Price> price) {
        this.price = price;
    }

    public Set<StoreDetails> getSd() {
        return sd;
    }

    public void setSd(Set<StoreDetails> sd) {
        this.sd = sd;
    }
    
    
}
