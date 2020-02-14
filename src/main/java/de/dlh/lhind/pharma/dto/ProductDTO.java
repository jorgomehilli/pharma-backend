package de.dlh.lhind.pharma.dto;

public class ProductDTO {

    public String name;
    public Integer price;
    public String imgPath;

    public ProductDTO(String name, Integer price, String imgPath) {
        this.name = name;
        this.price = price;
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
