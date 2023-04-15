package SP;

import com.mysql.cj.protocol.Resultset;
import conexion.conMySQL;
import dto.Cliente;
import dto.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class SPClienteDAO {

    public static boolean insert(Cliente objObjeto) throws Exception {
        Connection cn = conMySQL.getInstance().getConnection();
        String sql = "CALL sp_insert_cliente(?,?,?,?)";
        CallableStatement cs;
        try {
            cs = (CallableStatement) cn.prepareCall(sql);
            cs.setInt(1, objObjeto.getCodiClie());
            cs.setString(2, objObjeto.getAppaClie());
            cs.setString(3, objObjeto.getApmaClie());
            cs.setString(4, objObjeto.getNombClie());
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean update(Cliente objObjeto) throws Exception {
        Connection cn = conMySQL.getInstance().getConnection();
        String sql = "CALL sp_update_cliente(?,?,?,?)";
        CallableStatement cs;
        try {
            cs = (CallableStatement) cn.prepareCall(sql);
            cs.setInt(1, objObjeto.getCodiClie());
            cs.setString(2, objObjeto.getAppaClie());
            cs.setString(3, objObjeto.getApmaClie());
            cs.setString(4, objObjeto.getNombClie());
            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public static boolean delete(String codi) throws Exception {
        Connection cn = conMySQL.getInstance().getConnection();
        String sql = "CALL sp_delete_cliente(?);";
        CallableStatement cs;
        try {
            cs = (CallableStatement) cn.prepareCall(sql);
            cs.setString(1, codi);
            cs.execute();
                return true;
            
        } catch (SQLException e) {
            return false;
        }
    }
    
    public static boolean cambiarClaveUsua(Usuario objObjeto) throws Exception{
        Connection cn= conMySQL.getInstance().getConnection();
        String sql = "CALL sp_cambiar_clave(?,?);";
        CallableStatement cs;
        try {
            cs = (CallableStatement) cn.prepareCall(sql);
            cs.setString(1, objObjeto.getLogiUsua());
            cs.setString(2, objObjeto.getPassUsua());
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
     public static List<Cliente> listar() throws Exception {
        Connection cn = conMySQL.getInstance().getConnection();
        String sql = "SELECT * FROM cliente";
        PreparedStatement ps;
        ResultSet rs;
        List<Cliente> listaClientes = new ArrayList<>();
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodiClie(rs.getInt("codiClie"));
                cliente.setAppaClie(rs.getString("appaClie"));
                cliente.setApmaClie(rs.getString("apmaClie"));
                cliente.setNombClie(rs.getString("nombClie"));
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar clientes: " + e.getMessage());
        }
        return listaClientes;
    }
    
    public static Cliente buscar(String codigo) throws Exception{
        Connection cn = conMySQL.getInstance().getConnection();
        String codi = "%"+ codigo + "%";
        String sql = "select * from cliente where codiClie like ?";
        PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, codi);
        ResultSet rs = ps.executeQuery();
        
        Cliente cliente = null;
        if (rs.next()) {
            cliente = new Cliente();
            cliente.setCodiClie(rs.getInt("codiClie"));
            cliente.setAppaClie(rs.getString("appaClie"));
            cliente.setApmaClie(rs.getString("apmaClie"));
            cliente.setNombClie(rs.getString("nombClie"));
        }
        return cliente;
    }
    
    public static Usuario validar(String logi, String clave) throws Exception{
        Usuario usuario = null;
        ResultSet rs;
        Connection cn = conMySQL.getInstance().getConnection();
        String sql = "call sp_validar_usuario(?,?)";
        CallableStatement cs;
        try {
            cs = cn.prepareCall(sql);
            cs.setString(1, logi);
            cs.setString(2, clave);
            
            cs.execute();
            
            cs = cn.prepareCall("SELECT * FROM usuario WHERE logiUsua = ?");
            cs.setString(1, logi);
            
            rs = cs.executeQuery();
            
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setCodiUsua(rs.getInt("CodiUsua"));
                usuario.setLogiUsua(rs.getString("LogiUsua"));
                usuario.setPassUsua(rs.getString("passUsua"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
