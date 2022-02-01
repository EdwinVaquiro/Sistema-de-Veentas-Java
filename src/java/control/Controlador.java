/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import conexiones.GenerarSerie;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import modelo.Producto;
import modelo.ProductoDAO;
import modelo.Venta;
import modelo.VentaDAO;

/**
 *
 * @author Morenofamily
 */
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //FECHA
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
    Date date = new Date(System.currentTimeMillis());

    //EMPLEADO
    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    int ide;

    //CLIENTE
    Cliente cl = new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    int idCl;

    //PRODUCTO
    Producto pro = new Producto();
    ProductoDAO pdao = new ProductoDAO();
    int idp;

    //VENTA
    Venta v = new Venta();
    List<Venta> lista = new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double total;
    int ce;
    
    String numeroserie;
    VentaDAO vdao = new VentaDAO();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar":
                    List listas = pdao.listar();
                    request.setAttribute("productos", listas);
                    break;
                case "Agregar":
                    String nombre = request.getParameter("txtNombres");
                    Double precio = Double.parseDouble(request.getParameter("txtPrecio"));
                    int stock = Integer.parseInt(request.getParameter("txtStock"));
                    String estado = request.getParameter("txtEstado");

                    pro.setNombre(nombre);
                    pro.setPrecio(precio);
                    pro.setStock(stock);
                    pro.setEstado(estado);
                    pdao.agregar(pro);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    idp = Integer.parseInt(request.getParameter("id"));
                    Producto p = pdao.listarId(idp);
                    request.setAttribute("producto", p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String nombre1 = request.getParameter("txtNombres");
                    Double precio1 = Double.parseDouble(request.getParameter("txtPrecio"));
                    int stock1 = Integer.parseInt(request.getParameter("txtStock"));
                    String estado1 = request.getParameter("txtEstado");

                    pro.setNombre(nombre1);
                    pro.setPrecio(precio1);
                    pro.setStock(stock1);
                    pro.setEstado(estado1);
                    pro.setId(idp);
                    pdao.actualizar(pro);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    idp = Integer.parseInt(request.getParameter("id"));
                    pdao.delete(idp);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }

        if (menu.equals("Cliente")) {
            switch (accion) {
                case "Listar": {
                    List lista = cdao.listar();
                    request.setAttribute("clientes", lista);
                    break;
                }
                case "Agregar": {
                    String dni = request.getParameter("txtDni");
                    String nombre = request.getParameter("txtNombres");
                    String Direccion = request.getParameter("txtDir");
                    String estado = request.getParameter("txtEstado");
                    cl.setDni(dni);
                    cl.setNombres(nombre);
                    cl.setDireccion(Direccion);
                    cl.setEstado(estado);
                    cdao.agregar(cl);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                }
                case "Editar": {
                    idCl = Integer.parseInt(request.getParameter("id"));
                    Cliente e = cdao.listarId(idCl);
                    request.setAttribute("cliente", e);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                }

                case "Actualizar": {
                    String dni1 = request.getParameter("txtDni");
                    String nombre1 = request.getParameter("txtNombres");
                    String telefono1 = request.getParameter("txtDir");
                    String estado1 = request.getParameter("txtEstado");
                    cl.setDni(dni1);
                    cl.setNombres(nombre1);
                    cl.setDireccion(telefono1);
                    cl.setEstado(estado1);
                    cl.setId(idCl);
                    cdao.actualizar(cl);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                }
                case "Delete": {
                    idCl = Integer.parseInt(request.getParameter("id"));
                    cdao.delete(idCl);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                }

                default: {
                    throw new AssertionError();
                }

            }
            request.getRequestDispatcher("Cliente.jsp").forward(request, response);
        }

        if (menu.equals("Empleado")) {
            switch (accion) {
                case "Listar":
                    List lista = edao.listar();
                    request.setAttribute("empleados", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nombre = request.getParameter("txtNombres");
                    String telefono = request.getParameter("txtTel");
                    String estado = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUser");
                    em.setDni(dni);
                    em.setNombre(nombre);
                    em.setTelefono(telefono);
                    em.setEstado(estado);
                    em.setUser(user);
                    edao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Empleado e = edao.listarId(ide);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nombre1 = request.getParameter("txtNombres");
                    String telefono1 = request.getParameter("txtTel");
                    String estado1 = request.getParameter("txtEstado");
                    String user1 = request.getParameter("txtUser");
                    em.setDni(dni1);
                    em.setNombre(nombre1);
                    em.setTelefono(telefono1);
                    em.setEstado(estado1);
                    em.setUser(user1);
                    em.setId(ide);
                    edao.actualizar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    edao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                default:

                    throw new AssertionError();

            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }

        if (menu.equals("NuevaVenta")) {
            switch (accion) {
                case "BuscarCliente":
                    String dni = request.getParameter("codigocliente");
                    cl.setDni(dni);
                    cl = cdao.buscar(dni);
                    request.setAttribute("c", cl);
                    request.setAttribute("nserie", numeroserie);
                    break;
                case "BuscarProducto":

                    idp = Integer.parseInt(request.getParameter("codigoproducto"));
                    pro = pdao.listarId(idp);
                    request.setAttribute("c", cl);
                    request.setAttribute("producto", pro);
                    request.setAttribute("lista", lista);
                    request.setAttribute("total", total);
                    request.setAttribute("nserie", numeroserie);
                    
                    break;
                case "Agregar":
                 
                    request.setAttribute("nserie", numeroserie);
                    request.setAttribute("c", cl);
                    total = 0.0;
                    item = item + 1;
                    cod = pro.getId();
                    descripcion = request.getParameter("nombresproducto");
                    precio = Double.parseDouble(request.getParameter("precio"));
                    cant = Integer.parseInt(request.getParameter("cant"));
                    subtotal = precio * cant;
                    v = new Venta();
                    v.setItem(item);
                    v.setIdproducto(cod);
                    v.setDescripcionP(descripcion);
                    v.setPrecio(precio);
                    v.setCantidad(cant);
                    v.setSubtotal(subtotal);
                    lista.add(v);
                    for (int i = 0; i < lista.size(); i++) {
                        total += lista.get(i).getSubtotal();
                    }
                    request.setAttribute("total", total);
                    request.setAttribute("lista", lista);
                    break;
                case "GenerarVenta":
                    //Actualizacion del Stock
                    for (int i = 0; i < lista.size(); i++) {
                        Producto pr = new Producto();
                        int cantidad = lista.get(i).getCantidad();
                        int idproducto = lista.get(i).getIdproducto();
                        ProductoDAO aO = new ProductoDAO();
                        pr = aO.buscar(idproducto);
                        int sac = pr.getStock() - cantidad;
                        aO.actualizarstock(idproducto, sac);
                    }

                    //Guardar Venta
                    v.setIdcliente(cl.getId());
                    v.setIdempleado(2);
                    v.setNumserie(numeroserie);
                    v.setFecha(formatter.format(date));
                    v.setMonto(total);
                    v.setEstado("1");
                    vdao.GuardarVenta(v);
                    //Guardar Detalle Ventas
                    int idv = Integer.parseInt(vdao.IdVentas());

                    for (int i = 0; i < lista.size(); i++) {
                        v = new Venta();
                        v.setId(idv);
                        v.setIdproducto(lista.get(i).getIdproducto());
                        v.setCantidad(lista.get(i).getCantidad());
                        v.setPrecio(lista.get(i).getPrecio());
                        vdao.GuardarDetalleVentas(v);
                    }
                    break;

                case "Editar":       
                    total = 0.0;
                    int idven = Integer.parseInt(request.getParameter("Ide"));
                    ce = Integer.parseInt(request.getParameter("canti"));
                    
                    for (int i = 0; i < lista.size(); i++) {
                        if (lista.get(i).getItem() == idven) {  
                            Venta ve = new Venta(); 
                            ve.setItem(lista.get(i).getItem());
                            ve.setIdproducto(lista.get(i).getIdproducto());
                            ve.setDescripcionP(lista.get(i).getDescripcionP());
                            ve.setPrecio(lista.get(i).getPrecio());
                            ve.setCantidad(5);                                                    
                            subtotal = lista.get(i).getPrecio() * 5;
                            ve.setSubtotal(subtotal);                       
                            lista.set(i, ve);                          
                        }
                          total += lista.get(i).getSubtotal();
                    }
                   // request.setAttribute("cantis", ce);
                    request.setAttribute("total", total);
                    request.setAttribute("lista", lista);
                    request.setAttribute("nserie", numeroserie);
                    request.getRequestDispatcher("Controlador?menu=NuevaVenta&accion=default").forward(request, response);
                    break;
                case "Elimi":
                    
                    int idvent = Integer.parseInt(request.getParameter("Id"));
                    for (int i = 0; i < lista.size(); i++) {
                        if (lista.get(i).getItem()== idvent) {                           
                            total = total - lista.get(i).getSubtotal();
                            lista.remove(i);
                            break;
                        }
                    }                   
                    request.setAttribute("total", total);
                    request.setAttribute("lista", lista);
                    request.setAttribute("nserie", numeroserie);
                    break;

                case "Cancelar":
                    item = 1;
                    subtotal=0;
                    total=0.0;
                    lista.clear();
                    break;

                default:
                    numeroserie = vdao.GenerarSerie();
                    if (numeroserie == null) {
                        numeroserie = "00000001";
                        request.setAttribute("nserie", numeroserie);
                    } else {
                        int incrementar = Integer.parseInt(numeroserie);
                        GenerarSerie gs = new GenerarSerie();
                        numeroserie = gs.NumeroSerie(incrementar);
                        request.setAttribute("nserie", numeroserie);
                    }
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
