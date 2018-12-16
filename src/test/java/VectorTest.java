import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;



public class VectorTest {

    ArrayList<Integer> joinExpected = new ArrayList<Integer>() {{
        add(2);
        add(-5);
        add(72);
    }};

    ArrayList<Integer> crossExpected = new ArrayList<Integer>() {{
        add(-5);
    }};

    @Test(description = "позитивный тест операции Join", suiteName = "орпорпор")
    void correctVectorJoinOperations() {
        Vector vector = new Vector();
        vector.operation("[2,-5] [-5,72] +");
        Assert.assertEquals(joinExpected.toArray(), vector.vector.toArray());

    }

    @Test (description = "Позитивный тест операции Cross", suiteName = "орпорпор")

    void correctVectorCrossOperations() {
        Vector vector = new Vector();
        vector.operation("[2,-5,72] [-5] *");
        Assert.assertEquals(crossExpected.toArray(), vector.vector.toArray());
    }

    @Test (description = "Позитивный тест операции IsIn", suiteName = "орпорпор")

    void correctVectorIsInOperations() {
        Vector vector = new Vector();
        Assert.assertTrue(vector.operation("[2,-5,72] 72 ?"));
    }

  //  @Test (description = "Негативный тест регулярных выражений для векторов", suiteName = "орпорпор")

   // void invalidVectorRegex( ) {
     //   Vector vector = new Vector();
     //   Executable invalidRegexOperation = () -> {vector.operation("[wrong,,,]");};
      //  assertThrows(IllegalArgumentException.class, invalidRegexOperation,"Something is wrong with operations method");
   // }

    @Test (expectedExceptions = { IllegalArgumentException.class }, expectedExceptionsMessageRegExp = "Something is wrong with operations method")
    public void Negativetestregular() {
        Vector vector = new Vector();
       vector.operation("Wrong operation");

    }



    @Test (description = "Негативный тест операции IsIn", suiteName = "Негативный тест операции IsIn")

    void negativeVectorIsInOperations() {
        Vector vector = new Vector();
        Assert.assertFalse(vector.operation("[2,-5,30] 72 ?"));
    }
}


