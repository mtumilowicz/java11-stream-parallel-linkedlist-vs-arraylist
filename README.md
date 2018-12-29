# java11-stream-parallel-linkedlist-vs-arraylist

_Reference_: http://alexandrastech.blogspot.com/2016/04/java-8-parallel-streams-performance.html  
_Reference_: https://www.amazon.com/Modern-Java-Action-functional-programming/dp/1617293563

# preface
|Source   |Decomposability   |
|---|---|
|ArrayList   |Good   |
|LinkedList   |Poor   |
|IntStream.range   |Excellent   |
|Stream.iterate   |Poor   |
|HashSet   |Good   |
|TreeSet   |Good   |

`ArrayList` can be split much more efficiently than a
`LinkedList`, because the first can be evenly divided 
without traversing it (implements `RandomAccess`), as 
it’s necessary to do with the latter.

The stream is internally splitted using Spliterator, please
refer my other github projects:
* https://github.com/mtumilowicz/java11-spliterator
* https://github.com/mtumilowicz/java11-spliterator-forkjoin

# project description
We provide basic comparison of parallel streams over `ArrayList`
and `LinkedList`.
* summing in parallel
    ```
    private int parallelSum(List<Integer> values) {
        return values.parallelStream().mapToInt(i -> i).sum();
    }
    ```
* count time of summing
    ```
    private long timeOfSumming(List<Integer> integers) {
        long startTime = System.currentTimeMillis();
        parallelSum(integers);
        return System.currentTimeMillis() - startTime;
    }
    ```
* fill lists with random numbers
    ```
    private void setAll(List<Integer> integers) {
        Random random = new Random();
        IntStream.range(1, 10_000_000)
                .forEach(i -> integers.add(random.nextInt(500)));
    }
    ```
* compare
    * `ArrayList`
        ```
        var integers = new ArrayList<Integer>();
        
        setAll(integers);
        
        System.out.println(timeOfSumming(integers));
        ```
        **time: 60**
    * `LinkedList`
        ```
        var integers = new LinkedList<Integer>();
        
        setAll(integers);
        
        System.out.println(timeOfSumming(integers));
        ```
        **time: 400**