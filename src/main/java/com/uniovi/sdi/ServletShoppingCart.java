package com.uniovi.sdi;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "ServletShoppingCart", value = "/AddToShoppingCart")
public class ServletShoppingCart extends HttpServlet {

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
        // si el producto no es nulo, añadirlo al carrito
        if (product != null) {
            addToShoppingCart(cart, product);
        }
        // Retornar la vista con parámetro "selectedItems"
        request.setAttribute("selectedItems", cart);
        getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    private void addToShoppingCart(HashMap<String, Integer> cart, String productKey){
        if (cart.get(productKey) == null)
            cart.put(productKey, Integer.valueOf(1));
        else{
            int productCount = (Integer) cart.get(productKey).intValue();
            cart.put(productKey, Integer.valueOf(productCount + 1));
        }
    }

    private String shoppingCartToHtml(HashMap<String,Integer> cart){
        String shoppingCartToHtml = "";

        for (String key : cart.keySet()){
            shoppingCartToHtml += "<p>[" + key + "]," + cart.get(key) + " unidades</p>";
        }
        return shoppingCartToHtml;
    }


}
