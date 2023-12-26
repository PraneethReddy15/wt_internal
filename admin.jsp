<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="wt_internal_2.Product" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" href="./admin.css">
</head>
<body>
    <form action="DataServlet" method="get">
        <label for="productId">Product Id:</label>
        <input type="text" name="productId" id="productId" required><br>

        <label for="ProductName">Product Name:</label>
        <input type="text" name="ProductName" id="ProductName" required><br>

        <label for="Description">Description:</label>
        <input type="text" name="Description" id="Description" required><br>

        <label for="Price">Price:</label>
        <input type="text" name="Price" id="Price" required><br>

        <label for="AvailableQty">Available Qty:</label>
        <input type="text" name="AvailableQty" id="AvailableQty" required><br>

        <button type="submit">SUBMIT</button>
    </form>

    <% List<Product> productList = (List<Product>) request.getAttribute("productList");
       if (productList != null && !productList.isEmpty()) { %>
        <h2>Product List</h2>
        <table border="1">
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Available Quantity</th>
            </tr>
            <% for (Product product : productList) { %>
                <tr>
                    <td><%= product.getProductId() %></td>
                    <td><%= product.getProductName() %></td>
                    <td><%= product.getDescription() %></td>
                    <td><%= product.getPrice() %></td>
                    <td><%= product.getAvailableQty() %></td>
                </tr>
            <% } %>
        </table>
    <% } %>
</body>
</html>
