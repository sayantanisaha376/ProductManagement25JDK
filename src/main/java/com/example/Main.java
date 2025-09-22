import com.example.entity.Product;
import com.example.service.impl.ProductService;

void main() {
    var service = new ProductService();
    int choice;

    do {
        IO.println("\n===== Product Management Menu =====");
        IO.println("1. Add Product");
        IO.println("2. View Product by ID");
        IO.println("3. Update Product");
        IO.println("4. Delete Product");
        IO.println("5. Display All Products");
        IO.println("6. Sort by Name");
        IO.println("7. Sort by Price");
        IO.println("0. Exit");
        IO.print("Enter choice: ");
        choice = Integer.parseInt(IO.readln());

        try {
            switch (choice) {
                case 1 -> {
                    IO.print("Enter Product ID: ");
                    int id = Integer.parseInt(IO.readln());
                    IO.print("Enter Product Name: ");
                    String name = IO.readln();
                    IO.print("Enter Price: ");
                    double price = Double.parseDouble(IO.readln());
                    service.addProduct(new Product(id, name, price));
                    IO.println("Product added successfully.");
                }
                case 2 -> {
                    IO.print("Enter Product ID to view: ");
                    int vid = Integer.parseInt(IO.readln());
                    IO.println(service.getProduct(vid).toString());
                }
                case 3 -> {
                    IO.print("Enter Product ID to update: ");
                    int uid = Integer.parseInt(IO.readln());
                    IO.print("Enter new Name: ");
                    String newName = IO.readln();
                    IO.print("Enter new Price: ");
                    double newPrice = Double.parseDouble(IO.readln());
                    service.updateProduct(uid, newName, newPrice);
                    IO.println("Product updated.");
                }
                case 4 -> {
                    IO.print("Enter Product ID to delete: ");
                    int did = Integer.parseInt(IO.readln());
                    service.deleteProduct(did);
                    IO.println("Product deleted.");
                }
                case 5 -> {
                    IO.println("--- All Products ---");
                    service.displayAll();
                }
                case 6 -> {
                    IO.println("--- Products Sorted by Name ---");
                    service.sortByName();
                }
                case 7 -> {
                    IO.println("--- Products Sorted by Price ---");
                    service.sortByPrice();
                }
                case 0 -> IO.println("Exiting.");
                default -> IO.println("Invalid choice.");
            }
        } catch (Exception ex) {
            IO.println("Error: " + ex.getMessage());
        }
    } while (choice != 0);
}