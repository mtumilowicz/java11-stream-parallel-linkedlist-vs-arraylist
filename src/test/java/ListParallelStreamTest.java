import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by mtumilowicz on 2018-12-29.
 */
public class ListParallelStreamTest {
    @Test
    public void arrayList() {
        var integers = new ArrayList<Integer>();

        setAll(integers);
        
        System.out.println(timedSum(integers));
    }

    @Test
    public void linkedList() {
        var integers = new LinkedList<Integer>();

        setAll(integers);

        System.out.println(timedSum(integers));
    }

    private void setAll(List<Integer> integers) {
        Random random = new Random();
        IntStream.range(1, 10_000_000)
                .forEach(i -> integers.add(random.nextInt(500)));
    }

    private long timedSum(List<Integer> integers) {
        long startTime = System.currentTimeMillis();
        sum(integers);
        return System.currentTimeMillis() - startTime;
    }

    private int sum(List<Integer> values) {
        return values.parallelStream().mapToInt(i -> i).sum();
    }
}
