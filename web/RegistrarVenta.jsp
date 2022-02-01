<%-- 
    Document   : RegistrarVenta
    Created on : 6/10/2021, 03:21:25 PM
    Author     : Morenofamily
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">    
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">      
        <link rel="icon" href="images/icono.png" sizes="64x64">
        <title>RegistrarVenta</title>
        <style>
            @media print{
                .parte01, .btn, .accion{
                    display: none;
                }
            }
        </style>
    </head>
    <body>
        <div class="d-flex">
            <div class="col-lg-5 parte01">
                <div class="card">
                    <form action="Controlador?menu=NuevaVenta" method="POST">
                        <div class="card-body">
                            <div class="form-group">
                                <label>Datos del Cliente</label>                           
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-5 d-flex">
                                    <input type="text" name="codigocliente" value="${c.getDni()}" class="form-control" placeholder="Codigo">
                                    <input type="submit" name="accion" value="BuscarCliente" class="btn btn-info">                      
                                </div>          
                                <div class="col-sm-6">
                                    <input type="text" name="nombrescliente" value="${c.getNombres()}" class="form-control col-sm-11" placeholder="Datos Cliente" readonly="true"> 
                                </div>                                                                 
                            </div>
                            <div class="form-group">
                                <label>Datos Producto</label> 
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-5 d-flex">
                                    <input type="text" name="codigoproducto" value="${producto.getId()}"  class="form-control" placeholder="Codigo">
                                    <button type="submit" name="accion" value="BuscarProducto" class="btn btn-info">Buscar</button>                      
                                </div>          
                                <div class="col-sm-6">
                                    <input type="text" name="nombresproducto" value="${producto.getNombre()}" class="form-control col-sm-11" placeholder="Datos Producto" readonly="true"> 
                                </div>                                                                 
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-5 d-flex">
                                    <input type="text" name="precio" value="${producto.getPrecio()}" class="form-control" placeholder="$/.0.00" readonly="true">                                                 
                                </div>          
                                <div class="col-sm-3">
                                    <input type="number" name="cant" value="1"  placeholder="" class="form-control col-sm-10" min="1" max="${producto.getStock()}"> 
                                </div>                                                                 
                                <div class="col-sm-3">
                                    <input type="text" name="stock" value="${producto.getStock()}" placeholder="Stock" class="form-control col-sm-10" readonly="true"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" name="accion" value="Agregar" class="btn btn-success">Agregar</button>
                            </div>
                        </div>
                    </form>    
                </div>
            </div>
            <div class="col-sm-7">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex col-sm-5 ml-auto">
                            <label>Nro.Serie:</label>
                            <input type="text" name="NroSerie" value="${nserie}" class="form-control">
                        </div>
                        <br>
                        <table class="table table-hover">
                            <thead>
                                <tr class="text-center">
                                    <th>Nro</th>
                                    <th>Codigo</th>
                                    <th>Descripcion</th>
                                    <th>Precio</th>
                                    <th>Cantidad</th>
                                    <th>Subtotal</th>
                                    <th class="accion">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="ven" items="${lista}">
                                <tr class="text-center">
                                    <td>${ven.getItem()}</td>
                                    <td>${ven.getIdproducto()}</td>
                                    <td>${ven.getDescripcionP()}</td>
                                    <td>${ven.getPrecio()}</td>
                                    <td><input type="number" name="canti" value="${ven.getCantidad()}" class="form-control"></td>                                                                  
                                    <td>${ven.getSubtotal()}</td>
                                    <td class="d-flex">
                                        <a href="Controlador?menu=NuevaVenta&accion=Editar&Ide=${ven.getItem()}&canti=${ven.getCantidad()}" class="btn btn-primary">Editar</a>
                                        <a href="Controlador?menu=NuevaVenta&accion=Elimi&Id=${ven.getItem()}" class="btn btn-dark" style="margin-left: 10px">Eliminar</a>
                                    </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="card-footer">
                        <div class="col-sm-6">
                            <a href="Controlador?menu=NuevaVenta&accion=GenerarVenta" onclick="print()" class="btn btn-warning">Generar Venta</a>                           
                            <a href="Controlador?menu=NuevaVenta&accion=Cancelar" class="btn btn-danger">Cancelar</a>                         
                        </div>
                        <div class="col-sm-3 ml-auto">
                            <input type="text" name="txtTotal" value="$/.${total}" class="form-control">
                        </div>
                    </div>
                </div>
                <div>
                    
                </div>  
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>    
    </body>
</html>
