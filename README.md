# fpLib
A scala functional library created for educational purposes.

It contains **Monads, Applicatives and functors** as basic building blocks
and implementation of functional effects as **IO** using these.

The concept behind IO is that code inside it doesn't starts executing just after its called but it is stored as a data structure instead...

We can create our program as a composition of these IO's and at last run them to execute the underlying code.


