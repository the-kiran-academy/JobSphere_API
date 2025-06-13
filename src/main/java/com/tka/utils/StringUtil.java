package com.tka.utils;
public class StringUtil {

    // Static method to capitalize the first letter of each word
    public static String capitalizeWords(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String[] words = input.trim().split("\\s+"); // Split by whitespace
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                result.append(Character.toUpperCase(word.charAt(0)))  // First char to uppercase
                      .append(word.substring(1).toLowerCase())       // Rest to lowercase
                      .append(" ");
            }
        }

        return result.toString().trim();
    }
}
