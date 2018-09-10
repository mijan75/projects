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
public class Product {
    private int id;
    private String model;
    private int price; 
    private String ProductCatagory;
    private String status;
    private String BrandName;
  
    public Product() {
    }
    
    
    public Product(int id, String model, int price, String ProductCatagory, String status, String BrandName) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.ProductCatagory = ProductCatagory;
        this.status = status;
        this.BrandName = BrandName;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public String getProductCatagory() {
        return ProductCatagory;
    }

    public String getStatus() {
        return status;
    }

    public String getBrandName() {
        return BrandName;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", model=" + model + ", price=" + price + ", ProductCatagory=" + ProductCatagory + ", status=" + status + ", BrandName=" + BrandName + '}';
    }

    

    
        
    
}
