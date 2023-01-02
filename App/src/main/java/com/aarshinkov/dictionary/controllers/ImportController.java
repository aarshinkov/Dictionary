package com.aarshinkov.dictionary.controllers;

import com.aarshinkov.dictionary.base.*;
import com.aarshinkov.dictionary.requests.*;
import com.aarshinkov.dictionary.responses.*;
import com.aarshinkov.dictionary.services.*;
import javax.validation.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Controller
@RequiredArgsConstructor
public class ImportController extends Base {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final DictionaryService dictionaryService;

  @GetMapping("/words")
  public String getWords(@RequestParam(name = "search", defaultValue = "") String search, Model model) {

    if (search == null) {
      search = "";
    } else {
      search = search.trim();
    }

    model.addAttribute("search", search);

    model.addAttribute("words", dictionaryService.searchWords(search));

    return "words/words";
  }

  @GetMapping("/words/create")
  public String prepareCreateWord(Model model) {

    model.addAttribute("word", new WordCreateRequest());

    return "words/create";
  }

  @PostMapping(value = "/words/create")
  public String addWord(@ModelAttribute("word") @Valid WordCreateRequest word, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {

      return "words/create";
    }

    log.debug(word.toString());

    WordResponse response = dictionaryService.createWord(word);

    return "redirect:/words/create";
  }
}
