/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.conMySQL;
import dto.Cliente;
import dto.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jesus
 */
public class ClienteDAO {

    public static boolean insert(Cliente objObjeto) throws Exception {
        Connection cn = conMySQL.getInstance().getConnection();
        String sql = "CALL sp_insert_cliente(?,?,?,?,?,?,?,?,?);";
        CallableStatement cs;
        try {
            cs = cn.prepareCall(sql);
            cs.setInt(1, objObjeto.getCodiClie());
            cs.setString(2, objObjeto.getNumeDocu());
            cs.setString(3, objObjeto.getAppaClie());
            cs.setString(4, objObjeto.getApmaClie());
            cs.setString(5, objObjeto.getNombClie());
            cs.setString(6, objObjeto.getRaznSociClie());
            cs.setString(7, objObjeto.getNumeCeluClie());
            cs.setString(8, objObjeto.getMailClie());
            cs.setString(9, objObjeto.getSexoClie());
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean update(Cliente objObjeto) throws Exception {
        Connection cn = conMySQL.getInstance().getConnection();
        String sql = "CALL sp_update_cliente(?,?,?,?,?,?,?,?,?);";
        CallableStatement cs;
        try {
            cs = cn.prepareCall(sql);
            cs.setInt(1, objObjeto.getCodiClie());
            cs.setString(2, objObjeto.getNumeDocu());
            cs.setString(3, objObjeto.getAppaClie());
            cs.setString(4, objObjeto.getApmaClie());
            cs.setString(5, objObjeto.getNombClie());
            cs.setString(6, objObjeto.getRaznSociClie());
            cs.setString(7, objObjeto.getNumeCeluClie());
            cs.setString(8, objObjeto.getMailClie());
            cs.setString(9, objObjeto.getSexoClie());
            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

//    public static boolean cambiarClaveUsua(Usuario objObjeto) throws Exception {
//        Connection cn = conMySQL.getInstance().getConnection();
//        String sql = "CALL sp_cambiar_clave(?,?,?,?);";
//        CallableStatement cs;
//        try {
//            cs = cn.prepareCall(sql);
//            cs.setString(1, objObjeto.getCodiUsua());
//            cs.setString(2, objObjeto.getLogiUsua());
//            cs.setString(3, objObjeto.getPassUsua());
//            cs.setInt(4, objObjeto.getTipoUsua());
//
//            cs.execute();
//            return true;
//        } catch (SQLException e) {
//            return false;
//        }
//    }
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
                cliente.setNumeDocu(rs.getString("numeDocu"));
                cliente.setAppaClie(rs.getString("appaClie"));
                cliente.setApmaClie(rs.getString("apmaClie"));
                cliente.setNombClie(rs.getString("nombClie"));
                cliente.setRaznSociClie(rs.getString("raznSociClie"));
                cliente.setNumeCeluClie(rs.getString("numeCeluClie"));
                cliente.setMailClie(rs.getString("mailClie"));
                cliente.setSexoClie(rs.getString("sexoClie"));
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar clientes: " + e.getMessage());
        }
        return listaClientes;
    }

    public static boolean delete(int codi) throws Exception {
        Connection cn = conMySQL.getInstance().getConnection();
        String sql = "CALL sp_delete_cliente(?);";
        CallableStatement cs;
        try {
            cs = cn.prepareCall(sql);
            cs.setInt(1, codi);
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static Cliente buscar(String codigo) throws Exception {
        Connection cn = conMySQL.getInstance().getConnection();
        String codi = "%" + codigo + "%";
        String sql = "select * from cliente where codiClie like ?";
        PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, codi);
        ResultSet rs = ps.executeQuery();

        Cliente cliente = null;
        if (rs.next()) {
            cliente = new Cliente();
            cliente.setCodiClie(rs.getInt("codiClie"));
                cliente.setNumeDocu(rs.getString("numeDocu"));
                cliente.setAppaClie(rs.getString("appaClie"));
                cliente.setApmaClie(rs.getString("apmaClie"));
                cliente.setNombClie(rs.getString("nombClie"));
                cliente.setRaznSociClie(rs.getString("raznSociClie"));
                cliente.setNumeCeluClie(rs.getString("numeCeluClie"));
                cliente.setMailClie(rs.getString("mailClie"));
                cliente.setSexoClie(rs.getString("sexoClie"));
            rs.last();
        }
        return cliente;
    }

    public static void main(String[] args) throws Exception {
//        String logi = "kike";
//        String clave = "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4";
//
//        Usuario usuario = ClienteDAO.validar(logi, clave);
//
//        if (usuario != null) {
//            System.out.println("Usuario válido: " + usuario.getLogiUsua());
//        } else {
//            System.out.println("Usuario no válido");
//        }
//        Cambiar Clave
//        Usuario usuario = new Usuario("111", "dos", "12", 1);
//        System.out.println("Cambiando clave para usuario: " + usuario.getLogiUsua());
//        try {
//            boolean exito = cambiarClaveUsua(usuario);
//            if (exito) {
//                System.out.println("La clave del usuario " + usuario.getLogiUsua() + " ha sido cambiada exitosamente.");
//            } else {
//                System.out.println("Ha ocurrido un error al intentar cambiar la clave del usuario " + usuario.getLogiUsua());
//            }
//        } catch (Exception e) {
//            System.out.println("Ha ocurrido un error al intentar cambiar la clave del usuario " + usuario.getLogiUsua() + ": " + e.getMessage());
//        }

//        Agregar
//        try {
//            Cliente cliente = new Cliente("3", "3", "3", "3");
//            boolean resultado = insert(cliente);
//            if (resultado) {
//                System.out.println("Se ha agregado el cliente correctamente.");
//            } else {
//                System.out.println("No se ha podido agregar el cliente.");
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Buscar
//        try {
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("Ingrese el código a buscar: ");
//            String codigo = scanner.nextLine();
//
//            ClienteDAO clienteDAO = new ClienteDAO();
//            ResultSet rs = clienteDAO.buscar(codigo);
//
//            if (rs.first()) {
//                do {
//                    System.out.println(rs.getString("codiClie") + ", " + rs.getString("appaClie") + ", " + rs.getString("apmaClie") + ", " + rs.getString("nombClie"));
//                } while (rs.next());
//            } else {
//                System.out.println("No se encontraron resultados");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    //    }
//    public static Usuario validar(String logi, String clave) throws Exception {
//        Usuario usuario = null;
//        ResultSet rs;
//        Connection cn = conMySQL.getInstance().getConnection();
//        String sql = "CALL sp_validar_usuario(?, ?);";
//        CallableStatement cs;
//        try {
//            cs = cn.prepareCall(sql);
//            cs.setString(1, logi);
//            cs.setString(2, clave);
//            cs.registerOutParameter(3, java.sql.Types.INTEGER);
//
//            cs.execute();
//
//           int resultado = cs.getInt(3);
//            if (resultado == 1) {
//            // Si el resultado es 1, entonces el usuario y la clave son correctos
//            cs = cn.prepareCall("SELECT * FROM usuario WHERE logiUsua = ?");
//            cs.setString(1, logi);
//
//            rs = cs.executeQuery();
//
//            if (rs.next()) {
//                usuario = new Usuario();
//                usuario.setCodiUsua(rs.getString("CodiUsua"));
//                usuario.setLogiUsua(rs.getString("LogiUsua"));
//                usuario.setPassUsua(rs.getString("passUsua"));
//            }
//           }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return usuario;
//    }
}
