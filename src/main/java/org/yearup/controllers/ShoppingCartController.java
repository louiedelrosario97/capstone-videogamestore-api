package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;
import org.yearup.service.ShoppingCartService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("cart")
@CrossOrigin
@PreAuthorize("isAuthenticated()")                          // only logged-in users should have access to these actions
public class ShoppingCartController
{

    private ShoppingCartService shoppingCartService;        // notes: The controller depends on the service layer.
    private UserService userService;                        // 'User' objects are required for cart functionality.

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService)
    {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

// ----------------------------------- [ @GetMapping getCart() ] ------------------------------------------------------

    @GetMapping
    public ShoppingCart getCart(Principal principal)        // each method in this controller requires a Principal object as a parameter
    {
        // get logged-in user
        String username = principal.getName();
        // find database user by username
        User user = userService.getByUserName(username);
        int userId = user.getId();

        // calls the service to retrieve the cart
        return shoppingCartService.getByUserId(userId);
    }
// ----------------------------------- [ @PostMapping addProductToCart() ] ---------------------------------------------
    // instructions: add a POST method to add a product to the cart
    // the url should be https://localhost:8080/cart/products/15
    // return the updated cart, with a 201 status code created

    @PostMapping("products/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingCart addProductToCart(Principal principal, @PathVariable int productId)
    {
        String username = principal.getName();
        User user = userService.getByUserName(username);
        int userId = user.getId();

        return shoppingCartService.addProductToCart(userId, productId);
    }

// ----------------------------------- [ @PutMapping updateProduct() ] ------------------------------------------------------
    // instructions: add a PUT method to update an existing product in the cart - the url should be
    // https://localhost:8080/cart/products/15  (15 is the productId to be updated)

    @PutMapping("products/{productId}")
    public ShoppingCart updateProduct(Principal principal, @PathVariable int productId, @RequestBody ShoppingCartItem item)
    {
        String username = principal.getName();
        User user = userService.getByUserName(username);
        int userId = user.getId();

        // Pull the new quantity out of the request body and pass it to the service.
        return shoppingCartService.updateItemQuantity(userId, productId, item.getQuantity());
    }

// ----------------------------------- [ @DeleteMapping clearCart() ] ------------------------------------------------------
    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated; return the cart (200 OK)
    // add a DELETE method to clear all products from the current users cart
    // https://localhost:8080/cart  - return the (now empty) cart so the front end can refresh it (200 OK)

    @DeleteMapping
    public ShoppingCart clearCart(Principal principal)
    {
        String username = principal.getName();
        User user = userService.getByUserName(username);
        int userId = user.getId();

        shoppingCartService.clearCart(userId);
        return shoppingCartService.getByUserId(userId);
    }

}
