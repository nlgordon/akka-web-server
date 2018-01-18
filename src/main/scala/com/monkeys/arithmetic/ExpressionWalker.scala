package com.monkeys.arithmetic

import com.monkeys.arithmetic.ExpressionWalkerHelper.base

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class ExpressionWalker(expression: BaseExpression) {
  def result: Future[BigDecimal] = {
    Future {
      base(expression)
    }
  }
}

object ExpressionWalker {
  def apply(expression: BaseExpression) = new ExpressionWalker(expression)
}

private object ExpressionWalkerHelper {
  def base(expr: BaseExpression): BigDecimal = {
    expr match {
      case Constant(constant) => constant
      case expression: Expression => base(expression)
    }
  }

  def base(expr: Expression): BigDecimal = {
    val leftOperand: BigDecimal = base(expr.left)

    val rightOperand: BigDecimal = base(expr.right)

    expr.operator match {
      case op if "SUB".equals(op) => leftOperand - rightOperand
      case op if "MUL".equals(op) => leftOperand * rightOperand
      case op if "DIV".equals(op) => leftOperand / rightOperand
      case _ => leftOperand + rightOperand
    }
  }
}
