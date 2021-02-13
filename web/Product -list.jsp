<%-- 
    Document   : user-list
    Created on : Jan 31, 2021, 7:06:02 PM
    Author     : kwizera
--%>

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
                        <a href="https://www.javaguides.net" class="navbar-brand"> User
     Management App </a>
                    </div>
 <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
                    </ul> 
                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/listproduct" class="nav-link">Products</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Products</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/newproduct" class="btn btn-success">Add
     New Product</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        
                            <tr>
                                <th>Product_ID</th>
                                <th>Product_Name</th>
                                <th>Product_Price</th>
                                <th>Supplier_Name</th>
                                <th>Actions</th>
                            </tr>
                        
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="product" items="${listproduct}">

                                <tr>
                                    <td>
                                        <c:out value="${product.getId()}" />
                                    </td>
                                    <td>
                                        <c:out value="${product.getName()}" />
                                    </td>
                                    <td>
                                        <c:out value="${product.getPrice()}" />
                                    </td>
                                    <td>
                                        <c:out value="${product.getSupplierName()}" />
                                    </td>
                                    <td><a href="editproduct?pid=<c:out value='${product.getId()}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteproduct?pid=<c:out value='${product.getId()}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>