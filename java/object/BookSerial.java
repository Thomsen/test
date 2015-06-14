
import java.io.Serializable;

public class BookSerial implements Serializable {

    /**
     * Describe title here.
     */
    private String title;
    /**
     * Describe date here.
     */
    private String date;
    /**
     * Describe pageCount here.
     */
    private int pageCount;
    /**
     * Describe author here.
     */
    private AuthorSerial author;

    public BookSerial(String title, String date, int pageCount, AuthorSerial author) {
        this.title = title;
        this.date = date;
        this.pageCount = pageCount;
        this.author = author;
    }

    /**
     * Get the <code>Title</code> value.
     *
     * @return a <code>String</code> value
     */
    public final String getTitle() {
        return title;
    }

    /**
     * Set the <code>Title</code> value.
     *
     * @param title The new Title value.
     */
    public final void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Get the <code>Date</code> value.
     *
     * @return a <code>String</code> value
     */
    public final String getDate() {
        return date;
    }

    /**
     * Set the <code>Date</code> value.
     *
     * @param date The new Date value.
     */
    public final void setDate(final String date) {
        this.date = date;
    }

    /**
     * Get the <code>PageCount</code> value.
     *
     * @return an <code>int</code> value
     */
    public final int getPageCount() {
        return pageCount;
    }

    /**
     * Set the <code>PageCount</code> value.
     *
     * @param pageCount The new PageCount value.
     */
    public final void setPageCount(final int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * Get the <code>AuthorSerial</code> value.
     *
     * @return an <code>AuthorSerialode> value
     */
    public final AuthorSerial getAuthor() {
        return author;
    }

    /**
     * Set the <code>AuthorSerial</code> value.
     *
     * @param author The new Author value.
     */
    public final void setAuthor(final AuthorSerial author) {
        this.author = author;
    }

    /**
     * Get a string representation of this object.
     * 
     * @return a string representation of this object.
     * 
     * @see java.lang.Object#toString
     */
    public final String toString() {
        return new StringBuilder("title=" + title)
            .append(", date=" + date)
            .append(", pageCount=" + pageCount)
            .append(", hashCode=" + hashCode())
            .append(", author: " + author.toString())
            .toString();
    }
}
