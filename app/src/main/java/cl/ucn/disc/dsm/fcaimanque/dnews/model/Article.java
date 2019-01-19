
package cl.ucn.disc.dsm.fcaimanque.dnews.model;


import lombok.Builder;
import lombok.Getter;

@Builder
public final class Article {

    @Getter
    private Source source;
    @Getter
    private String author;
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    private String url;
    @Getter
    private String urlToImage;
    @Getter
    private String publishedAt;
    @Getter
    private String content;


}
