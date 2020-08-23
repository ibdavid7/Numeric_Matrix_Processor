package processor;

import java.util.Scanner;

public class Main {

    public static String menu = "1. Add matrices\n" +
            "2. Multiply matrix to a constant\n" +
            "3. Multiply matrices\n" +
            "4. Transpose matrix\n" +
            "5. Calculate a determinant\n" +
            "6. Inverse matrix\n" +
            "0. Exit\n" +
            "Your choice: ";

    public static String menuTranspose = "1. Main diagonal\n" +
            "2. Side diagonal\n" +
            "3. Vertical line\n" +
            "4. Horizontal line\n" +
            "0. Return to main menu\n" +
            "Your choice: ";

    public static void main(String[] args) {
        int choice;

        do {

            do {
                System.out.print(menu);
                Scanner in = new Scanner(System.in);
                choice = in.nextInt();
                in.close();
            } while (choice < 0 && choice > 5);
            switch (choice) {
                case 0:
                    break;
                case 1:
                    Matrix matrix1 = Matrix.instance();
                    Matrix matrix2 = Matrix.instance();
                    System.out.println("The addition result is:");
                    System.out.println(Matrix.add(matrix1, matrix2).toString());
                    break;
                case 2:
                    Matrix matrix3 = Matrix.instance();
                    Scanner in = new Scanner(System.in);
                    Double scalar = Double.parseDouble(in.nextLine());
                    in.close();
                    System.out.println("The multiplication result is:");
                    System.out.println(Matrix.multiply(matrix3, scalar).toString());
                    break;
                case 3:
                    Matrix matrix4 = Matrix.instance();
                    Matrix matrix5 = Matrix.instance();
                    System.out.println("The multiplication result is:");
                    System.out.println(Matrix.multiply(matrix4, matrix5).toString());
                    break;
                case 4:
                    int choice2 = 0;
                    do {
                        System.out.print(menuTranspose);
                        in = new Scanner(System.in);
                        choice2 = in.nextInt();
                        in.close();
                    } while (choice2 < 0 && choice2 > 5);

                    switch (choice2) {
                        case 0:
                            break;
                        case 1:
                            Matrix matrix6 = Matrix.instance();
                            System.out.println("The result is:");
                            System.out.println(Matrix.transposeMainDiagonal(matrix6).toString());
                            break;
                        case 2:
                            Matrix matrix7 = Matrix.instance();
                            System.out.println("The result is:");
                            System.out.println(Matrix.transposeSideDiagonal(matrix7).toString());
                            break;
                        case 3:
                            Matrix matrix8 = Matrix.instance();
                            System.out.println("The result is:");
                            System.out.println(Matrix.transposeVertical(matrix8).toString());
                            break;
                        case 4:
                            Matrix matrix9 = Matrix.instance();
                            System.out.println("The result is:");
                            System.out.println(Matrix.transposeHorizontal(matrix9).toString());
                            break;
                        default:
                            break;
                    }
                    break;
                case 5:
                    Matrix matrix10 = Matrix.instance();
                    System.out.println("The determinant result is:");
                    System.out.println(Matrix.fmt(Matrix.determinant(matrix10)));
                    break;
                case 6:
                    Matrix matrix11 = Matrix.instance();
                    System.out.println("The inverse result is:");
                    System.out.println(Matrix.inverse(matrix11).toString());
                    break;
                default:
                    break;

            }

        }
        while (choice != 0);

    }
}
