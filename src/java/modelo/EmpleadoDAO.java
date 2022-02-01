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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morenofamily
 */
public class EmpleadoDAO {
    ConexionMySql cn= new ConexionMySql();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Empleado Validar(String user,String dni){
        Empleado em= new Empleado();
        String sql="select * from empleado where User=? and Dni=?";
        try{
            con=cn.ConexionMySql();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, dni);
            rs=ps.executeQuery();
            while(rs.next()){
                em.setId(rs.getInt("IdEmpleado"));
                em.setUser(rs.getString("User"));
                em.setDni(rs.getString("Dni"));
                em.setNombre(rs.getString("Nombres"));
            }
        }catch(Exception e){
            System.out.println("Error3 "+ e.getMessage());
        }finally
        {
            cn.cerrarConexion();
        }
        return em;        
    }
    
    //OPERACIONES CRUD
    
    public List listar(){
        String sql="select * from empleado";
        List<Empleado>lista=new ArrayList();
        try{
            con=cn.ConexionMySql();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Empleado em=new Empleado();
                em.setId(rs.getInt(1));
                em.setDni(rs.getString(2));
                em.setNombre(rs.getString(3));
                em.setTelefono(rs.getString(4));
                em.setEstado(rs.getString(5));
                em.setUser(rs.getString(6));
                lista.add(em);
            }
        }catch(Exception e){
            System.out.println("Error4 "+ e.getMessage());
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
    
    public int agregar(Empleado em){
      String sql="insert into empleado (Dni,Nombres,Telefono,Estado,User) values(?,?,?,?,?)";
        try {
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNombre());
            ps.setString(3, em.getTelefono());
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getUser());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error5 "+ e.getMessage());
        }finally
        {
            cn.cerrarConexion();
        }
        return r;
    }
      
    public int actualizar(Empleado em){
        String sql="update empleado set Dni=?,Nombres=?,Telefono=?,Estado=?,User=? where IdEmpleado=?";
        try {
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNombre());
            ps.setString(3, em.getTelefono());
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getUser());
            ps.setInt(6, em.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error6 "+ e.getMessage());
        }finally
        {
            cn.cerrarConexion();
            
        }
        return r;
    }
    
    public void delete(int id){
        String sql="delete from empleado where IdEmpleado="+id;
        try {
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql);                    
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error7 "+ e.getMessage());
        } finally
        {
            cn.cerrarConexion();
        }     
    }
    
    public Empleado listarId(int id){
        Empleado em=new Empleado();
        String sql="select * from empleado where IdEmpleado="+id;
        try{
            con = cn.ConexionMySql();
            ps=con.prepareStatement(sql); 
            rs=ps.executeQuery();
            while(rs.next()){                              
                em.setDni(rs.getString(2));
                em.setNombre(rs.getString(3));
                em.setTelefono(rs.getString(4));
                em.setEstado(rs.getString(5));
                em.setUser(rs.getString(6));              
            }
        }catch(Exception e){
            System.out.println("Error8 "+ e.getMessage());
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
        return em;
    }
}
