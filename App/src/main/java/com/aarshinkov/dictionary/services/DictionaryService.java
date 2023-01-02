package com.aarshinkov.dictionary.services;

import com.aarshinkov.dictionary.requests.*;
import com.aarshinkov.dictionary.responses.*;
import java.util.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public interface DictionaryService {

  List<WordResponse> searchWords(String search);
  
  WordResponse createWord(WordCreateRequest wcr);

}
