package com.aarshinkov.dictionary.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.*;
import org.springframework.web.servlet.config.annotation.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Configuration
public class CoreConfig implements WebMvcConfigurer {

  @Bean
  public ReloadableResourceBundleMessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//    messageSource.setBasenames("classpath:messages/messages");
//    messageSource.setBasenames("classpath:messages/messages", "classpath:env");
    messageSource.setBasenames("classpath:env");
    messageSource.setDefaultEncoding("UTF-8");

    return messageSource;
  }
}
