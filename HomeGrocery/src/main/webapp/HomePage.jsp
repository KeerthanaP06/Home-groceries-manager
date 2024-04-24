<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    body {
        background-image: url('Home_grocery.jpg');
        background-size: cover;
        background-position: center;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .container {
        background-color: rgba(255, 255, 255, 0.7);
        padding: 20px;
        border-radius: 10px;
        text-align: left;
        height: 45vh;
        margin-left: 160px; /* Move the container to the right */
    }
    
    table {
        margin: auto;
        border-collapse: separate;
        border-spacing: 10px; 
    }
    td {
        padding: 10px;
        font-size: 20px;
    }
    heading{
        font-color:red;
    }
    
</style>
</head>
<body>
<div class="container">
    <h1 id="heading">Home Grocery Management</h1>
    <form action="register" method ="post">

    <table>
     <tr>
     <td>Item</td>
     <td><input type="text" name="item"></td>
     </tr>
     
     <tr>
     <td>Quantity</td>
     <td><input type="text" name="quantity"></td>
     </tr>
     
     <tr>
     <td>Brand Name</td>
     <td><input type="text" name="brand_name"></td>
     </tr>
     
     <tr>
     <td><input type="submit" value="Add"></td>
     <td><input type="reset" value="Cancel"></td>
     </tr>
     
    </table>
    
    <a href='GroceriesList'>Grocery List</a>
    </form>
</div>
</body>
</html>
