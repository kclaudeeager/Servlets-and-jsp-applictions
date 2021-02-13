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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    public static String Suppliername;
    private ProductQueries productq;
int pid;
    @Override
    public void init() {
        userDAO = new UserDAO();
        productq=new ProductQueries();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        doGet(request, response);
        
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();
         
        try {
              
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    
                    break;
                  case "/newproduct":
                   // showNewProductForm(request, response);
                    ListSuppliers(request,response);
                    
                    
                    break;
                case "/insertProduct":
                    insertProduct(request, response);
                  
                    break;
                case "/deleteproduct":
                    deleteProduct(request, response);
                    break;
                case "/editproduct":
                    showProductEditForm(request, response);
                    break;
                case "/updateproduct":
                    updateProduct(request, response);
                   
                    break;
                  case "/listproduct":
                    listProducts(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
                    
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < User > listUser = userDAO.selectAllUsers();
    request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(name, email, country);
        userDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User book = new User(id, name, email, country);
        userDAO.updateUser(book);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("list");

    }

    private void showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
         try {
            RequestDispatcher dispatcher;
             dispatcher = request.getRequestDispatcher("Product-form.jsp");
            dispatcher.forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("productname");
        String price = request.getParameter("price");
        String Supplier = request.getParameter("supliername");
        Product newProduct = new Product(name, price, Supplier);
        Suppliername=newProduct.getSupplierName();
        
        productq.insertProduct(newProduct);
        
        response.sendRedirect("listproduct");
   
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
         throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("pid"));
        productq.deleteProduct(id);
        response.sendRedirect("listproduct");
       
    }

    private void showProductEditForm(HttpServletRequest request, HttpServletResponse response) 
    throws SQLException,  IOException {
        try {
             pid = Integer.parseInt(request.getParameter("pid"));
            
            Product existingProduct = productq.selectProduct(pid);
                List<User> ListUsers = userDAO.list();
            request.setAttribute("listUsers", ListUsers);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Product-form.jsp");
            request.setAttribute("product", existingProduct);
            dispatcher.forward(request, response);
            
        } catch (ServletException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    private void updateProduct(HttpServletRequest request, HttpServletResponse response){
            try {
            int id = pid;    
              String name = request.getParameter("productname");
               String price = request.getParameter("price");
               String Supplier = request.getParameter("supliername");


//                  List < Product > listProduct = productq.selectAllProducts();
//    request.setAttribute("listProducts", listProduct);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("Product-list.jsp");
//        dispatcher.forward(request, response);
       Product newProduct = new Product(id,name, price, Supplier);
        Suppliername=newProduct.getSupplierName();
            productq.updateProduct(newProduct);
            
            response.sendRedirect("listproduct");
        } catch (IOException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ServletException ex) {
//            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }  
    }
        private void listProducts(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Product > listproduct = productq.selectAllProducts();
    request.setAttribute("listproduct", listproduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Product -list.jsp");
        dispatcher.forward(request, response);
        return;
    }
        private void ListSuppliers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
               try {
 
            List<User> ListUsers = userDAO.list();
            request.setAttribute("listUsers", ListUsers);
 
            RequestDispatcher dispatcher = request.getRequestDispatcher("Product-form.jsp");
            dispatcher.forward(request, response);
 
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
}
}