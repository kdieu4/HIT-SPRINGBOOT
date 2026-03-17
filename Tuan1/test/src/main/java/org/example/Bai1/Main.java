package org.example.Bai1;

public class Main {
    public static void main(String[] args) {
        Shape s1 = new Circle("Red", 5.0);
        Shape s2 = new Rectangle("Blue", 4.0, 6.0);

        System.out.println("Hình tròn " + s1.getColor() + " có diện tích " + String.format("%.3f", s1.area()));
        System.out.printf("Hình chữ nhật %s có diện tích: %.3f%n", s2.getColor(), s2.area());
    }
}