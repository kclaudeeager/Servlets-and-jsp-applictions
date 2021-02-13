/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package running;

/**
 *
 * @author kwizera
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProductQueries {
 private final String jdbcURL = "jdbc:mysql://localhost/demo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
private final String jdbcUsername = "root";
private final String jdbcPassword = "";

    private static final String INSERT_Product = "INSERT INTO product(PName,Pprice,SupplierId)VALUES(?,?,?)";

    private static final String SELECT_Product_BY_ID = "select product.Pid,product.PName,product.Pprice,users.name from product INNER JOIN users ON product.SupplierId=users.id where Pid =?";
    private static final String SELECT_ALL_Products = "select product.Pid,product.PName,product.Pprice,users.name from product INNER JOIN users ON product.SupplierId=users.id order by product.Pid ASC";
    private static final String DELETE_Products_SQL = "delete from product where Pid =?;";
    private static final String UPDATE_Products_SQL = "update product set PName = ?,Pprice= ?,SupplierID=? where Pid = ?";

    public ProductQueries() {}

    public Connection getConnection() {
        Connection connection = null;
        try {
           Class.forName("com.mysql.jdbc.Driver");   //register driver

      connection=DriverManager.getConnection(jdbcURL ,jdbcUsername ,jdbcPassword);
      System.out.println("Connected");
        } catch (SQLException e) {
            // TODO Auto-generated catch block

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block

        }
        return connection;
    }

    public void insertProduct(Product product) throws SQLException {
        System.out.println(INSERT_Product);
        // try-with-resource statement will auto close the connection.
          
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Product)) {
              if (GetSupplierID()>0){
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getPrice());
            preparedStatement.setInt(3, GetSupplierID());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
              }
              // System.out.println("The user is "+UserServlet.Suppliername+" The id is "+GetSupplierID());
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
public ArrayList<String> SelectUsers()
{
    ArrayList<String> Suppliers=new ArrayList<>();
    String query="select name from users";
    
    try (Connection connection = getConnection();
     PreparedStatement preparedStatement = connection.prepareStatement(query))
    {
      ResultSet result=preparedStatement.executeQuery();
      while(result.next())
      {
          Suppliers.add(result.getString("name"));
      }
      for(String supp:Suppliers)
                   System.out.println("The users are "+supp);
    }
    catch (SQLException ex) {
         Logger.getLogger(ProductQueries.class.getName()).log(Level.SEVERE, null, ex);
     }
    return Suppliers;
}
    public Product selectProduct(int id) {
        Product product = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Product_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("PName");
                String price = rs.getString(3);
                String supplier = rs.getString("users.name");
                product= new Product(id, name, price, supplier);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    public List < Product > selectAllProducts() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Product > products = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Products);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString("PName");
                String Price = rs.getString("Pprice");
                String Supplier = rs.getString("name");
                products.add(new Product(id, name, Price, Supplier));
                
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_Products_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateProduct(Product p) {
        
        boolean rowUpdated = false;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_Products_SQL);) {
            if (GetSupplierID()>0)
        {
            statement.setString(1, p.getName());
            statement.setString(2, p.getPrice());
            statement.setInt(3,GetSupplierID());
            statement.setInt(4, p.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
           
        } catch (SQLException ex) {
            
         Logger.getLogger(ProductQueries.class.getName()).log(Level.SEVERE, null, ex);
     }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    private int GetSupplierID() throws SQLException
    {
        String Supplier=UserServlet.Suppliername;
        int Suppid = 0;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select users.id from users where name=?");
        preparedStatement.setString(1, Supplier);
         ResultSet rs = preparedStatement.executeQuery();
         if(rs.next())
             Suppid=rs.getInt("id");
         System.out.println("The user is "+UserServlet.Suppliername);
         
        return Suppid;
    }
}