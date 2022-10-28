package io.github.prem_sh.configurations;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(info = @Info(title = "SIEMENS PLCs API", version = "3.0", description = "This api provides SIEMENS plc devices Information", contact = @Contact(name = "Premkumar A", url = "https://github.com/prem-sh", email = "premkumar@fake.com"), license = @License(name = "GPL")), servers = {
		@Server(description = "Localhost server", url = "http://localhost:8080"),
		@Server(description = "Developement server", url = "http://dev.prem-sh.com:8080"),
		@Server(description = "Staging server", url = "http://stage.prem-sh.com:8080"),
		@Server(description = "Production server", url = "http://prem-sh.com:8080") })
@SecurityScheme(name = "Basic-auth", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
class OpenAPIConfiguration {
}

public class OpenApiDocsConfig {

}
