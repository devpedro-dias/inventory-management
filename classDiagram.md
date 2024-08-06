# Class Diagram

```mermaid
   classDiagram
    class User {
        Long id
        String name
        String email
        String password
        String phone
        String address
        String zipCode
    }
    
    class Employee {
        Long id
        String name
        String email
        String password
        String phone
        String address
        String zipCode
        String cpf
    }

    class Customer {
        Long id
        String name
        String email
        String password
        String phone
        String address
        String zipCode
        String cnpj
    }

    class Order {
        Long id
        OrderStatus orderStatus
        LocalDateTime dateTime
        List~Product~ products
        Customer customer
        Employee employee
    }

    class OrderItem {
        Long id
        Order order
        Product product
        int quantity
        double price
    }

    class OrderStatus {
        Completed
        Pending
        Canceled
    }


    class Inventory {
        Long id
        Product product
        int quantity
    }

    class Product {
        Long id
        String name
        double price
        int quantity
        Category category
    }

    class Category {
        Long id
        String name
        List~Product~ products
    }

    class Movement {
        Long id
        MovementType movementType
        Product product
        int quantity
        Date date
    }

    class MovementType {
        IN
        OUT
    }

    User <|-- Employee
    User <|-- Customer

    Customer "1" --> "0..*" Order
    Employee "1" --> "0..*" Order
    Order "1" --> "0..*" OrderItem
    OrderItem "1" --> "1" Product
    Order "1" --> "0..*" Product
    Inventory "1" --> "1" Product 
    Product "1" --> "0..*" Movement 
    Product "1" --> "1..*" Category
    Category "1" --> "0..*" Product 
```
