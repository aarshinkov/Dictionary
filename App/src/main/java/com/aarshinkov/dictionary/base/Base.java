package com.aarshinkov.dictionary.base;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.i18n.*;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Component
public class Base {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private MessageSource messageSource;

  protected String getMessage(String key) {
    try {
      return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    } catch (NoSuchMessageException e) {
      return "";
    }
  }

  protected String getMessage(String key, Object... params) {
    try {
      return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
    } catch (NoSuchMessageException e) {
      return "";
    }
  }

  public String getEnvironment() {
    return getMessage("env");
  }

  public String getEnvironmentName() {
    return getMessage("env.name");
  }
}
