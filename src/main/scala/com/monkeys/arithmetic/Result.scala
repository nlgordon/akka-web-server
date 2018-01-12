package com.monkeys.arithmetic

class Result(constant: BigDecimal) {
  def asBigDecimal() = constant
}

object Result {
  def apply(constant: BigDecimal) = new Result(constant)
}
