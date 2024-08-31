Here is an updated version of the problem with detailed descriptions of the entities and additional requirements.

---

### Project Description:

You need to create a Spring Boot project and design an API for a Market where users can manage their carts, wishlists, and order history. This project will involve creating CRUD operations for Users,
Carts, and Wishlists, along with specific additional endpoints. The application should use H2 (in-memory) as the database. You should also write unit tests for the implemented functionalities.

### Entities:

#### 1. **User**

- **id**: A unique identifier for the user.
- **name**: The name of the user.
- **cart**: A one-to-one relationship with a `Cart` entity representing the current shopping cart of the user.
- **wishlist**: A one-to-one relationship with a `Wishlist` entity representing the user's wishlist.
- **orderHistory**: A one-to-many relationship with the `Order` entity representing the user's past orders.

#### 2. **Cart**

- **id**: A unique identifier for the cart.
- **products**: A one-to-many relationship with the `Product` entity representing the list of products in the cart. The fetch type should be eager, and cascading should be applied to persist the
  associated products.
- **user**: A one-to-one relationship with the `User` entity that owns this cart.

#### 3. **Product**

- **id**: A unique identifier for the product.
- **name**: The name of the product.
- **price**: The price of the product.
- **quantity**: The quantity of the product available or added to the cart/wishlist.

#### 4. **Wishlist**

- **id**: A unique identifier for the wishlist.
- **products**: A one-to-many relationship with the `Product` entity representing the list of products in the wishlist. The fetch type should be eager, and cascading should be applied to persist the
  associated products.

#### 5. **Order**

- **id**: A unique identifier for the order.
- **completed**: A boolean flag indicating whether the order has been completed.
- **cart**: A many-to-one relationship with the `Cart` entity representing the cart associated with the order.
- **user**: A many-to-one relationship with the `User` entity representing the user who placed the order.

### Requirements:

1. **CRUD Operations**:
    - Implement CRUD operations for `User`, `Cart`, and `Wishlist`.
    - Users should be able to add and remove products from their cart and wishlist.

2. **Additional Endpoints**:
    - **Get all carts sorted by total value**:
        - Endpoint: `/carts/sorted-by-total`
        - This endpoint should return all carts from all users, sorted by the total value of the products (total = sum of (price * quantity) for each product in the cart).

    - **Get all users sorted by number of orders**:
        - Endpoint: `/users/sorted-by-orders`
        - This endpoint should return all users, sorted by the number of orders they have placed (order history size).

    - **Place an order and mark it as completed**:
        - Endpoint: `/order/place/{id}`
        - This endpoint should place an order based on the provided cart ID, mark the order as completed, and move the cart's content into the user's order history.

### Tips:

- Ensure that you handle edge cases, such as attempting to place an order for an empty cart.
- Utilize H2's in-memory database for rapid development and testing.
- Document your API using tools like Swagger for easier testing and interaction.
