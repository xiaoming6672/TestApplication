package com.zhang.practice.kotlin.model

/**
 *
 * @author ZhangXiaoMing
 * 2023-05-09 17:08 周二
 */
class TestBean(private val firstName: String, private val secondName: String) :
    BaseBean(secondName) {

    fun print() {
        print("$firstName ${basePrint()}")
        basePrint()
    }

}