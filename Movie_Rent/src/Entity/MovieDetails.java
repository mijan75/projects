/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author User
 */
@Entity
public class MovieDetails {
    @Id @GeneratedValue 
   
    private int movieId;
    private String movieName;
    private String directorName;
    private String starName;
    private String releaseDate;
  
    @ManyToMany
    private Set<Price> p ;
    
    public MovieDetails() {
    }

    public MovieDetails(int movieId, String movieName, String directorName, String starName, String releaseDate, Set<Price> p) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.directorName = directorName;
        this.starName = starName;
        this.releaseDate = releaseDate;
        this.p = p;
    }

 
 
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    
    
    @Override
    public String toString() {
        return movieName;
    }

    public Set<Price> getP() {
        return p;
    }

    public void setP(Set<Price> p) {
        this.p = p;
    }

   

   
    
    
}
