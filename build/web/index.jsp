<%-- 
    Document   : index
    Created on : 6/10/2021, 12:31:43 PM
    Author     : Morenofamily
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">       
        <meta name="viewport" content="width=device-width, initial-scale=1.0">    
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">      
        <link rel="icon" href="images/icono.png" sizes="64x64">
        <title>Login</title>       
    </head>
    <body>

        <div class="container mt-4 col-lg-4">
            <div class="card col-sm-10">
                <div class="card-body">
                    <form class="form-sing" action="Validar" method="POST">
                        <div class="form-group text-center">
                            <h3>Login</h3>
                            <img src="images/logo.jpg" alt="70" width="170"/>
                            <br/>
                            <label>Bienvenidos al Sistema</label>
                        </div>  
                        <div class="form-group">
                            <label>Usuario:</label>   
                            <input type="text" name="txtuser" class="form-control" required="true">
                        </div>  
                        <div class="form-group">
                            <label>Password:</label>
                            <input type="password" name="txtpass" class="form-control" required="true">
                        </div>  
                        <input type="submit" name="accion" value="Ingresar" class="btn btn-info btn btn-outline-dark btn-block">
                    </form>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    </body>
</html>

