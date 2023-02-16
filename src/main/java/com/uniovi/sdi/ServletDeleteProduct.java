package com.uniovi.sdi;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "ServletDeleteProduct", value = "/DeleteProduct")
public class ServletDeleteProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // obtener la sesion del request
        HttpSession session = request.getSession();
        // de la sesion obtener el carrito y hacer un cast a hashmap
        HashMap<String, Integer> cart =
                (HashMap<String, Integer>) request.getSession().getAttribute("cart");

        // No hay carrito, creamos uno y lo insertamos en sesión
        if (cart == null) {
            // crear el carrito
            cart = new HashMap<String, Integer>();
            // establecer el valor del nuevo carrito al atributo cart de la sesion
            request.getSession().setAttribute("cart", cart);
        }
        // sacar el nombre del producto de la request
        String product = request.getParameter("product");
        // si el producto no es nulo, borrarlo del carrito
        if (product != null) {
            deleteFromShoppingCart(cart, product);
        }
        // retornar el carrito modificado
        request.setAttribute("selectedItems", cart);
        getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    private void deleteFromShoppingCart(HashMap<String, Integer> cart, String productKey){
        // si hay mas de un producto decrementar las unidades, si no eliminarlo del carrito
        if (cart.get(productKey) > 1){
            int units = cart.get(productKey);
            cart.put(productKey, Integer.valueOf(units - 1));
        }
        else{
            cart.remove(productKey);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
