package com.aarshinkov.dictionary.responses;

import java.io.*;
import lombok.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VersionResponse implements Serializable {

  private String message;
  private String version;
  private String environment;

}
