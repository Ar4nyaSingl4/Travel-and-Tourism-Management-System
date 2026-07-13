package travel.tourism;

public class Package {
    String name, description, price, image;
    int days;

    public Package(String name, String description, String price, String image,int days) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.days = days;
    }
}