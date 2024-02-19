# Sum Dates Dojo

We don’t have a library to calculate the result of adding seconds in a given date time.
Lets create a function to sum amount of seconds in a date time.

## Example 1

### Input

```
date: 2014-03-04 02:00:00

seconds: 100000
```

### Output

```
2014-03-05 05:46:40
```

## Example 2

### Input

```
date: 2014-03-04 02:00:00

seconds: 30
```

### Output

```
2014-03-04 02:00:30

2 * 60 =  min
2 * 60 * 60 = sec
(2 * 60 * 60) + 30 = sec
(4 * 24 * 60 * 60) + (2 * 60 * 60) + 30 = sec

2345213452345 sec

input: 02:00:00
sec: 70

70 % 60 = 10

min = sec % unit hour / one min
hour = sec % unit day / one hour

output: 02:01:10

```

### Requirements

- Java 21
- Maven 3

### Running

```
mvn clean install
```