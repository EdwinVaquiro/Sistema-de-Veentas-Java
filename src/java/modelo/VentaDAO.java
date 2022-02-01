/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conexiones.ConexionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Morenofamily
 */
public class VentaDAO {
    Connection con;
    ConexionMySql cn= new ConexionMySql();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public String GenerarSerie(){
        String numeroserie="";
        String sql="select max(NumeroSerie) from ventas";
        try {
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                numeroserie=rs.getString(1);
            }
            
        } catch (Exception e) {
            System.out.println("Error10 "+ e.getMessage());
        }
        return numeroserie;
    }
    
    public String IdVentas(){
        String idventas="";
        String sql="select max(IdVentas) from ventas";
        try {
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
               idventas=rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("Error11 "+ e.getMessage());
        }
        return idventas;
    }
    
    public int GuardarVenta(Venta ve){
        String sql="insert into ventas (IdCliente,IdEmpleado,NumeroSerie,FechaVentas,Monto,Estado) values(?,?,?,?,?,?)";
        try {
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql);
            ps.setInt(1, ve.getIdcliente());
            ps.setInt(2, ve.getIdempleado());
            ps.setString(3, ve.getNumserie());
            ps.setString(4, ve.getFecha());
            ps.setDouble(5, ve.getMonto());
            ps.setString(6, ve.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error11 "+ e.getMessage());
        }
        return r;
    }
    
    public int GuardarDetalleVentas(Venta ven){
        String sql="insert into detalle_ventas (IdVentas,IdProducto,Cantidad,PrecioVenta) values(?,?,?,?)";
        try {
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql);
            ps.setInt(1, ven.getId());
            ps.setInt(2, ven.getIdproducto());
            ps.setInt(3, ven.getCantidad());
            ps.setDouble(4, ven.getPrecio());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error12 "+ e.getMessage());
        }
        
        return r;
    }
    
    /* public void delete(int id){
        String sql="delete from producto where IdProducto="+id;
        try {
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql);                    
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error7 "+ e.getMessage());
        }      
    }*/
}
