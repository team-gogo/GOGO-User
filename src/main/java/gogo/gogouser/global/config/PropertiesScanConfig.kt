package gogo.gogouser.global.config

import gogo.gogouser.global.jwt.JwtProperties
import gogo.gogouser.global.security.SecurityProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class,
        SecurityProperties::class
    ]
)
class PropertiesScanConfig {

}
