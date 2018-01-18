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
      case _ => 0.0
    }
  }

  def base(expr: Expression): BigDecimal = {
    val leftOperand: BigDecimal = expr.left match {
      case expression: Expression => base(expression)
      case expression: BaseExpression => base(expression)
      case _ => 0.0
    }

    val rightOperand: BigDecimal = expr.right match {
      case expression: Expression => base(expression)
      case expression: BaseExpression => base(expression)
      case _ => 0.0
    }

    expr.operator match {
      case op if "SUB".equals(op) => leftOperand - rightOperand
      case op if "MUL".equals(op) => leftOperand * rightOperand
      case op if "DIV".equals(op) => leftOperand / rightOperand
      case _ => leftOperand + rightOperand
    }
  }
}
