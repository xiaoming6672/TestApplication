package com.zhang.practice.kotlin.model

/**
 *
 * @author ZhangXiaoMing
 * 2023-05-09 20:45 周二
 */
open class BaseBean(b: String) {
    val base: String = b

    fun basePrint(): String {
        return base
    }
}