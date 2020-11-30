# juice-master

It's a secret =P

## Software Requirements

```
1. jdk 1.8
2. kotlin 1.4
3. gradle 6.3
```

## Running the Application

```gradle
gradle clean run
```

## Running tests

```gradle
gradle clean test
```

## Assumptions

```
1. Assuming the data format for the signal is the one used in the app
2. Most part of the app is tests using night shift. Although the app will work in morning shift, but has not been extensively tested.
3. The traffic in the corridors are below average. Which means multiple signals will not be send one after the other.
4. Also, no motion will trigger switching off of all lights and switching on of all ACs on the same floor
```

## Possible improvements
```
1. Update status functions in InstructionProcessor could be moved to separate external functions
2. Could add more test scenarios to cover all possible outcomes
```