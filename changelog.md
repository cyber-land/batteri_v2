
# Changelog

**disclaimer**
The user-space is not affected (functionality wise) except for a couple of obscure functions nobody really ever used

**summary**
improve readability and performance using less code lines
update to `JDK 24`

## global

- remove useless comments (a comment should say why something is done, not what)
- remove useless methods (read the disclaimer)

## Terrain.java

- has just a function so make it a function override of its base class
- remove the business logic from `paintComponent(Graphic g)` and put it in a new function (`simulation_cycle()`)

	the original function was called 3 times during the initial window and surface drawing producing the "bug" of moving uselessly the adversaries 3 times before the simulation even start

## Food.java

- make use on an array of bitsets insted of a matrix of boolean in order to use much less space ( 87.5% ) and make cache aware algorithms much more efficient

	old algos gain an important speedup too ( close to 2X )
- add a (publicly readable) counter that keeps track of the times `toggle()` is being called

## Batterio.java

- merge `run()`, `eat()` and `isDead()` since they are always call sequentially anyway

	should slightly improve main loop performance

## MainFrom.java

- make use of just one timer: this should improve data access consistency since i'm not sure how is managed if three timers access that data simultaneously (is it even possible ?!)
- merge `recuperNomi()` and `iniziallizzaBatteri()` and make both of them much more leaner
- don't track execution times during the inizialization since JIT is a bitch

	instead, they are tracked in every `simulation_cycle()` to prove the heavy toll that not-compiled languages have on the performance of their executables and the unpredictability of the black magic they use to stay competitive
- merge some attributes in a custom class ( `Adversary.java` ) to improve legibility

	instead of inserting and executing one entity per group (bad cache locality), groups are executed one after the other but their execution order is randomixed by a shuffle function to ensure the same level of fairness

# TODOs
- continua a non essere equo in caso di adversaries divisori di 20, siccome l'ordine viene ripetuto ed i primi saranno sempre primi
