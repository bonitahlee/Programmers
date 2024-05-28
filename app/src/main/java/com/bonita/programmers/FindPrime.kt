package com.bonita.programmers

import java.math.BigInteger
import kotlin.math.sqrt

/**
 * 양의 정수 n이 주어집니다. 이 숫자를 k진수로 바꿨을 때, 변환된 수 안에 아래 조건에 맞는 소수(Prime number)가 몇 개인지 알아보려 합니다.
 *
 * - 0P0처럼 소수 양쪽에 0이 있는 경우
 * - P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
 * - 0P처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
 * - P처럼 소수 양쪽에 아무것도 없는 경우
 * - 단, P는 각 자릿수에 0을 포함하지 않는 소수입니다.
 * - 예를 들어, 101은 P가 될 수 없습니다.
 * - 예를 들어, 437674을 3진수로 바꾸면 211020101011입니다. 여기서 찾을 수 있는 조건에 맞는 소수는 왼쪽부터 순서대로 211, 2, 11이 있으며, 총 3개입니다. (211, 2, 11을 k진법으로 보았을 때가 아닌, 10진법으로 보았을 때 소수여야 한다는 점에 주의합니다.) 211은 P0 형태에서 찾을 수 있으며, 2는 0P0에서, 11은 0P에서 찾을 수 있습니다.
 *
 * 정수 n과 k가 매개변수로 주어집니다. n을 k진수로 바꿨을 때, 변환된 수 안에서 찾을 수 있는 위 조건에 맞는 소수의 개수를 return 하도록 solution 함수를 완성해 주세요.
 *
 * [제한사항]
 * 1 ≤ n ≤ 1,000,000
 * 3 ≤ k ≤ 10
 */
class FindPrime {

    // 다른 사람 정답
    fun solution(n: Int, k: Int): Int {
        val s = n.toString(k)
        return s.split("0")
            .filter { p ->
                p.isNotEmpty()
                        && p != "1"
                        && isPrimeNum(p.toBigInteger())
                        && (s.contains("0${p}0")
                        || s.contains("${p}0")
                        || s.contains("0${p}")
                        || s.contains(p))
            }
            .size
    }

    private fun isPrimeNum(num: BigInteger): Boolean {
        var i = 2

        while (i <= sqrt(num.toDouble())) {
            if (num % i.toBigInteger() == BigInteger.ZERO) return false
            i++
        }
        return true
    }

    // 내가 푼 문제. n을 k 진수로 변환하는 코드
    private fun convert(n: Int, k: Int): String {
        // 1. n 을 k 진수로 변환하기
        var mok = n
        val convertNumList = mutableListOf<Int>()

        while (mok > 0) {
            val remainder = mok % k
            convertNumList.add(remainder)
            mok /= k
        }

        // 변환된 숫자를 문자열로 반환
        return convertNumList.reversed().joinToString("")
    }
}