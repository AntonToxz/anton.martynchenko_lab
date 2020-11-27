import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class main {

    public static void main(String[] args) {
        //TEST1
        //System.out.println(getIntegersFromList(Arrays.asList(1,2,"a","b")));
        //System.out.println(getIntegersFromList(Arrays.asList(1,2, "a","b",0,15)));
        //System.out.println(getIntegersFromList(Arrays.asList(1,2,"a","b","aasf","1","123", 231)));

        //TEST2
        System.out.println(first_non_repeating_letter("stress"));
        System.out.println(first_non_repeating_letter("sTreSS"));

        //TEST3
        ///System.out.println(sum_of_digits(12345));
        //System.out.println(sum_of_digits(1623451));

        //TEST4.1
        //System.out.println(count_of_pairs_sum1(Arrays.asList(1, 3, 6, 2, 2, 0, 4, 5), 6));
        //System.out.println(count_of_pairs_sum1(Arrays.asList(1, 3, 6, 2, 2, 0, 4, 5), 5));

        //TEST4.2
        //System.out.println(count_of_pairs_sum2(Arrays.asList(1, 3, 6, 2, 2, 0, 4, 5), 6));
        //System.out.println(count_of_pairs_sum2(Arrays.asList(1, 3, 6, 2, 2, 0, 4, 5), 5));

        //TEST5
        //System.out.println(members("Fred:Conwill;Willfred:Conwill;Barney:Tornbull;Betty:Tornbull;Bjon:Tornbull;Raphael:Conwill;Alfred:Conwill"));
        //System.out.println(members("Fred:Conwill;Willfred:Conwill;Anton:Mart;Betty:Tornbull;Bjon:Tornbull;Ktoto:Toto;Alfred:Conwill"));
    }

    /**
     * Takes list of non-negative integers and strings and returns a new list with the strings filtered out
     * @param array array with strings and non-integers
     * @return array without strings
     */
    public static ArrayList<Integer> getIntegersFromList(List array) {
        ArrayList<Integer> integers = new ArrayList<>();
        array.stream().filter(num -> num instanceof Integer).forEach(num -> integers.add((Integer) num));
        return integers;
    }

    /**
     * Takes string and returns fists unique char
     * @param str random string
     * @return first unique char
     */
    public static char first_non_repeating_letter(String str) {
        OptionalInt ichar = str.chars().filter(c -> str.chars().filter(ch -> (c == ch)||(c == ch+32)).count() == 1).findFirst();
        return (char) ichar.getAsInt();
    }

    /**
     * Counts recursive sum of digits in a number
     * @param num random number
     * @return sum of digits
     */
    public static int sum_of_digits(int num) {
        String snum = String.valueOf(num);
        if (snum.length() > 1) {
            int x = Character.getNumericValue(snum.charAt(0));
            return x + sum_of_digits(Integer.parseInt(snum.substring(1)));
        } else {
            return num;
        }
    }

    /**
     * Using for() counts unique combinations of numbers in array sum of which equals target
     * @param array input array of numbers
     * @param target random number
     * @return number of combinations
     */
    public static int count_of_pairs_sum1(List<Integer> array, int target) {
        int count = 0;
        for (int first = 0; first < array.size(); first++) {
            for (int second = first + 1; second < array.size(); second++) {
                if (array.get(first) + array.get(second) == target) {
                    //System.out.println(array.get(first) + " " + array.get(second));
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Using stream counts unique combinations of numbers in array sum of which equals target
     * @param array input array of numbers
     * @param target random number
     * @return number of combinations
     */
    public static int count_of_pairs_sum2(List<Integer> array, int target) {
        List<Integer> arr = new ArrayList<>(array);
        AtomicInteger count = new AtomicInteger(),
                skip = new AtomicInteger();

        arr.stream().forEach(a->{
            arr.stream().skip(skip.incrementAndGet()).forEach(b->{
                if (a+b==target)
                    count.getAndIncrement();
            });
        });

        return count.get();
    }

    /**
     * Parses string with names and sorts in alphabet order
     * @param str string with names and surnames devided by ';'
     * @return string with sorted names
     */
    public static String members(String str) {
        List<String> names = Arrays.asList(str.split(";"));
        names.forEach(s -> {
            String[] temp = s.split(":");
            names.set(names.indexOf(s), "(" + temp[1] + ", " + temp[0] + ")");
        });

        StringBuilder result = new StringBuilder();
        names.stream().sorted().forEach(s-> result.append(s));
        return result.toString();
    }

}
