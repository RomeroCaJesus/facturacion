<%@page import="security.SHA256"%>
<%@page import="dto.Usuario"%>
<%@page import="dao.UsuarioDAO"%>

<%
    String logi = request.getParameter("logi");
    String actu = request.getParameter("actu");
    String nuev = request.getParameter("nuev");
    String conf = request.getParameter("conf");
    
    

    Usuario u = UsuarioDAO.buscar(logi);

    String textoCifrado = SHA256.encrypt(actu);
    if (textoCifrado.equals(u.getPassUsua())  ) {
        if (nuev.equals(conf) ) {
            u.setPassUsua(SHA256.encrypt(nuev));
            boolean b = UsuarioDAO.editar(u);
            if (b == true) {
                out.print("{\"resultado\":\"ok\"}");
            } else {
                out.print("{\"resultado\":\"error\"}");
            }
        } else {
            out.print("{\"resultado\":\"La nueva clave es diferente que la confirmación.\"}");
        }
    } else {
        out.print("{\"resultado\":\"La clave actual no es la correcta.\"}");
    }
%>