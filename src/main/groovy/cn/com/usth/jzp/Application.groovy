package cn.com.usth.jzp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by jiangzhipeng on 16/2/19.
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
class Application extends SpringBootServletInitializer {

    static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }

}
