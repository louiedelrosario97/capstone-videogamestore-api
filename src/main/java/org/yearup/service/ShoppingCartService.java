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
//---------------------------------------- [ GET getByUserId() ] --------------------------------------------------

    // instructions: load the user's cart rows, look up each product, and build the ShoppingCart

    // notes:
    // we want data from our DB, so we create a list that calls the repository. (repo connects to DB)
    // CartItem's variables are just IDs, so we need to use the 'productId' to fetch the full data
    // we'll use ProductService's getById() and pass in CartItem's getProductId()
    // ShoppingCartItem combines the Product data from ProductService with the quantity from the CartItem row.

    public ShoppingCart getByUserId(int userId)
    {
        List<CartItem> usersCart = shoppingCartRepository.findByUserId(userId);

        ShoppingCart cart = new ShoppingCart();

        for (CartItem cartItem : usersCart)
        {
            Product product = productService.getById(cartItem.getProductId());

            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();

            shoppingCartItem.setProduct(product);
            shoppingCartItem.setQuantity(cartItem.getQuantity());

            cart.add(shoppingCartItem);
        }
        return cart;
    }
// ----------------------------------- [ POST - addProductToCart() ] ---------------------------------------------------

    // instructions: if the product we are adding is not yet in the cart, we insert the product with a quantity of 1
    //               if the product already exists in the cart, increase the quantity by 1

    // notes: we need to check the DB to see if the product we are adding already exists in the users cart
    // for both outcomes, we should create an if-else block.
    // to see the DB, we should call the ShoppingCartRepo. (the repo has a method called findByUserIdAndProductId(), perfect.)
    // findByUserIdAndProductId() passes userId and productId (CartItem's data members)
    //
    // - [if] cartItem equals 'null' then we add the cartItem's data, set the quantity to 1, and .save to the repo
    // - [else] increase the cartItem quantity by 1. .setQuantity then .save

    public ShoppingCart addProductToCart(int userId, int productId)
    {
        CartItem existing = shoppingCartRepository.findByUserIdAndProductId(userId, productId);

        if  (existing == null)
        {
            CartItem newItem = new CartItem();
            newItem.setUserId(userId);
            newItem.setProductId(productId);
            newItem.setQuantity(1);
            shoppingCartRepository.save(newItem);
        }
        else
        {
            existing.setQuantity(existing.getQuantity() + 1);
            shoppingCartRepository.save(existing);
        }
        return getByUserId(userId); // returns same method signature + re-queries the DB
    }

// ----------------------------------- [ DELETE - clearCart() ] -------------------------------------------------------

    public void clearCart(int userId)
    {

    }

// ----------------------------------- [ PUT - updateCart() ] --------------------------------------------------------

    public ShoppingCart updateProduct(int userId, int productId, int quantity)
    {

    }
}
