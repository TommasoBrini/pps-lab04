package tasks.adts

/*  Exercise 1: 
 *  Complete the implementation of ComplexADT trait below, so that it passes
 *  the test in ComplexTest.
 */

object Ex1ComplexNumbers:

  trait ComplexADT:
    type Complex
    def complex(re: Double, im: Double): Complex
    extension (complex: Complex)
      def re(): Double
      def im(): Double
      def sum(other: Complex): Complex
      def subtract(other: Complex): Complex
      def asString(): String

  object BasicComplexADT extends ComplexADT:

    private case class ComplexImpl(re: Double, im: Double)
    // Change assignment below: should probably define a case class and use it?
    opaque type Complex = ComplexImpl

    def complex(re: Double, im: Double): Complex = ComplexImpl(re, im)

    extension (c: Complex)
      def re(): Double = c.re
      def im(): Double = c.im
      def sum(other: Complex): Complex = complex(c.re + other.re, c.im + other.im)
      def subtract(other: Complex): Complex = complex(c.re - other.re, c.im - other.im)
      def asString(): String = c match
        case ComplexImpl(0, 0) => "0.0"
        case ComplexImpl(_, 0) => c.re.toString
        case ComplexImpl(0, _) => c.im + "i"
        case ComplexImpl(_, _) if c.im < 0 => c.re + " - " + c.im.abs + "i"
        case _ => c.re + " + " + c.im + "i"
