public class MenuItem {
    private Integer id;
    private String category;
    private String itemName;
    private String description;
    private Float price;
    private Integer prepTime;
    private Integer available;

    public MenuItem(String itemName) {
        this.id = 0;
        this.itemName = itemName;
        this.description = itemName;
        this.category = itemName;
        this.price = 0f;
        this.prepTime = 0;
        this.available = 0;
    }

    public MenuItem(String itemName, String category) {
        this(itemName);
        this.category = category;
    }

    public MenuItem(String itemName, String category, String description) {
        this(itemName, category);
        this.description = description;
    }

    public MenuItem(String itemName, String category, String description, Float price) {
        this(itemName, category, description);
        this.price = price;
    }

    public MenuItem(String itemName, String category, String description, Float price, Integer available) {
        this(itemName, category, description, price);
        this.available = available;
    }

    public MenuItem(String itemName, String category, String description, Float price, Integer available, Integer prepTime) {
        this(itemName, category, description, price, available);
        this.prepTime = prepTime;
    }

    static public MenuItem fromRow(String row) {
        String[] columns = row.split("\t");
        System.out.printf("Menu => '%s'%n", String.join("', '", columns));

        return switch (columns.length) {
            case 2 -> new MenuItem(columns[1], columns[0]);
            case 3 -> new MenuItem(columns[1], columns[0], columns[2]);
            case 4 -> new MenuItem(columns[1], columns[0], columns[2], Float.parseFloat(columns[3]));
            case 5 ->
                    new MenuItem(columns[1], columns[0], columns[2], Float.parseFloat(columns[3]), 0, Integer.parseInt(columns[4]));
            case 6 ->
                    new MenuItem(columns[1], columns[0], columns[2], Float.parseFloat(columns[3]), Integer.parseInt(columns[5]), Integer.parseInt(columns[4]));
            default -> new MenuItem("default");
        };
    }

    public Integer getAvailable() {
        return this.available;
    }

    public String getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getId() {
        return this.id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public Integer getPrepTime() {
        return this.prepTime;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setAvailable(Integer value) {
        this.available = value;
    }

    public void setCategory(String value) {
        this.category = value;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public void setItemName(String value) {
        this.itemName = value;
    }

    public void setPrepTime(Integer value) {
        this.prepTime = value;
    }

    public void setPrice(Float value) {
        this.price = value;
    }
}
