package com.monkeys.arithmetic

abstract class Expr

case class NullExpression() extends Expr {}

case class Constant() extends Expr {}

case class Expression(left: Option[Expression] = None,
                      right: Option[Expression] = None,
                      operator: Option[String] = None,
                      constant: Option[BigDecimal] = Some(0)) extends Expr{
  def isConstantOnly = left.isEmpty && right.isEmpty
}

object Expression {
  def apply() = new Expression()

  def apply(leftOperand: BigDecimal, rightOperand: BigDecimal, op: String) = new Expression(Some(new Expression(constant = Some(leftOperand))), Some(new Expression(constant = Some(rightOperand))), Some(op))

  def apply(leftOperand: BigDecimal, rightOperand: Expression, op: String) =
    new Expression(left = Some(new Expression(constant = Some(leftOperand))), right = Some(rightOperand), operator = Some(op))

  def apply(leftOperand: Expression, rightOperand: BigDecimal, op: String) =
    new Expression(right = Some(new Expression(constant = Some(rightOperand))), left = Some(leftOperand), operator = Some(op))
}
