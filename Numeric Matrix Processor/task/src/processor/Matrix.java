package processor;

import java.util.Scanner;

public class Matrix {
    Double[][] matrix;

    public Matrix(Double[][] matrix) {
        this.matrix = matrix;
    }

    public static Matrix instance() {
        Scanner in = new Scanner(System.in);
        int rows;
        int cols;
        do {
            System.out.print("Enter size of the matrix: ");
            String[] s = in.nextLine().split("\\s+");
            rows = Integer.parseInt(s[0]);
            cols = Integer.parseInt(s[1]);
        } while (rows < 0 && cols < 0);

        Double[][] matrix = new Double[rows][cols];

        do {
            System.out.println("Enter the matrix:");
            for (int i = 0; i < rows; i++) {
                String[] line = in.nextLine().split("\\s+");
                Double[] matrixRow = new Double[cols];
                for (int j = 0; j < cols; j++) {
                    matrixRow[j] = Double.parseDouble(line[j]);
                }
                matrix[i] = matrixRow;
            }
        } while (!isPopulated(new Matrix(matrix)));

        in.close();

        return new Matrix(matrix);
    }

    public static Matrix multiply(Matrix matrix, Double scalar) {
        if (matrix.matrix.length == 0) {
            System.out.println("Zero length matrix");
            return null;
        } else {
            Matrix resultMatrix = new Matrix(new Double[matrix.matrix.length][matrix.matrix[0].length]);
            for (int i = 0; i < matrix.matrix.length; i++) {
                for (int j = 0; j < matrix.matrix[i].length; j++) {
                    resultMatrix.matrix[i][j] = matrix.matrix[i][j] * scalar;
                }
            }
            return resultMatrix;
        }
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrix.length == 0 || matrix2.matrix.length == 0) {
            System.out.println("Zero length matrix");
            return null;
        } else if (matrix1.matrix.length != matrix2.matrix.length ||
                matrix1.matrix[0].length != matrix2.matrix[0].length) {
            System.out.println("ERROR");
            return null;
        } else {
            Double[][] matrix = new Double[matrix1.matrix.length][matrix1.matrix[0].length];
            for (int i = 0; i < matrix1.matrix.length; i++) {
                for (int j = 0; j < matrix1.matrix[i].length; j++) {
                    matrix[i][j] = matrix1.matrix[i][j] + matrix2.matrix[i][j];
                }
            }
            return new Matrix(matrix);
        }
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrix.length == 0 || matrix2.matrix.length == 0) {
            System.out.println("Zero length matrix");
            return null;
        } else if (matrix1.matrix[0].length != matrix2.matrix.length) {
            System.out.println("Incompatible input matrices, multiplication not possible");
            return null;
        } else {

            Double[][] matrix = new Double[matrix1.matrix.length][matrix2.matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    Double sum = 0.0;
                    for (int k = 0; k < matrix1.matrix[0].length; k++) {
                        sum += matrix1.matrix[i][k] * matrix2.matrix[k][j];
                    }
                    matrix[i][j] = sum;
                }
            }
            return new Matrix(matrix);
        }
    }

    public static Matrix transposeMainDiagonal(Matrix matrix) {
        if (matrix == null || matrix.matrix.length == 0 || matrix.matrix[0].length == 0) {
            return null;
        } else if (matrix.matrix.length == 1 && matrix.matrix[0].length == 1) {
            return matrix;
        } else {
            int origRows = matrix.matrix.length;
            int origCols = matrix.matrix[0].length;
            Double[][] matrixTransposed = new Double[origCols][origRows];
            for (int i = 0; i < origCols; i++) {
                for (int j = 0; j < origRows; j++) {
                    matrixTransposed[j][i] = matrix.matrix[i][j];
                }
            }
            return new Matrix(matrixTransposed);
        }
    }

    public static Matrix transposeSideDiagonal(Matrix matrix) {
        if (matrix == null || matrix.matrix.length == 0 || matrix.matrix[0].length == 0) {
            return null;
        } else if (matrix.matrix.length == 1 && matrix.matrix[0].length == 1) {
            return matrix;
        } else {
            int origRows = matrix.matrix.length;
            int origCols = matrix.matrix[0].length;
            Double[][] matrixTransposed = new Double[origCols][origRows];
            for (int i = 0; i < origCols; i++) {
                for (int j = 0; j < origRows; j++) {
                    matrixTransposed[i][j] = matrix.matrix[origCols - 1 - j][origCols - 1 - i];
                }
            }
            return new Matrix(matrixTransposed);
        }
    }

    public static Matrix transposeVertical(Matrix matrix) {
        if (matrix == null || matrix.matrix.length == 0 || matrix.matrix[0].length == 0) {
            return null;
        } else if (matrix.matrix.length == 1 && matrix.matrix[0].length == 1) {
            return matrix;
        } else {
            int origRows = matrix.matrix.length;
            int origCols = matrix.matrix[0].length;
            Double[][] matrixTransposed = new Double[origRows][origCols];
            for (int i = 0; i < origRows; i++) {
                for (int j = 0; j < origCols; j++) {
                    matrixTransposed[i][j] = matrix.matrix[i][origCols - 1 - j];
                }
            }
            return new Matrix(matrixTransposed);
        }
    }

    public static Matrix transposeHorizontal(Matrix matrix) {
        if (matrix == null || matrix.matrix.length == 0 || matrix.matrix[0].length == 0) {
            return null;
        } else if (matrix.matrix.length == 1 && matrix.matrix[0].length == 1) {
            return matrix;
        } else {
            int origRows = matrix.matrix.length;
            int origCols = matrix.matrix[0].length;
            Double[][] matrixTransposed = new Double[origRows][origCols];
            for (int i = 0; i < origRows; i++) {
                for (int j = 0; j < origCols; j++) {
                    matrixTransposed[i][j] = matrix.matrix[origRows - 1 - i][j];
                }
            }
            return new Matrix(matrixTransposed);
        }
    }

    public static double determinant(Matrix matrix) {
        try {
            if (matrix == null || matrix.matrix.length == 0 || matrix.matrix[0].length == 0) {
                throw new IllegalArgumentException("Null argument passed");
            } else if (matrix.matrix.length != matrix.matrix[0].length) {
                throw new IllegalArgumentException("Matrix not square");
            } else if (matrix.matrix.length == 1 && matrix.matrix[0].length == 1) {
                return matrix.matrix[0][0];
            } else if (matrix.matrix.length == 2 && matrix.matrix[0].length == 2) {
                return matrix.matrix[0][0] * matrix.matrix[1][1] - matrix.matrix[0][1] * matrix.matrix[1][0];
            } else {
                double result = 0;
                for (int i = 0; i < matrix.matrix.length; i++) {
                    Double[][] minor = new Double[matrix.matrix.length - 1][matrix.matrix[i].length - 1];
                    for (int j = 0; j < matrix.matrix.length - 1; j++) {
                        for (int k = 0; k < matrix.matrix[i].length - 1; k++) {
                            minor[j][k] = matrix.matrix[i <= j ? j + 1 : j][k + 1];
                        }
                    }
                    result += matrix.matrix[i][0] * Math.pow(-1, i + 1 + 1) * determinant(new Matrix(minor));
                }
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Matrix inverse(Matrix matrix) {
        try {
            if (matrix == null || matrix.matrix.length == 0 || matrix.matrix[0].length == 0) {
                throw new IllegalArgumentException("Null argument passed");
            } else if (matrix.matrix.length != matrix.matrix[0].length) {
                throw new IllegalArgumentException("Matrix not square");
            } else {
                return multiply(adjoint(matrix), 1.0 / determinant(matrix));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Matrix adjoint(Matrix matrix) {
        try {
            Matrix matrixResult;
            if (matrix == null || matrix.matrix.length == 0 || matrix.matrix[0].length == 0) {
                throw new IllegalArgumentException("Null argument passed");
            } else if (matrix.matrix.length != matrix.matrix[0].length) {
                throw new IllegalArgumentException("Matrix not square");
            } else if (matrix.matrix.length == 1 && matrix.matrix[0].length == 1) {
                matrixResult = new Matrix(new Double[1][1]);
                matrixResult.matrix[0][0] = 1.0;
                return matrixResult;
            } else {

                matrixResult = new Matrix(new Double[matrix.matrix.length][matrix.matrix[0].length]);

                for (int j = 0; j < matrix.matrix.length; j++) {
                    for (int k = 0; k < matrix.matrix[j].length; k++) {

                        matrixResult.matrix[j][k] = Math.pow(-1, j + 1 + k + 1) * determinant(reduceMatrix(matrix, j, k));

                    }
                }

                return transposeMainDiagonal(matrixResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isPopulated(Matrix matrix) {
        for (int i = 0; i < matrix.matrix.length; i++) {
            for (int j = 0; j < matrix.matrix[i].length; j++) {
                if (matrix.matrix[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Matrix reduceMatrix(Matrix matrix, int row, int col) {

        if (matrix == null || matrix.matrix.length == 0 || matrix.matrix[0].length == 0) {
            return null;
        } else {
            int rows = matrix.matrix.length;
            int cols = matrix.matrix[0].length;

            Double[][] array = new Double[rows - 1][cols - 1];
            for (int i = 0; i < rows - 1; i++) {
                for (int j = 0; j < cols - 1; j++) {
                    array[i][j] = matrix.matrix[i < row ? i : i + 1][j < col ? j : j + 1];
                }
            }
            return new Matrix(array);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                sb.append(fmt(this.matrix[i][j]) + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String fmt(double d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%.2f", d);
    }
}


