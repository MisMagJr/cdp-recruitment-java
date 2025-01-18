package adeo.leroymerlin.cdp.dto;

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

    public void setName(String name) {
        this.name = name;
    }
}
