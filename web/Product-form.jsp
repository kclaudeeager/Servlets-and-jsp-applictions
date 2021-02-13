<%-- 
    Document   : Product-form
    Created on : 01 Feb 2021, 5:46:41 PM
    Author     : Kwizera
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="running.ProductQueries"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>User Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>
    <body>
        
             <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand"> User Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/listproduct" class="nav-link">Products</a></li>
                    </ul>
                </nav>
            </header>
                     <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${product != null}">
                            <form action="updateproduct" method="post">
                        </c:if>
                        <c:if test="${product == null}">
                            <form action="insertProduct" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${product != null}">
                                    Edit Product
                                </c:if>
                                <c:if test="${product == null}">
                                    Add New Product
                                </c:if>
                            </h2>
                        </caption
                        

                        <c:if test="${product != null}">
                            <input type="hidden" name="pid" value="<c:out value='${product.getId()}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Product Name</label> <input type="text" value="<c:out value='${product.getName()}' />" class="form-control" name="productname" required="required" placeholder="Enter The product name">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Product Price </label> <input type="text" value="<c:out value='${product.getPrice()}' />" class="form-control" name="price" placeholder="Enter The product price">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Supplier Name</label> 
                            <fieldset class="form-group">
                                <%--    <input type="text" value="<c:out value='${product.getSupplierName()}' />" class="form-control" name="supliername" placeholder="Enter The Supplier name">--%>
                            </fieldset>
                            <fieldset class="form-group">
                                <select name="supliername">
                                   
    <c:forEach items="${listUsers}" var="Users" >
        
        <option value="${Users.name}">${Users.name}</option>
         
    </c:forEach>
        <option value="${product.getSupplierName()}"  selected>${product.getSupplierName()}</option>
</select>
                            </fieldset>
                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
    </body>
</html>
