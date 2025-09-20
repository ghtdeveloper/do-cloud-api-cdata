package com.bluecatch.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "${api.info.title}",
                description = "${api.info.description}",
                version = "${api.info.version}",
                contact = @Contact(
                        name = "${api.info.contact.name}",
                        email = "n/a",
                        url = "n/a"
                ),
                license = @License(
                        name = "${api.info.license.name}",
                        url = "${api.info.license.url}"
                ),
                termsOfService = "${api.info.termsOfService}"
        ),
        servers = {
                @Server(
                        url = "${api.servers.server1.url}",
                        description = "${api.servers.server1.description}"
                )
        }
)
@SecurityScheme(
        name = "apiKey",
        type = SecuritySchemeType.APIKEY,
        paramName = "x-api-key",
        in = io.swagger.v3.oas.annotations.enums.SecuritySchemeIn.HEADER,
        description = "Api Key authentication"
)
@SuppressWarnings("unused")
class OpenApiConfig {
}