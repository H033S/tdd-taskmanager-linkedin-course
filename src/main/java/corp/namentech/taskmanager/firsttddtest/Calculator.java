package corp.namentech.taskmanager.firsttddtest;

public class Calculator {

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by 0");
        }
        return a / b;
    }
}
