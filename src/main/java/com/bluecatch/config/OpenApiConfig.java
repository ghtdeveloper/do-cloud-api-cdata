package com.bluecatch.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "${api.info.title}",
                description = "${api.info.description}",
                version = "${api.info.version}",
                contact = @Contact(
                        name = "${api.info.contact.name}",
                        email = "${api.info.contact.email}",
                        url = "${api.info.contact.url}"
                ),
                license = @License(
                        name = "${api.info.license.name}", url = "${api.info.license.url}"
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
@SuppressWarnings("unused")
class OpenApiConfig {
}
