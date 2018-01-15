package com.monkeys.arithmetic

import com.monkeys.arithmetic.ExpressionWalkerHelper.base

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class ExpressionWalker(expression: Expression) {
  def result: Future[BigDecimal] = {
    Future {
      base(expression)
    }
  }
}

object ExpressionWalker {
  def apply(expression: Expression) = new ExpressionWalker(expression)
}

private object ExpressionWalkerHelper {
  def base(expr: Expression): BigDecimal = {
    val leftOperand: BigDecimal = expr.left match {
      case Some(leftNumber) if leftNumber.isConstantOnly => leftNumber.constant.get
      case _ => 0.0
    }

    val rightOperand: BigDecimal = expr.right match {
      case Some(rightNumber) if rightNumber.isConstantOnly => rightNumber.constant.get
      case Some(_) if expr.right.isDefined => base(expr.right.get)
      case _ => 0.0
    }

    expr.operator match {
      case Some(op) if "SUB".equals(op) => leftOperand - rightOperand
      case Some(op) if "MUL".equals(op) => leftOperand * rightOperand
      case Some(op) if "DIV".equals(op) => leftOperand / rightOperand
      case _ => leftOperand + rightOperand
    }
  }
}
