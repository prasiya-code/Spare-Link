package com.spareLink.model;

public class TestSparePart {
    private String name;
    private double price;
    private String image;
    private String description;
    private int brandId;
    private int categoryId;

    public TestSparePart(String name, double price, String image, String description, int brandId, int categoryId) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.brandId = brandId;
        this.categoryId = categoryId;
    }

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getImage() {
		return image;
	}

	public String getDescription() {
		return description;
	}

	public int getBrandId() {
		return brandId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
