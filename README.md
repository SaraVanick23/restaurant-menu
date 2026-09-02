# Restaurant Order System - Java

A simple console-based Java application for managing restaurant orders.

The project was created to practice object-oriented programming, collections, CRUD operations and the Repository pattern using in-memory data storage.

## Technologies

- Java
- IntelliJ IDEA
- Git

## Features

- Displays the restaurant menu
- Creates new orders
- Adds multiple items to an order
- Calculates the total price
- Lists all orders
- Updates order status
- Deletes orders
- Uses an in-memory repository to manage orders
- Handles invalid user input

## Project Structure

- `MenuItem` - Represents an item available on the restaurant menu
- `OrderItem` - Represents a menu item and its quantity in an order
- `Order` - Represents a complete customer order
- `OrderStatus` - Defines the possible order statuses
- `OrderRepository` - Manages CRUD operations using in-memory storage
- `OrderSystem` - Controls the main application logic and user interaction
- `Main` - Starts the application

## CRUD Operations

The application implements CRUD operations using an `ArrayList` as in-memory storage:

- Create - Create and save a new order
- Read - Find and list orders
- Update - Update the status of an order
- Delete - Delete an order

The data is stored only while the application is running and is not persisted in a database.

## How to Run

1. Clone the repository:

```bash
git clone https://github.com/SaraVanick23/restaurant-menu.git