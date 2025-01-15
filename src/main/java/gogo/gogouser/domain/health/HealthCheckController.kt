package gogo.gogouser.domain.health

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @GetMapping("/user")
    fun healthCheck() = "GOGO User Service OK"

}
