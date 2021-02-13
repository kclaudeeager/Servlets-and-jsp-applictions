/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package running;

import java.util.Date;

/**
 *
 * @author Kwizera
 */
public class Product {
    protected String productname;
    protected String price;
    protected int pid;
    protected String supliername;
    Date date;
    public Product()
    {
        
    }
    public Product(String productname,String price)
    {
        this.productname=productname;
        this.price=price;
        
    }
        public Product(String productname,String price,int pid)
    {
        this.productname=productname;
        this.price=price;
        this.pid=pid;
    }
              public Product(String productname,String price,String supliername)
    {
        this.productname=productname;
        this.price=price;
    
        this.supliername=supliername;
    }
 

    Product(int pid, String productname, String price, String suppliername) {
            this.productname=productname;
        this.price=price;
        this.pid=pid;
        this.supliername=suppliername;
         
    }
               public int getId() {
        return pid;
    }
    public void setId(int id) {
        pid = id;
    }
    public String getName() {
        return productname;
    }
    public void setName(String name) {
        this.productname = name;
    }
    public String getSupplierName() {
        return supliername;
    }
    public void setSupplier(String name) {
        this.supliername = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    
}
