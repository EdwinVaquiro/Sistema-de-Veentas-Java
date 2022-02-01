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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Morenofamily
 */
public class ProductoDAO {
    ConexionMySql cn= new ConexionMySql();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Producto buscar(int id){
        Producto p = new Producto();
        String sql="select * from producto where idproducto="+id;
        try {
            con=cn.ConexionMySql();
            ps=con.prepareStatement(sql);          
            rs=ps.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("Error13 "+ e.getMessage());
        }
        return p;
    }
    
    public int actualizarstock(int id,int stock){
        String sql="update producto set Stock=? where IdProducto=?";
        try {
             con=cn.ConexionMySql();
             ps=con.prepareStatement(sql); 
             ps.setInt(1, stock);
             ps.setInt(2, id);
             ps.executeUpdate();
        } catch (Exception e) {
             System.out.println("Error14 "+ e.getMessage());
        }
        return r;
    }
    
    public int actualizarcanti(int id,int canti){
        String sql="update producto set Cantidad=? where IdProducto=?";
        try {
             con=cn.ConexionMySql();
             ps=con.prepareStatement(sql); 
             ps.setInt(1, canti);
             ps.setInt(2, id);
             ps.executeUpdate();
        } catch (Exception e) {
             System.out.println("Error16 "+ e.getMessage());
        }
        return r;
    }
    
    //OPERACIONES CRUD
    
    public List listar(){
        String sql="select * from producto";
        List<Producto>lista=new ArrayList();
        try{
            con=cn.ConexionMySql();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Producto pro=new Producto();
                pro.setId(rs.getInt(1));               
                pro.setNombre(rs.getString(2));
                pro.setPrecio(rs.getDouble(3));
                pro.setStock(rs.getInt(4));
                pro.setEstado(rs.getString(5));
                lista.add(pro);
            }
        }catch(Exception e){
            System.out.println("Error4 "+ e.getMessage());
        }
        return lista;
    }
    
    public int agregar(Producto pro){
      String sql="insert into producto (Nombres,Precio,Stock,Estado) values(?,?,?,?)";
        try {
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql);
            ps.setString(1, pro.getNombre());
            ps.setDouble(2, pro.getPrecio());
            ps.setInt(3, pro.getStock());
            ps.setString(4, pro.getEstado());          
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error5 "+ e.getMessage());
        }
        return r;
    }
      
    public int actualizar(Producto pro){
        String sql="update producto set Nombres=?,Precio=?,Stock=?,Estado=? where IdProducto=?";
        try {
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql);
            ps.setString(1, pro.getNombre());
            ps.setDouble(2, pro.getPrecio());
            ps.setInt(3, pro.getStock());
            ps.setString(4, pro.getEstado());           
            ps.setInt(5, pro.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error6 "+ e.getMessage());
        }
        return r;
    }
    
    public void delete(int id){
        String sql="delete from producto where IdProducto="+id;
        try {
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql);                    
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error7 "+ e.getMessage());
        }      
    }
    
    public Producto listarId(int id){
        Producto pro=new Producto();
        String sql="select * from producto where IdProducto="+id;
        try{
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql); 
            rs=ps.executeQuery();
            while(rs.next()){    
                pro.setId(rs.getInt(1));
                pro.setNombre(rs.getString(2));
                pro.setPrecio(rs.getDouble(3));
                pro.setStock(rs.getInt(4));
                pro.setEstado(rs.getString(5));              
            }
        }catch(Exception e){
            System.out.println("Error8 "+ e.getMessage());
        }
        return pro;
    }
}
