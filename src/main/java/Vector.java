import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vector
{
    public ArrayList<Integer> vector;

    public Vector()
    {
    }

    private String arrayRegex = "^\\[([-]?\\d+(,\\s*[-]?\\d+)*)?\\]\\s*";


    //Ðåãóëÿðíûå âûðàæåíèÿ äëÿ îïåðàöèé íàä âåêòîðîì
    private String[] operationsRegex =
            {
                    arrayRegex + arrayRegex.replace("^", "") + "[+]$",	//Îáúåäèíåíèå âåêòîðîâ
                    arrayRegex + "[-]?\\d+\\s*[?]$", 					//Âõîæäåíèå ÷èñëà â âåêòîð
                    arrayRegex + arrayRegex.replace("^", "") + "[*]$",	//Ïåðåñå÷åíèå âåêòîðîâ
            };

    public boolean operation(String inputString)
    {
        int handleOption = -1;
        for (int i = 0; i < operationsRegex.length; i++)
        {
            Pattern pattern = Pattern.compile(operationsRegex[i]);
            Matcher matcher = pattern.matcher(inputString);
            if (matcher.matches())
            {
                handleOption = i;
                break;
            }
        }
        return operationsHandler(handleOption, inputString);
    }

    private boolean operationsHandler(int operation, String inputString)
    {
        switch (operation) {
            case 0:
                return joinVectors(inputString);
            case 1:
                return findIfIsInVector(inputString);
            case 2:
                return crossVectors(inputString);
            default:
                throw new IllegalArgumentException
                        ("Something is wrong with operations method");


        }
    }

    private boolean findIfIsInVector(String inputString)
    {
        Pattern pattern = Pattern.compile(arrayRegex);
        Matcher matcher = pattern.matcher(inputString);
        matcher.find();
        List<Integer> vectorLeft = parseStringToArrayInt(matcher.group(0));
        Integer number = Integer
                .parseInt(inputString.replaceAll(arrayRegex, "").replaceAll("\\s*[?]", ""));
        return vectorLeft.contains(number);
    }

    private boolean joinVectors(String inputString)
    {
        Pattern pattern = Pattern.compile(arrayRegex.replace("^", ""));
        Matcher matcher = pattern.matcher(inputString);
        matcher.find();
        int endOfFirst = matcher.end();
        ArrayList<Integer> vectorLeft = parseStringToArrayInt(matcher.group(0));
        matcher.find(endOfFirst);
        List<Integer> vectorRight = parseStringToArrayInt(matcher.group(0));
        for (Integer number : vectorRight)
        {
            if (!vectorLeft.contains(number))
            {
                vectorLeft.add(number);
            }
        }
        vector = vectorLeft;
        return true;
    }

    private boolean crossVectors(String inputString)
    {
        Pattern pattern = Pattern.compile(arrayRegex.replace("^", ""));
        Matcher matcher = pattern.matcher(inputString);
        matcher.find();
        int endOfFirst = matcher.end();
        List<Integer> vectorLeft = parseStringToArrayInt(matcher.group(0));
        matcher.find(endOfFirst);
        List<Integer> vectorRight = parseStringToArrayInt(matcher.group(0));
        ArrayList<Integer> newVector = new ArrayList<Integer>();
        for (Integer number : vectorRight)
        {
            if (vectorLeft.contains(number))
            {
                newVector.add(number);
            }
        }
        vector = newVector;
        return true;
    }

    private ArrayList<Integer> parseStringToArrayInt(String stringToParse)
    {
        String[] items = stringToParse.replaceAll("\\[", "").replaceAll("\\]", "")
                .replaceAll("\\s", "").split(",");
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < items.length; i++) {
            numbers.add(Integer.parseInt(items[i]));
        }
        return numbers;
    }

}