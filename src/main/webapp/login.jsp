<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>Servlets</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:useBean id="counter" class="com.uniovi.sdi.Counter" scope="application"/>
<jsp:setProperty name="counter" property="increase" value="1"/>
<%
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    // si el credencial es valido lo guardo en la sesion
    if (name != null && name.equals("admin") && password != null && password.equals("admin")){
        request.getSession().setAttribute("user", "admin");
        // enviar el usuario a admin.jsp
        response.sendRedirect("admin.jsp");
    }
    // si el credencial no es admin, es otro
    else if (request.getSession().getAttribute("user") != null){
        request.getRequestDispatcher("index.jsp");
    }
    else{
        request.getSession().setAttribute("user", name);
    }
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li class="nav-item"><a class="nav-link" href="AddToShoppingCart">Carrito</a></li>
            <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
            <li class="nav-item"><a class="nav-link" href="admin.jsp">Administrar productos</a></li>
            <li class="nav-item>"><a class="nav-link" href="products">Productos</a></li>
            <li class="nav-item"><a class="nav-link" href="comment.jsp">Comentarios</a> </li>
        </ul>
        <div class="nav navbar-right">
            <div class="center-block">
                <jsp:getProperty name="counter" property="total"/>
                Visitas
            </div>
        </div>
    </div>
</nav>

<!-- Contenido -->
<div class="container" id="main-container">
    <h2>Identificación de usuario</h2>
    <form class="form-horizontal" method="post" action="login.jsp">
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Nombre:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name" required="true"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Password:</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" name="password"
                       required="true"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">Agregar</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>