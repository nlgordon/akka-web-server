package com.monkeys.arithmetic

import org.scalatest.{AsyncFlatSpec, Matchers}

class ExpressionWalkerTest extends AsyncFlatSpec with Matchers {
  "Expression Walker" should "transform the addition two constant operands into a result" in {
    ExpressionWalker(Expression(2, 2, "ADD")).result map { result => result should equal(4) }
    ExpressionWalker(Expression(0, 0, "ADD")).result map { result => result should equal(0) }
    ExpressionWalker(Expression(-1, 1, "ADD")).result map { result => result should equal(0) }
    ExpressionWalker(Expression(-1, -1, "ADD")).result map { result => result should equal(-2) }
  }

  it should "transform the subtraction of two constant operands into a result" in {
    ExpressionWalker(Expression(2, 2, "SUB")).result map { result => result should equal(0) }
    ExpressionWalker(Expression(2, 1, "SUB")).result map { result => result should equal(1) }
    ExpressionWalker(Expression(-2, 1, "SUB")).result map { result => result should equal(-3) }
    ExpressionWalker(Expression(-2, -1, "SUB")).result map { result => result should equal(-1) }
  }

  it should "transform the multiplication of two constant operands into a result" in {
    ExpressionWalker(Expression(2, 2, "MUL")).result map { result => result should equal(4) }
    ExpressionWalker(Expression(2, 1, "MUL")).result map { result => result should equal(2) }
    ExpressionWalker(Expression(-2, 4, "MUL")).result map { result => result should equal(-8) }
    ExpressionWalker(Expression(-2, -1, "MUL")).result map { result => result should equal(2) }
  }
}
