package com.aarshinkov.dictionary.requests;

import java.io.*;
import javax.validation.constraints.*;
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
public class WordCreateRequest implements Serializable {

  @NotBlank
  @Size(max = 100)
  private String word;

  @NotBlank
  @Size(max = 100)
  private String meaning;

  @Override
  public String toString() {
    return "Word - " + word + "; Meaning - " + meaning;
  }
}
