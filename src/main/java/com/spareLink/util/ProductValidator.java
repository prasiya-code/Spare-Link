package com.spareLink.util;

public class ProductValidator {

    // Validation method for spare part attributes
    public static String validate(String name, double price, int quantity, String description) {

        // Check if name is empty
        if (name == null || name.trim().isEmpty()) {
            return "Part name is required.";
        }

        // Check if price is valid (greater than 1500)
        if (price <= 1500) {
            return "Price must be greater than 1500.";
        }

        // Check if quantity is valid (non-negative)
        if (quantity < 0) {
            return "Quantity cannot be negative.";
        }

        // Check if description length is sufficient (more than 20 characters)
        if (description == null || description.trim().length() <= 10) {
            return "Description must be more than 20 characters.";
        }

        // If all checks pass, return null (no error)
        return null;
    }
}
