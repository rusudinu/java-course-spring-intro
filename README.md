• create a Spring Boot project and design (code) an API for a Market (users have a cart, and add and remove products, each user has an id, and also a Wishlist, no need to implement a login system;

create all CRUD operations for Users, Cart and Wishlist, use H2 (in-memory) as a database)
• create unit tests for it

• please make sure to implement all those things, and meet the deadline

• comments would be a nice addition

Please make sure to have the extra endpoints:
• an endpoint where i can get all the carts from all the users, sorted by the total of the products (a product must have a name, price and a quantity)

• an endpoint where i can get all the users sorted by their number of orders (users will also have order history, make sure to implement it)

• create an endpoint that will place an order and mark it as completed using its id (.../order/place/{id}), this will also put the order in the order history for its client
