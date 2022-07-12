package com.aarshinkov.dictionary.controllers;

import com.aarshinkov.dictionary.base.*;
import com.aarshinkov.dictionary.responses.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
public class HomeController extends Base {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @GetMapping(value = {"/", "/home", "/version"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<VersionResponse> version() {

    VersionResponse version = new VersionResponse();
    version.setMessage("Welcome to the Dictionary REST API");
    version.setVersion("v1.0.0");
    log.debug("Environment: " + getEnvironmentName());
    version.setEnvironment(getEnvironmentName());

    return new ResponseEntity<>(version, HttpStatus.OK);
  }
}
