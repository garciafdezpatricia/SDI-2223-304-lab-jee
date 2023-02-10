package com.uniovi.sdi;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "ServletProducts", value = "/products")
public class ServletProducts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retornar los productos de la base de datos
        List<Product> storeProducts = getProducts();
        // guardar la lista de productos
        request.setAttribute("storeProducts", storeProducts);
        // retornar la vista con el parametro storeProducts
        getServletContext().getRequestDispatcher("/products-view.jsp").forward(request, response);
    }

    // retorna los productos de la base de datos
    private List<Product> getProducts(){
        List<Product> products = new LinkedList<Product>();
        ObjectContainer db = null;
        try {
            db = Db4oEmbedded.openFile("bdProducts");
            List<Product> response = db.queryByExample(Product.class);
            // NO RETORNAR LA MISMA LISTA DE LA RESPUESTA
            products.addAll(response);
        } finally {
            db.close();
        }
        return products;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
