package com.aarshinkov.dictionary.responses;

import java.io.*;
import java.sql.*;
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
public class WordResponse implements Serializable {

  private String wordId;
  private String word;
  private String meaning;
  private Timestamp addedOn;
  private Timestamp lastEdit;

}
