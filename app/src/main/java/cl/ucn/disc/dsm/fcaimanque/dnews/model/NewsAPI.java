
package cl.ucn.disc.dsm.fcaimanque.dnews.model;

import java.util.List;
import lombok.Getter;

public class NewsAPI {
    @Getter
    private String status;
    @Getter
    private Integer totalResults;
    @Getter
    private List<Article> articles = null;

}
