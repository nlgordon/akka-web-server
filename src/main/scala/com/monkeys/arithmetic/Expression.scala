package com.monkeys.arithmetic

case class Expression(left: Option[Expression] = None, right: Option[Expression] = None, operator: Option[String] = None, constant: Option[BigDecimal] = None) {
  def asResult(): BigDecimal = {
    val leftResult = left match {
      case Some(lefty) => lefty.asResult()
      case _ => BigDecimal(0)
    }

    val rightResult = right match {
      case Some(righty) => righty.asResult()
      case _ => BigDecimal(0)
    }

    val constResult = constant match {
      case Some(number) => number
      case _ => BigDecimal(0)
    }

    leftResult + rightResult + constResult
  }
}

object Expression {
  def apply() = new Expression()

  def apply(leftOperand: Expression, rightOperand: Expression, op: String) = new Expression(Some(leftOperand), Some(rightOperand), Some(op))

  def apply(constant: BigDecimal) = new Expression(None, None, None, Some(constant))

  def apply(left: Option[Expression], right: Option[Expression], operator: Option[String], constant: Option[BigDecimal]) = new Expression(left, right, operator, constant)
}
