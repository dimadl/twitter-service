package by.epam.twitterservice.util;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author Dzmitry_Dydyshka.
 */
public class NewsJSONBody {

    @XmlElement(name = "full_text")
    private String fullText;

    @XmlElement(name="short_text")
    private String shortText;

    private String title;

    @XmlElement(name="creation_date")
    private String creationDate;

    @XmlElement(name="modification_date")
    private String modificationDate;

    private List<String> tags;

    private String author;

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
