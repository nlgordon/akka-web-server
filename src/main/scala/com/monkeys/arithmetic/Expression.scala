package com.monkeys.arithmetic

case class Expression(left: Option[Expression] = None, right: Option[Expression] = None, operator: Option[String] = None, constant: Option[BigDecimal] = Some(0)) {
  def isConstantOnly = left.isEmpty && right.isEmpty
}

object Expression {
  def apply() = new Expression()

  def apply(leftOperand: BigDecimal, rightOperand: BigDecimal, op: String) = new Expression(Some(new Expression(constant = Some(leftOperand))), Some(new Expression(constant = Some(rightOperand))), Some(op))

  def apply(leftOperand: BigDecimal, rightOperand: Expression, op: String) = new Expression(Some(new Expression(constant = Some(leftOperand))), Some(rightOperand), Some(op))
}
