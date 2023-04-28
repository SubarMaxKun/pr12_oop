package com.shevliakov;

import com.github.javafaker.Faker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FakeData {

    public void fakeData() {
        String url = "jdbc:sqlite:C:/Users/subar/Desktop/pr12/src/main/resources/traders";

        try (Connection conn = DriverManager.getConnection(url)) {
            Faker faker = new Faker();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO trader_account (name, age, country, balance, orders, orders_value) VALUES (?, ?, ?, ?, ?, ?)");

            for (int i = 0; i < 50; i++) {
                stmt.setString(1, faker.name().fullName());
                stmt.setInt(2, faker.number().numberBetween(18, 99));
                stmt.setString(3, faker.country().name());
                stmt.setString(4, faker.number().digits(6));
                stmt.setInt(5, faker.number().randomDigit());
                stmt.setString(6, faker.number().digits(6));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
