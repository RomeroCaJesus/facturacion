<%@page import="java.io.IOException"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="dao.ClienteDAO"%>
<%@page import="java.util.List"%>
<%@page import="dto.Cliente"%>

<%
    String op = request.getParameter("op");
    Gson g = new Gson();
    switch (op) {
        case "1":
            List<Cliente> lista = ClienteDAO.listar();
            String resultado = g.toJson(lista);
            resultado = "{\"data\":" + resultado + "}";
            out.print(resultado);
            break;
        case "2":
            int codi = Integer.parseInt(request.getParameter("codi"));
            String numedocu = request.getParameter("numedocu");
            String paterno = request.getParameter("paterno");
            String materno = request.getParameter("materno");
            String nombre = request.getParameter("nombre");
            String razon = request.getParameter("razon");
            String celular = request.getParameter("celular");
            String mail = request.getParameter("mail");
            String sexo = request.getParameter("sexo");

            Cliente c = new Cliente(codi, numedocu, paterno, materno, nombre, razon, celular, mail, sexo);
            boolean b = ClienteDAO.insert(c);
            if (b) {
                out.print("{\"resultado\":\"ok\"}");
            } else {
                out.print("{\"resultado\":\"error\"}");
            }

            break;
        case "3":
            int Newcodi = Integer.parseInt(request.getParameter("codi"));
            String Newnumedocu = request.getParameter("numedocu");
            String Newpaterno = request.getParameter("paterno");
            String Newmaterno = request.getParameter("materno");
            String Newnombre = request.getParameter("nombre");
            String Newrazon = request.getParameter("razon");
            String Newcelular = request.getParameter("celular");
            String Newmail = request.getParameter("mail");
            String Newsexo = request.getParameter("sexo");
            Cliente cliente = new Cliente(Newcodi, Newnumedocu, Newpaterno, Newmaterno, Newnombre, Newrazon, Newcelular, Newmail, Newsexo);
            boolean bNew = ClienteDAO.update(cliente);
            if (bNew) {
                out.print("{\"resultado\":\"ok\"}");
            } else {
                out.print("{\"resultado\":\"error\"}");
            }
            break;
        case "4":
            codi = Integer.parseInt(request.getParameter("codi"));
            ClienteDAO.delete(codi);
            //ClienteDAO.eliminar(codi);
            out.print("{\"resultado\":\"ok\"}");
            break;
        case "5":
            String codigo = request.getParameter("codi");
            Cliente p = ClienteDAO.buscar(codigo);
            if (p != null) {
                out.print(g.toJson(p));
            } else {
                out.print("{'resultado':'error'}");
            }
            break;
    }
%>
