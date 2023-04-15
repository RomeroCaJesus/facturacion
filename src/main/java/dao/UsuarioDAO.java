/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author jesus
 */
public class UsuarioDAO {

    private static EntityManagerFactory getEntity() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pe.gestor_prueba_war_1.0-SNAPSHOTPU");
            return emf;
        } catch (Exception e) {
            return null;
        }
    }

    public static Usuario validar(String usuario, String clave) {
        try {
            EntityManager em = getEntity().createEntityManager();
            Query q = em.createNamedQuery("Usuario.validar");
            q.setParameter("logiUsua", usuario);
            q.setParameter("passUsua", clave);
            Usuario pers = (Usuario) q.getSingleResult();
            return pers;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean agregar(Usuario persona) {
        try {
            EntityManager em = getEntity().createEntityManager();
            em.getTransaction().begin();
            em.persist(persona);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static Usuario buscar(String codigo) {
        EntityManager em = getEntity().createEntityManager();
        Query q = em.createNamedQuery("Usuario.findByLogiUsua");
        q.setParameter("logiUsua", codigo);
        Usuario usuario = (Usuario) q.getSingleResult();
        return usuario;

    }
    
    public static boolean editar(Usuario usuario) {
        try {
            EntityManager em = getEntity().createEntityManager();
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static List<Usuario> listar() {
        try {
            EntityManager em = getEntity().createEntityManager();
            Query q = em.createNamedQuery("Usuario.findAll");
            List<Usuario> lista = q.getResultList();
            return lista;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) {
//        // Datos de prueba
//        String usuario = "uno";
//        String clave = "be7b7bbb9e5a5066d4346c87c60d7d6d84ada88a570855c2e5d58fc658ef9916";
//
//        // Validar usuario y clave
//        Usuario usuarioValidado = validar(usuario, clave);
//
//        if (usuarioValidado != null) {
//            System.out.println("Bienvenido, " + usuarioValidado.getLogiUsua());
//        } else {
//            System.out.println("Usuario y/o clave incorrectos");
//        }
    }
}
