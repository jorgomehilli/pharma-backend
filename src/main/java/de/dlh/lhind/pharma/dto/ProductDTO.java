package de.dlh.lhind.pharma.dto;

public class ProductDTO {

    public String name;
    public Integer price;
    public Integer quantity;
    public String imgPath;

    public ProductDTO(String name, Integer price, Integer quantity, String imgPath) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
