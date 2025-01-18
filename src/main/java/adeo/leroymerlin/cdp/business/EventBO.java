package adeo.leroymerlin.cdp.business;

import java.util.Set;

public class EventBO {
    private Long id;

    private String title;

    private String imgUrl;

    private Set<BandBO> bands;

    private Integer nbStars;

    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<BandBO> getBands() {
        return bands;
    }

    public void setBands(Set<BandBO> bandBOS) {
        this.bands = bandBOS;
    }

    public Integer getNbStars() {
        return nbStars;
    }

    public void setNbStars(Integer nbStars) {
        this.nbStars = nbStars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
