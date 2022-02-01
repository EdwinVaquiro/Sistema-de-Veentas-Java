<%-- 
    Document   : Producto
    Created on : 6/10/2021, 03:20:30 PM
    Author     : Morenofamily
--%>

<%@ taglib prefix="o" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">    
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">      
        <link rel="icon" href="images/icono.png" sizes="64x64">
        <title>Producto</title>  
    </head>
    <body>
        <div class="d-flex">
             <div class="card col-sm-4">
             <div class="card-body">
                 <form action="Controlador?menu=Producto" method="POST">              
                    <div class="form-group">
                        <label>Nombres</label>
                        <input type="text" value="${producto.getNombre()}" name="txtNombres" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Precio</label>
                        <input type="text" value="${producto.getPrecio()}" name="txtPrecio" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Stock</label>
                        <input type="text" value="${producto.getStock()}"  name="txtStock" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Estado</label>
                        <input type="text" value="${producto.getEstado()}" name="txtEstado" class="form-control">
                    </div>
                    <input type="submit" name="accion" value="Agregar" class="btn btn-success">
                    <input type="submit" name="accion" value="Actualizar" class="btn btn-warning">           
                </form>
            </div>           
        </div>
        <div class="col-sm-8">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>NOMBRES</th>
                        <th>PRECIO</th>
                        <th>STOCK</th>
                        <th>ESTADO</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                <o:forEach var="pro" items="${productos}">
                    <tr>
                        <td>${pro.getId()}</td>                        
                        <td>${pro.getNombre()}</td>
                        <td>${pro.getPrecio()}</td>
                        <td>${pro.getStock()}</td>
                        <td>${pro.getEstado()}</td>
                        <td>
                            <a class="btn btn-info" href="Controlador?menu=Producto&accion=Editar&id=${pro.getId()}">Buscar</a>
                            <a class="btn btn-danger" href="Controlador?menu=Producto&accion=Delete&id=${pro.getId()}">Eliminar</a>
                        </td>
                    </tr>
                </o:forEach>                  
                </tbody>
            </table>            
        </div>
        </div>    
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>    
    </body>
</html>
