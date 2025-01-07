# Performance test

| Collection          | Elements Quantity | Contains the value  | Time Taken (ms) |
|---------------------|-------------------|---------------------|-----------------|
| List<Int>           | 1000000           | true                | 3               | 
| MutableList<Int>    | 1000000           | true                | 1               | 
| Set<Int>            | 1000000           | true                | 0               | 
| MutableSet<Int>     | 1000000           | true                | 0               | 
| List<String>        | 1000000           | true                | 7               | 
| MutableList<String> | 1000000           | true                | 1               | 
| Set<String>         | 999991            | true                | 0               | 
| MutableSet<String>  | 999992            | true                | 0               |
| List<Int>           | 5000000           | true                | 7               | 
| MutableList<Int>    | 5000000           | true                | 3               | 
| Set<Int>            | 5000000           | true                | 0               | 
| MutableSet<Int>     | 5000000           | true                | 0               | 
| List<String>        | 5000000           | true                | 37              | 
| MutableList<String> | 5000000           | true                | 15              | 
| Set<String>         | 4999925           | true                | 0               | 
| MutableSet<String>  | 4999946           | true                | 0               |
| List<Int>           | 10000000          | true                | 24              |
| MutableList<Int>    | 10000000          | true                | 20              |
| Set<Int>            | 10000000          | true                | 0               |
| MutableSet<Int>     | 10000000          | true                | 0               |
| List<String>        | 10000000          | true                | 59              |
| MutableList<String> | 10000000          | true                | 81              |
| Set<String>         | 9999713           | true                | 0               |
| MutableSet<String>  | 9999680           | true                | 0               |


## Output

1. - Use `collection.plus` is super slow to generate collection
2. - Solution was use mutable collections
3. - Set has the best lookup time