package com.monkeys.arithmetic

import org.scalatest.{FlatSpec, Matchers}

class ExpressionTest extends FlatSpec with Matchers {
  "An empty expression" should "transform into a result that is zero" in {
    Expression().asResult() should equal(BigDecimal(0.0))
  }

  "A constant expression" should "transform into a result that is the constant" in {
    Expression(BigDecimal(0)).asResult() should equal(BigDecimal(0))
    Expression(BigDecimal(2.2)).asResult() should equal(BigDecimal(2.2))
    Expression(BigDecimal(-1.242)).asResult() should equal(BigDecimal(-1.242))
  }

  "An expression with left and right operands and an \"ADD\" operator" should "transform into a result that is the sum of both operands" in {
    Expression(Expression(2), Expression(3), "ADD").asResult() should equal(BigDecimal(5))
  }
}
