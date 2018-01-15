package com.monkeys.arithmetic

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class ExpressionWalker(expression: Expression) {
  def result: Future[BigDecimal] = {
    Future {
      val leftOperand: BigDecimal = expression.left match {
        case Some(leftNumber) if leftNumber.isConstantOnly => leftNumber.constant.get
        case _ => 0.0
      }

      val rightOperand: BigDecimal = expression.right match {
        case Some(rightNumber) if rightNumber.isConstantOnly => rightNumber.constant.get
        case _ => 0.0
      }

      expression.operator match {
        case Some(op) if "SUB".equals(op) => leftOperand - rightOperand
        case Some(op) if "MUL".equals(op) => leftOperand * rightOperand
        case Some(op) if "DIV".equals(op) => leftOperand / rightOperand
        case _ => leftOperand + rightOperand
      }
    }
  }
}

object ExpressionWalker {
  def apply(expression: Expression) = new ExpressionWalker(expression)
}
