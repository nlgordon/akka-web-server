package com.monkeys.arithmetic

case class Expression(left: Option[Expression] = None, right: Option[Expression] = None, operator: Option[String] = None, constant: Option[BigDecimal] = None) {
  def asResult(): BigDecimal = constant match {
    case Some(number) => number
    case _ => 0.0
  }
}

object Expression {
  def apply() = new Expression()
  def apply(constant: BigDecimal) = new Expression(None, None, None, Some(constant))
  def apply(left: Option[Expression], right: Option[Expression], operator: Option[String], constant: Option[BigDecimal]) = new Expression(left, right, operator, constant)
}
