/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package complementos;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author ANDRES
 */
public class conexion {
    public Connection conexionBD;
    public final String bd ="db_empresa1";
    public final String urlconexion = String.format("jdbc:mysql://127.0.0.1:3306/%s",bd);
     public final String usuario ="root";
     public final String contra ="root";
     public final String jdbc ="com.mysql.cj.jdbc.Driver";
     
     public void abrir_coxion(){
     try{
         Class.forName(jdbc);
         conexionBD = DriverManager.getConnection(urlconexion,usuario,contra);
         JOptionPane.showMessageDialog(null, "conexio  exitosa","exito",JOptionPane.INFORMATION_MESSAGE);
     }
     catch(Exception ex){
     System.out.println("errror............"+ ex.getMessage());}
     }
     
     public void cerrar_conexion (){
     try{
     conexionBD.close();
     }
     catch(SQLException ex){
         System.out.println("errror............"+ ex.getMessage());
     }
     }
     
}
