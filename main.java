import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class main {

    public static void main(String[] args) {
        //TEST1
        //System.out.println(getIntegersFromList(Arrays.asList(1,2,"a","b")));
        //System.out.println(getIntegersFromList(Arrays.asList(1,2, "a","b",0,15)));
        //System.out.println(getIntegersFromList(Arrays.asList(1,2,"a","b","aasf","1","123", 231)));

        //TEST2
        //System.out.println(first_non_repeating_letter("abcde"));
        //System.out.println(first_non_repeating_letter("abcdea"));

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

    public static int sum_of_digits(int num) {
        String snum = String.valueOf(num);
        if (snum.length() > 1) {
            int x = Character.getNumericValue(snum.charAt(0));
            return x + sum_of_digits(Integer.parseInt(snum.substring(1)));
        } else {
            return num;
        }
    }

    public static char first_non_repeating_letter(String str) {
        OptionalInt ichar = str.chars().filter(c -> str.chars().filter(ch -> c == ch).count() == 1).findFirst();
        return (char) ichar.getAsInt();
    }

    public static ArrayList<Integer> getIntegersFromList(List array) {
        ArrayList<Integer> integers = new ArrayList<>();
        array.stream().filter(num -> num instanceof Integer).forEach(num -> integers.add((Integer) num));
        return integers;
    }

}
