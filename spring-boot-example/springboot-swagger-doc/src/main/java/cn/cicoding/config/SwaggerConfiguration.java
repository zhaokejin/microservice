package cn.cicoding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhaokejin
 * @description
 * @date 2019/12/19
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	/**
	 * swagger 信息
	 *
	 * @return 页面信息
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Cicoding API")
				.description("Cicoding API")
				.termsOfServiceUrl("")
				.version("1.0.0")
				.contact(new Contact("", "", "")).build();
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("cn.cicoding"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(this.apiInfo());
		//.globalOperationParameters(parameters);
	}
}
