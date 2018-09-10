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

/**
 *
 * @author User
 */
@Entity
public class OfferedMovie {
    @Id @GeneratedValue 
    private int offeredId;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<MovieDetails> mv;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<StoreDetails> sd;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Distributor> distributor;
    
    private String date;

    public OfferedMovie() {
    }

    public OfferedMovie(int offeredId, Set<MovieDetails> mv, Set<StoreDetails> sd, Set<Distributor> distributor, String date) {
        this.offeredId = offeredId;
        this.mv = mv;
        this.sd = sd;
        this.distributor = distributor;
        this.date = date;
    }

    

   

 

    public int getOfferedId() {
        return offeredId;
    }

    public void setOfferedId(int offeredId) {
        this.offeredId = offeredId;
    }

    public Set<MovieDetails> getMv() {
        return mv;
    }

    public void setMv(Set<MovieDetails> mv) {
        this.mv = mv;
    }

    public Set<StoreDetails> getSd() {
        return sd;
    }

    public void setSd(Set<StoreDetails> sd) {
        this.sd = sd;
    }

    public Set<Distributor> getDistributor() {
        return distributor;
    }

    public void setDistributor(Set<Distributor> distributor) {
        this.distributor = distributor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return mv +"";
    }
    
    
}
