package examples;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    static Calculator calc;

    @BeforeAll
     static public void setUp()
    {
        calc=new Calculator();
    }

    @Test
    public void additionTest()
    {
        int result=calc.add(5,10);

        Assert.assertEquals(15,result);
    }

    @Test
    public void multiplyTest()
    {
        int result=calc.multiply(5,10);

        Assert.assertEquals(50,result);
    }
}
