import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;




public class MyStackTest {

    Vector vectorMock = mock(Vector.class);
    MyStack stack = new MyStack(3);

    @Test (description = "Позитивный тест pushToStack", suiteName = "Позитивный тест pushToStack")
    public void positivePushToStackTest() {
        stack.pushToStack(vectorMock);
        assertTrue(stack.findValue(vectorMock));
    }

    @Test (description = "Позитивный тест pullFormStack", suiteName = "Позитивный тест pullFormStack")
    public void positivePullFromStackTest() {
        stack.pushToStack(vectorMock);
        assertEquals(vectorMock, stack.pullFromStack());
    }

    @Test (description = "Позитивный тест findValue", suiteName = "Позитивный тест findValue")
    public void positiveFindValueTest() {
        stack = new MyStack(3);
        stack.pushToStack(new Vector());
        stack.pushToStack(vectorMock);
        stack.pushToStack(new Vector());
        assertTrue(stack.findValue(vectorMock));
    }

    @Test (description = "Позитивный тест getSize и setSize", suiteName = "Позитивный тест getSize и setSize")
    public void positiveGetSizeAndSetSizeTest() {
        stack.setSize(5);
        assertEquals(5, stack.getSize());
    }


    @Test(expectedExceptions = { IllegalArgumentException.class }, expectedExceptionsMessageRegExp = "Size of stack is exceeded")
    public void exceptiontestOne() throws Exception {

        stack = new MyStack(1);
        stack.pushToStack(vectorMock);
        stack.pushToStack(vectorMock);


    }

    @Test(expectedExceptions = { IllegalArgumentException.class }, expectedExceptionsMessageRegExp = "No elements in stack")
    public void Noelements() throws Exception {

        stack = new MyStack(1);
       stack.pullFromStack();


    }


}


