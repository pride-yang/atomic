package top.yang.selector;

import java.util.Collections;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Selectable html.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class Html extends HtmlNode {

  private final static Logger logger = LoggerFactory.getLogger(Html.class);

  /**
   * Disable jsoup html entity escape. It can be set just before any Html instance is created.
   *
   * @deprecated
   */
  public static boolean DISABLE_HTML_ENTITY_ESCAPE = false;

  /**
   * Store parsed document for better performance when only one text exist.
   */
  private Document document;

  public Html(String text, String url) {
    try {
      this.document = Jsoup.parse(text, url);
    } catch (Exception e) {
      this.document = null;
      logger.error("parse document error {}", e.getMessage());
    }
  }

  public Html(String text) {
    try {
      this.document = Jsoup.parse(text);
    } catch (Exception e) {
      this.document = null;
      logger.error("parse document error {}", e.getMessage());
    }
  }

  public Html(Document document) {
    this.document = document;
  }

  public Document getDocument() {
    return document;
  }

  @Override
  protected List<Element> getElements() {
    return Collections.<Element>singletonList(getDocument());
  }

  /**
   * @param selector selector
   * @return result
   */
  public String selectDocument(Selector selector) {
    if (selector instanceof ElementSelector) {
      ElementSelector elementSelector = (ElementSelector) selector;
      return elementSelector.select(getDocument());
    } else {
      return selector.select(getFirstSourceText());
    }
  }

  public List<String> selectDocumentForList(Selector selector) {
    if (selector instanceof ElementSelector) {
      ElementSelector elementSelector = (ElementSelector) selector;
      return elementSelector.selectList(getDocument());
    } else {
      return selector.selectList(getFirstSourceText());
    }
  }

  public static Html create(String text) {
    return new Html(text);
  }

}