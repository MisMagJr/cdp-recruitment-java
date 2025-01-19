package adeo.leroymerlin.cdp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class EventDTO {
    private Long id;

    private String title;

    private String imgUrl;

    private Set<BandDTO> bands;

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
    @JsonProperty("title")
    public String getTitleWithCount() {
        return title + " [" + (bands != null ? bands.size() : 0) + "]";
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

    public Set<BandDTO> getBands() {
        return bands;
    }

    public void setBands(Set<BandDTO> bandDTOS) {
        this.bands = bandDTOS;
        updateTitleWithBandCount();
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

    private void updateTitleWithBandCount() {
        if (title != null && bands != null) {
            int bandCount = bands.size();
            String expectedSuffix = " [" + bandCount + "]";

            // When getting a PUT request from the FrontEnd, Front wasn't modified to remove the count from the end, so we add check here
            // The only downside to this, is if a client other than the front end decides to update with a title that really include [n] at the end which is unlikely, but it will get deleted
            if (title.endsWith(expectedSuffix)) {
                // Remove the "[n]" suffix from the title if it exists (coming from front of this project)
                title = title.substring(0, title.length() - expectedSuffix.length());
            }
        }
    }
}
