package conexiones;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Maicol Alexander Moreno
 */
//Clase con Singleton
public class ConexionMySql {
    //Variable clase
    public static ConexionMySql instance; // Singleton
    Connection cnn;
    String url = "jdbc:mysql://localhost:3306/bd_ventas";
    String user = "root";
    String pass = "";
    public  Connection ConexionMySql(){ 
        try {
            Class.forName("com.mysql.jdbc.Driver");//Cargar el driver   
            cnn  = (Connection) DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex  ) {
            System.out.println("Error1 "+ ex.getMessage());
        } catch (SQLException ex2) {
            System.out.println("Error2 "+ ex2.getMessage());
        }
        return cnn;
    }
    
    public static synchronized ConexionMySql getInstance(){
        if(instance == null){
            instance = new ConexionMySql();
        }
        return instance;
    }

    public Connection getCnn() {
        return cnn;
    }

    public void setCnn(Connection cnn) {
        this.cnn = cnn;
    }
    public void cerrarConexion(){
        instance = null;
    }
}

