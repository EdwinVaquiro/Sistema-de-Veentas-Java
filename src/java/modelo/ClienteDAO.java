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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Morenofamily
 */
public class ClienteDAO {
    ConexionMySql cn= new ConexionMySql();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
   public Cliente buscar(String dni){
       Cliente c = new Cliente();
       String sql="select * from cliente where Dni="+dni;
       try {
           con=cn.ConexionMySql();
           ps=con.prepareStatement(sql);
           rs=ps.executeQuery();
           while(rs.next()){
              c.setId(rs.getInt(1));
              c.setDni(rs.getString(2));
              c.setNombres(rs.getString(3));
              c.setDireccion(rs.getString(4));
              c.setEstado(rs.getString(5));
           }
       } catch (Exception e) {
           System.out.println("Error9"+ e.getMessage());
       }
       return c;
   }
    
    //OPERACIONES CRUD
    
    public List listar(){
        String sql="select * from cliente";
        List<Cliente>lista=new ArrayList();
        try{
            con=cn.ConexionMySql();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Cliente em=new Cliente();
                em.setId(rs.getInt(1));
                em.setDni(rs.getString(2));
                em.setNombres(rs.getString(3));
                em.setDireccion(rs.getString(4));
                em.setEstado(rs.getString(5));
                lista.add(em);
            }
        }catch(SQLException e){
            System.out.println("Error listar cliente "+ e.getMessage());
        }finally
        {
            cn.cerrarConexion();
            if(rs!=null)
            {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Error en cerrar el reader listar "+ ex.getMessage());
                }
            }
        }
        return lista;
    }
    
    public int agregar(Cliente em){
      String sql="insert into cliente (Dni,Nombres,Direccion,Estado) values(?,?,?,?)";
        try {
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNombres());
            ps.setString(3, em.getDireccion());
            ps.setString(4, em.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Agregar cliente "+ e.getMessage());
        }finally
        {
            cn.cerrarConexion();
        }
        return r;
    }
      
    public int actualizar(Cliente em){
        String sql="update cliente set Dni=?,Nombres=?,Direccion=?,Estado=? where IdCliente=?";
        try {
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNombres());
            ps.setString(3, em.getDireccion());
            ps.setString(4, em.getEstado());
            ps.setInt(5, em.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(" Error en actualizar cliente "+ e.getMessage());
        }finally
        {
            cn.cerrarConexion();   
        }
        return r;
    }
    
    public void delete(int id){
        String sql="delete from cliente where IdCliente="+id;
        try {
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql);                    
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en borrar cliente"+ e.getMessage());
        } finally
        {
            cn.cerrarConexion();
        }     
    }
    
    public Cliente listarId(int id){
        Cliente em=new Cliente();
        String sql="select * from Cliente where IdCliente="+id;
        try{
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql); 
            rs=ps.executeQuery();
            while(rs.next()){                              
                em.setDni(rs.getString(2));
                em.setNombres(rs.getString(3));
                em.setDireccion(rs.getString(4));
                em.setEstado(rs.getString(5));          
            }
        }catch(Exception e){
            System.out.println("Error en listar por id del cliente "+ e.getMessage());
        }finally
        {
            cn.cerrarConexion();
            if(rs!=null)
            {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Error en cerrar el reader listar id "+ ex.getMessage());
                }
            }
        }
        return em;
    }
    
}
