/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chloemarshall
 */
public class FormularioCookie extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 

        Cookie cookie = null;
        Cookie [] cookies = null;
        
        cookies = request.getCookies();
        
        String nombre = request.getParameter("nombre");
        if((nombre == null || nombre == (""))){
            nombre = "Desconocido";
        }
        
        String vcolor = request.getParameter("colorDiv");
        if(vcolor==null){
            vcolor="red";
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForumlarioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<p>");
            if(cookies != null){
                for(int i = 0; i < cookies.length; i++){
                    cookie = cookies[i];
                    out.print(cookie.getName()+": ");
                    out.println(cookie.getValue()+"</br>");
                }
            }
            out.println("Nuevo Valor Cookies:</br>");
            out.println("<strong>Nombre:</strong> "+nombre+" <strong>Color:</strong> "+vcolor+"");
            out.println("</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        Cookie nameCookie = new Cookie("nombre", request.getParameter("nombre"));
        Cookie colorCookie = new Cookie("colorDiv", request.getParameter("colorDiv"));
        
        nameCookie.setMaxAge(60*60);
        colorCookie.setMaxAge(60*60);
        
        response.addCookie(nameCookie);
        response.addCookie(colorCookie);
        
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
