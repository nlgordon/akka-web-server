package com.monkeys.arithmetic

abstract class BaseExpression

case class Expression(left: Option[BaseExpression] = None,
                      right: Option[BaseExpression] = None,
                      operator: Option[String] = None) extends BaseExpression {
}

case class Constant(constant: BigDecimal) extends BaseExpression

object Expression {
  def apply() = new Expression()

  def apply(leftOperand: BigDecimal, rightOperand: BigDecimal, op: String) =
    new Expression(left = Some(Constant(leftOperand)), right = Some(Constant(rightOperand)), Some(op))

  def apply(leftOperand: BigDecimal, rightOperand: Expression, op: String) =
    new Expression(left = Some(Constant(leftOperand)), right = Some(rightOperand), operator = Some(op))

  def apply(leftOperand: Expression, rightOperand: BigDecimal, op: String) =
    new Expression(right = Some(Constant(rightOperand)), left = Some(leftOperand), operator = Some(op))
}

