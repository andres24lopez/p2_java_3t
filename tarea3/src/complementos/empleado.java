/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package complementos;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;




/**
 *
 * @author ANDRES
 */
public class empleado extends persona {
    private int id;
    private String puesto;
    
    private conexion cn;
    
    
    
    public empleado(){}

    public empleado(int id, String puesto, String codigo, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(codigo, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.id = id;
        this.puesto = puesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
    

    
 public DefaultTableModel leer (){
    DefaultTableModel tabla = new DefaultTableModel ();
    try{
    cn =new conexion();
    cn.abrir_coxion();
    String query;
    query ="SELECT e.id_empleado,e.codigo,e.nombres,e.apellidos,e.direccion,e.telefono,e.fecha_nacimiento,p.puesto FROM empleados e\n" +
"JOIN puestos p ON e.id_puesto = p.id_puesto;";
    ResultSet consulta =cn.conexionBD.createStatement().executeQuery(query);
   
    String encabezadoo[] ={"id","codigo","nombres","apellidos","direccion","telefono","nacimiento","puesto"};
    tabla.setColumnIdentifiers(encabezadoo);
    
    String datos []= new String [8];
    
    while (consulta.next()){
    datos [0]=consulta.getString("id_empleado");
    datos [1]=consulta.getString("codigo");
    datos [2]=consulta.getString("nombres");
    datos [3]=consulta.getString("apellidos");
    datos [4]=consulta.getString("direccion");
    datos [5]=consulta.getString("telefono");
    datos [6]=consulta.getString("fecha_nacimiento");
     datos [7]=consulta.getString("puesto");
    tabla.addRow(datos);
    
    }
    cn.cerrar_conexion();
    
    }
    
    
    catch(Exception ex){
    System.out.println("error :"+ex.getMessage());
    }
    return tabla;
    
    }
 
 
 
 
 public int obtenerIdPuesto(String nombrePuesto) {
    int idPuesto = 0;
    try {
        cn = new conexion();
        cn.abrir_coxion();
        
        // Consulta para obtener el id_puesto basado en el nombre del puesto
        String query = "SELECT id_puesto FROM puestos WHERE puesto = ?";
        PreparedStatement parametro = cn.conexionBD.prepareStatement(query);
        parametro.setString(1, nombrePuesto);
        
        ResultSet rs = parametro.executeQuery();
        if (rs.next()) {
            idPuesto = rs.getInt("id_puesto");
        }
        
        cn.cerrar_conexion();
    } catch (Exception ex) {
        System.out.println("Error al obtener id_puesto: " + ex.getMessage());
    }
    return idPuesto;
}
 
 
 
 
 
 

   
 @Override
    public void agregar (){
        try{
        PreparedStatement parametro;
        String query ="INSERT INTO empleados(codigo, nombres, apellidos, direccion, telefono, fecha_nacimiento, id_puesto) VALUES (?, ?, ?, ?, ?, ?, ?);";
            cn = new conexion();
            cn.abrir_coxion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, this.getCodigo());
            parametro.setString(2, this.getNombres());
            parametro.setString(3, this.getApellidos());
            parametro.setString(4, this.getDireccion());
            parametro.setString(5, this.getTelefono());
            parametro.setString(6, this.getFecha_nacimiento());
            
            
        
            
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            
            JOptionPane.showMessageDialog(null, Integer.toString(executar) +"registro ingresado","egregar",JOptionPane.INFORMATION_MESSAGE);
            
        }
        catch(Exception ex){
        System.out.println("la cagaste "+ ex.getMessage());}
    }
 
    @Override
    public void actualizar() {
    try {
        // Preparar la consulta SQL para actualizar un empleado existente
        String query = "UPDATE empleados SET codigo = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, id_puesto = ? WHERE id_empleado = ?;";
        cn = new conexion();
        cn.abrir_coxion();
        PreparedStatement parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);

        // Asignar valores a los parámetros de la consulta
        parametro.setString(1, this.getCodigo());
        parametro.setString(2, this.getNombres());
        parametro.setString(3, this.getApellidos());
        parametro.setString(4, this.getDireccion());
        parametro.setString(5, this.getTelefono());
        parametro.setString(6, this.getFecha_nacimiento());

       
        int idPuesto = this.getIdPuesto(); 
        parametro.setInt(7, idPuesto);

       
        parametro.setInt(8, this.getIdEmpleado());

        
        int ejecutar = parametro.executeUpdate();
        cn.cerrar_conexion();

       
        JOptionPane.showMessageDialog(null, ejecutar + " registro(s) actualizado(s)", "Actualizar", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception ex) {
        System.out.println("Error al actualizar: " + ex.getMessage());
    }
    
    
    
    
}
 @Override
    public void eliminar() {
    try {
        // Preparar la consulta SQL para eliminar un empleado
        String query = "DELETE FROM empleados WHERE id_empleado = ?;";
        cn = new conexion();
        cn.abrir_coxion();
        PreparedStatement parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);

        // Asignar el ID del empleado que se va a eliminar
        parametro.setInt(1, this.getIdEmpleado());

        // Ejecutar la eliminación
        int ejecutar = parametro.executeUpdate();
        cn.cerrar_conexion();

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(null, ejecutar + " registro(s) eliminado(s)", "Eliminar", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception ex) {
        System.out.println("Error al eliminar: " + ex.getMessage());
    }
}
 
    
    
    
}
