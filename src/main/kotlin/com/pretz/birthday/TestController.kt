package com.pretz.birthday

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @PostMapping("/test")
    fun sendMail(@RequestParam number: Int) {
        println("Test line: $number")
    }
}
