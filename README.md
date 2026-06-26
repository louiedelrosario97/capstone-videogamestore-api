# Debugging Capstone - Video Game Store API

## Project Context
This is a backend API project built for an online video game store application. The bulk of the 

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
<img width="400" alt="phase1-categories controller1" src="https://github.com/user-attachments/assets/d662269b-b7fc-4ae3-a900-a7aad267fb02" /> 

<img width="400" alt="phase1-categories controller2" src="https://github.com/user-attachments/assets/bc1e340e-3e0c-4dd6-aec3-44a93d3feb1a" />


# Phase 2 - Debugging Endpoints
In this phase, there were 2 bugs that we had to troubleshoot. 

#### Bug 1 — Incorrect Search Results

No matter what filters you passed to `GET /products`, only featured products came back. Even if you applied no filters, the products database would still filter out non-featured products.

**Root cause:** There was a hardcoded `.filter(Product::isFeatured)` in `ProductService.search()` that shouldn't be in the business logic. We simply delete the line of code to fix the first bug.
<img width="2552" height="1435" alt="ProductService search method" src="https://github.com/user-attachments/assets/7a4544ed-3595-47fe-b3f6-114bdfc95980" />
<img width="1507" height="484" alt="isFeatured filter" src="https://github.com/user-attachments/assets/d020824a-872e-48a9-9ef3-d43ac129f6ce" />

## Phase 3 - Shopping Cart 
## Phase 4 - User Profile
