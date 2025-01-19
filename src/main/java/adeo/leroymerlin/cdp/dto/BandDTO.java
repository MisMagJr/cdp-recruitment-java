package adeo.leroymerlin.cdp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public class BandDTO {

    private Long id;

    private String name;

    private Set<MemberDTO> members;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<MemberDTO> getMembers() {
        return members;
    }

    public void setMembers(Set<MemberDTO> memberDTOS) {
        this.members = memberDTOS;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getNameWithCount() {
        return name + " [" + (members != null ? members.size() : 0) + "]";
    }

    public void setName(String name) {
        this.name = name;
    }
}
