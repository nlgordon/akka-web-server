package com.monkeys.arithmetic

abstract class BaseExpression

case class Expression(left: BaseExpression,
                      right: BaseExpression,
                      operator: String) extends BaseExpression {
}

case class Constant(constant: BigDecimal) extends BaseExpression

object Expression {
  def apply(leftOperand: BigDecimal, rightOperand: BigDecimal, op: String) =
    new Expression(left = Constant(leftOperand), right = Constant(rightOperand), op)

  def apply(leftOperand: BigDecimal, rightOperand: Expression, op: String) =
    new Expression(left = Constant(leftOperand), right = rightOperand, operator = op)

  def apply(leftOperand: Expression, rightOperand: BigDecimal, op: String) =
    new Expression(right = Constant(rightOperand), left = leftOperand, operator = op)
}

