package com.basic.springone

import java.io.FileNotFoundException

fun loadResource(name: String): String =
    object {}.javaClass.classLoader?.getResource(name)?.readText()
        ?: throw FileNotFoundException("file $name not found")

fun loadPayload(name: String): String =
    loadResource("payload/$name")