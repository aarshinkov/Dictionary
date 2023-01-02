package com.aarshinkov.dictionary.services;

import com.aarshinkov.dictionary.requests.*;
import com.aarshinkov.dictionary.responses.*;
import java.sql.*;
import java.util.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<WordResponse> searchWords(String search) {

    if (search == null) {
      search = "";
    } else {
      search = search.trim().toLowerCase();
    }

    final String wordsSql = "SELECT w.* FROM words w WHERE w.meaning LIKE '%' || LOWER(?) || '%' OR w.word LIKE '%' || LOWER(?) || '%' ORDER by meaning;";

    try ( Connection conn = Objects.requireNonNull(jdbcTemplate.getDataSource().getConnection());
             PreparedStatement wordsPstmt = conn.prepareStatement(wordsSql)) {

      try {

//        conn.setAutoCommit(false);
        wordsPstmt.setString(1, search.toLowerCase());
        wordsPstmt.setString(2, search.toLowerCase());

        ResultSet rset = wordsPstmt.executeQuery();

//        conn.commit();
        List<WordResponse> words = new ArrayList<>();

        while (rset.next()) {
          WordResponse w = getWordFromResultSet(rset);

          words.add(w);
        }

        return words;

      } catch (SQLException ex) {
        log.error("Error searching a word", ex);
//        conn.rollback();
      } finally {
//        conn.setAutoCommit(true);
      }
    } catch (Exception e) {
      log.error("Error searching a word", e);
    }

    return new ArrayList<>();
  }

  @Override
  public WordResponse createWord(WordCreateRequest wcr) {

    wcr.setWord(wcr.getWord().trim().toLowerCase());
    wcr.setMeaning(wcr.getMeaning().trim().toLowerCase());

    log.debug("Adding word to dictionary. " + wcr.toString());

    final String wordCreateSql = "INSERT INTO words (word_id, word, meaning) VALUES (?, ?, ?)";
    final String wordGetSql = "SELECT w.* FROM words w WHERE w.word_id = ?";

    try ( Connection conn = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
             PreparedStatement wordCreatePstmt = conn.prepareStatement(wordCreateSql);
             PreparedStatement wordGetPstmt = conn.prepareStatement(wordGetSql)) {

      try {

        conn.setAutoCommit(false);

        final String WORD_ID = UUID.randomUUID().toString();

        wordCreatePstmt.setString(1, WORD_ID);
        wordCreatePstmt.setString(2, wcr.getWord());
        wordCreatePstmt.setString(3, wcr.getMeaning());

        wordCreatePstmt.execute();

        conn.commit();

        log.debug("Word with ID " + WORD_ID + " added to dictionary.");

        wordGetPstmt.setString(1, WORD_ID);

        ResultSet rset = wordGetPstmt.executeQuery();

        if (rset.next()) {
          return getWordFromResultSet(rset);
        }

      } catch (SQLException ex) {
        log.error("Error adding category to product", ex);
        conn.rollback();
      } finally {
        conn.setAutoCommit(true);
      }
    } catch (Exception e) {

      log.error("Error adding category to product", e);
    }

    return null;
  }

  private WordResponse getWordFromResultSet(ResultSet rset) throws SQLException {
    WordResponse w = new WordResponse();
    w.setWordId(rset.getString("word_id"));
    w.setWord(rset.getString("word"));
    w.setMeaning(rset.getString("meaning"));
    w.setAddedOn(rset.getTimestamp("added_on"));
    w.setLastEdit(rset.getTimestamp("last_edit"));

    return w;
  }
}
