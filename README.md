# Debugging Capstone - Video Game Store API

## Project Context
This is a backend API project built for an online video game store application. The frontend 
website starter code is already fully functional. The objective is to step in as the backend 
developer, and debug the API tests that were failing and build out certain missing features.

The API is built with Java Spring Boot, and uses MySQL for the database. The API endpoint testing was done 
through Insomnia.

## Frontend Capabilities
The storefront connects to the API to power everything users can do on the page. From the 
website, users can browse products by category, search and filter the product list, create an 
account, log in, add items to their shopping cart, and check out to place an order.

## Frontend Capabilities 

# Project Phases

The tasks for this project are structured in 4 phases.

| Phase | Focus |
|-------|-------|
| Phase 1 | Categories Controller |
| Phase 2 | Debugging Endpoints |
| Phase 3 | Implementing Shopping Cart |
| Phase 4 | Implementing User Profile |

Each phase came with a pre-built Insomnia test folder. The tests defined exactly what each endpoint needed to return. To mark each phase as complete, we must pass all tests in that phases folder. 

# Phase 1 — Categories Controller
The `CategoriesController` existed in the starter code but every method was empty. I implemented all 6 REST endpoints and the corresponding service layer logic from scratch.
## Starter Code
<img width="600" alt="starter-categoriescontroller" src="https://github.com/user-attachments/assets/faf6df38-a4aa-4956-822b-b5ca57342347" />

## Implemented Code
I wired up all 6 endpoints, set up the restrictions so only admins can create, update, or delete categories, and connected the controller to the service layer.
<br>

<img width="400" alt="phase1-categories controller1" src="https://github.com/user-attachments/assets/d662269b-b7fc-4ae3-a900-a7aad267fb02" /> 

<img width="400" alt="phase1-categories controller2" src="https://github.com/user-attachments/assets/bc1e340e-3e0c-4dd6-aec3-44a93d3feb1a" />


# Phase 2 - Debugging Endpoints
In this phase, there were 2 bugs that we had to troubleshoot. 

#### Bug 1 — Incorrect Search Results

No matter what filters you passed to `GET /products`, only featured products came back. Even if you applied no filters, the products database would still filter out non-featured products.

**Root cause:** There was a hardcoded `.filter(Product::isFeatured)` in `ProductService.search()` that shouldn't be in the business logic. We simply delete the line of code to fix the first bug.
<br>
<img width="1000"  alt="ProductService search method" src="https://github.com/user-attachments/assets/7a4544ed-3595-47fe-b3f6-114bdfc95980" />
<img width="1000"  alt="isFeatured filter" src="https://github.com/user-attachments/assets/d020824a-872e-48a9-9ef3-d43ac129f6ce" />

#### Bug 2 — Product Updates Don't Fully Save
When you update a product, the request would return `200 OK` but if you fetched
that product again, the `stock` field would still show the original value. The update
appeared to work but the stock change was never actually saved to the database.

**Root cause:** In `ProductService.update()`, `setStock()` was missing from the
list of fields being transferred. Adding `existing.setStock(product.getStock());` fixed the bug.

## All Tests For Phase 2 Pass
<img width="2592" height="1024" alt="2 11 test pass" src="https://github.com/user-attachments/assets/d18e80b3-a71d-4f38-95f4-02da7d5817af" />

# Phase 3 — Implementing Shopping Cart
In this phase, the `ShoppingCartController` and `ShoppingCartService` existed in the
starter code but had no implementation. The shopping cart feature needed to be built from scratch
across all three layers Repository, Service, and Controller.

## Starter Code

## Implemented Code
I wired up all 4 endpoints, set up security so only logged-in users can access the cart,
and connected the controller through the service down to the repository layer.

## All Tests For Phase 3 Pass
<img width="2557" height="1380" alt="phase 3 all tests pass!" src="https://github.com/user-attachments/assets/48964ed2-6bf9-4aad-8b5d-5cec14b795f6" />

# Phase 4 — Implementing User Profile
In this phase, there was no controller at all. I built the `ProfileController` from scratch
with 2 endpoints that allow a logged-in user to view and update their own profile.

## Starter Code


## Implemented Code
I wired up the GET and PUT endpoints, locked the entire controller down to
authenticated users only, and connected it to the existing `ProfileService` following
the same pattern used in Phase 3.

## All Tests For Phase 4 Pass
<img width="2543" height="1280" alt="phase 4 all tests pass" src="https://github.com/user-attachments/assets/55e31d3d-cb33-421c-afbb-a09ee64de32f" />


