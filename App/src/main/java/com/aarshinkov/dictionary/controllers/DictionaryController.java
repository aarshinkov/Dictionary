package com.aarshinkov.dictionary.controllers;

import com.aarshinkov.dictionary.base.*;
import com.aarshinkov.dictionary.requests.*;
import com.aarshinkov.dictionary.responses.*;
import com.aarshinkov.dictionary.services.*;
import java.util.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
public class DictionaryController extends Base {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final DictionaryService dictionaryService;

  @GetMapping(value = "/dictionary/words", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<WordResponse>> search(@RequestParam(name = "s", required = false) String search) {

    List<WordResponse> words = dictionaryService.searchWords(search);

    return new ResponseEntity<>(words, HttpStatus.OK);
  }

  @PostMapping(value = "/dictionary/words", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WordResponse> addWord(@RequestBody WordCreateRequest wcr) {

//    WordResponse response = dictionaryService.createWord(wбоrc);
    WordResponse response = new WordResponse();

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
