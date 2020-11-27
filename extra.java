import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class extra {
    public static void main(String[] args) {
        //TEST6
        //System.out.println(bit_to_IP("2149583361"));
        //System.out.println(bit_to_IP("32"));
        //System.out.println(bit_to_IP("0"));

        //TEST7
        //System.out.println(nextBigger(12));
        //System.out.println(nextBigger(513));
        //System.out.println(nextBigger(2017));
        //System.out.println(nextBigger(9));
        //System.out.println(nextBigger(111));
        //System.out.println(nextBigger(531));
    }

    public static int nextBigger(int num) {
        String strnum = Integer.toString(num);
        int[] subnum, subnum_sorted;

        for (int i = strnum.length()-2; i >= 0; i--) {
            subnum = strnum.substring(i).chars().map(ch-> ch-'0').toArray();
            subnum_sorted = Arrays.stream(subnum).boxed()
                    .sorted(Collections.reverseOrder())
                    .mapToInt(Integer::intValue)
                    .toArray();

            if(Arrays.compare(subnum,subnum_sorted)<0){
                StringBuilder builder = new StringBuilder(strnum.substring(0,i));
                Arrays.stream(subnum_sorted).forEach(c-> builder.append(c));
                return Integer.parseInt(builder.toString());
            }
        }
        return -1;
    }

    public static String bit_to_IP(String num) {
        BigInteger bignum = new BigInteger(num);
        String binary = String.format("%32s", bignum.toString(2)).replaceAll(" ", "0");
        String IPv4 = new StringBuilder().append(Integer.parseInt(binary.substring(0, 8), 2)).append(".")
                .append(Integer.parseInt(binary.substring(9, 16), 2)).append(".")
                .append(Integer.parseInt(binary.substring(17, 24), 2)).append(".")
                .append(Integer.parseInt(binary.substring(25, 32), 2)).toString();

        return IPv4;
    }
}
