package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;
// business logic that ShoppingCartController plugs into
@Service
public class ShoppingCartService
{
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService)
    {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    public ShoppingCart getByUserId(int userId)
    {
        // instructions: load the user's cart rows, look up each product, and build the ShoppingCart

        // we want data from our DB, so we create a list that calls the repository. (repo connects to DB)
        List<CartItem> cartByUserId = shoppingCartRepository.findByUserId(userId);

        // CartItem's variables are just IDs, so we need to use the 'productId' to fetch the full data
        // we'll use ProductService's getById() and pass in CartItem's getProductId()

        ShoppingCart cart = new ShoppingCart();

        for (CartItem cartItem : cartByUserId)
        {
            Product product = productService.getById(cartItem.getProductId());

            // ShoppingCartItem combines the Product data from ProductService with the quantity from the CartItem row.
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();

            shoppingCartItem.setProduct(product);

            shoppingCartItem.setQuantity(cartItem.getQuantity());

            cart.add(shoppingCartItem);
        }
        return cart;
    }

    // add additional methods here (what methods?)
    // POST
    // PUT
    // DELETE
}
