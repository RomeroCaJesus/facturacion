<%@page import="dao.ClienteDAO"%>
<%@page import="dto.Usuario"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="security.MD5"%>
<%@page import="security.SHA256"%>

<%
    String logi = request.getParameter("logi");
    String pass = request.getParameter("pass");

    //String textoCifrado = MD5.getMD5(pass);
    String textoCifrado = SHA256.encrypt(pass);

    Usuario u=UsuarioDAO.validar(logi, textoCifrado);
    if(u == null){
        out.print("{\"resultado\":\"error\"}");
    }
    else{
        out.print("{\"resultado\":\"ok\"}");
    }

%>