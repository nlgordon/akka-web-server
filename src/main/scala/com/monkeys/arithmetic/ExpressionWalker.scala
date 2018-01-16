package com.monkeys.arithmetic

import com.monkeys.arithmetic.ExpressionWalkerHelper.base

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class ExpressionWalker(expression: Expr) {
  def result: Future[BigDecimal] = {
    Future {
      base(expression)
    }
  }
}

object ExpressionWalker {
  def apply(expression: Expr) = new ExpressionWalker(expression)
}

private object ExpressionWalkerHelper {
  def base(expr: Expr): BigDecimal = {
    val leftOperand: BigDecimal = expr match {
      case Expression(left, _, _, _)  =>
        left match {
          case Some (leftNumber) if leftNumber.isConstantOnly => leftNumber.constant.get
          case Some (_) if left.isDefined => base (left.get)
          case _ => 0.0
        }
      case _ => 0.0
    }

    val rightOperand: BigDecimal = expr match {
      case Expression(_, right, _, _)  =>
        right match {
          case Some (rightNumber) if rightNumber.isConstantOnly => rightNumber.constant.get
          case Some (_) if right.isDefined => base (right.get)
          case _ => 0.0
        }
      case _ => 0.0
    }

    expr match {
      case Expression(_, _, op, _) =>
        op match {
          case Some(oppy) if "SUB".equals(oppy) => leftOperand - rightOperand
          case Some(oppy) if "MUL".equals(oppy) => leftOperand * rightOperand
          case Some(oppy) if "DIV".equals(oppy) => leftOperand / rightOperand
          case _ => leftOperand + rightOperand
        }
    }
  }
}
