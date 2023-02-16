package com.uniovi.sdi;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet(name = "ServletComment", value = "/AddComment")
public class ServletComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // coger sesion
        HttpSession session = request.getSession();

        // coger blog de la sesion y si no hay, crearlo
        LinkedList<Comment> blog = (LinkedList<Comment>) session.getAttribute("blog");

        if (blog == null){
            blog = new LinkedList<Comment>();
            session.setAttribute("blog", blog);
        }
        // crear comentario y guardarlo en el blog
        String user = (String) session.getAttribute("user");
        String comment = request.getParameter("comment");
        Comment item = new Comment(user, comment);
        blog.add(item);

        // establecer los comentarios de la sesion
        session.setAttribute("comentarios", blog);
        getServletContext().getRequestDispatcher("/comment.jsp").forward(request, response);
    }
}
