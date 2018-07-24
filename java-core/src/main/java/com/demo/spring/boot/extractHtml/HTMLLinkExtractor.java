package com.demo.spring.boot.extractHtml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bqhuy on 7/23/2018.
 */
public class HTMLLinkExtractor {
    private static Logger logger = LoggerFactory.getLogger(HTMLLinkExtractor.class);
    private Pattern patternTag, patternHref, patternTitle;
    private Matcher matcherTag, matcherHref, matcherTitle;

    private static final String HTML_A_TAG_PATTERN = "<li[^>]*>(.+?)</li>";
    private static final String HTML_A_HREF_TAG_PATTERN = "\\s*href\\s*=\\s*(\"https://drive.google.com/uc([^\"]*\")|'[^']*'|([^'\">\\s]+))";
    private static final String HTML_A_TITLE_TAG_PATTERN = "\\s*title\\s*=\\s*\"\\s*([^\"]*)\\s*\"\\s*";


    public HTMLLinkExtractor() {
        patternTag = Pattern.compile(HTML_A_TAG_PATTERN);
        patternHref = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
        patternTitle = Pattern.compile(HTML_A_TITLE_TAG_PATTERN);
    }

    /**
     * Validate html with regular expression
     *
     * @param html html content for validation
     * @return Vector links and link text
     */
    public Vector<HtmlLink> grabHTMLLinks(final String html) {
        Vector<HtmlLink> result = new Vector<HtmlLink>();
        matcherTag = patternTag.matcher(html);
        while (matcherTag.find()) {
            String content = matcherTag.group(1);
            String link = null;
            String title = null;
            matcherHref = patternHref.matcher(content);
            if (matcherHref.find()) {
                link = matcherHref.group(1);
            }
            matcherTitle = patternTitle.matcher(content);
            if (matcherTitle.find()) {
                title = matcherTitle.group(1);
            }
            if (link != null && title != null) {
                HtmlLink obj = new HtmlLink();
                obj.setTitle(title);
                obj.setLink(link);
                result.add(obj);
            }
        }

        return result;
    }
}
