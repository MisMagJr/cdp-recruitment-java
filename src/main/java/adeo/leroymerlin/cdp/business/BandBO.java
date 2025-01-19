package adeo.leroymerlin.cdp.business;

import java.util.Set;

public class BandBO {

    private Long id;

    private String name;

    private Set<MemberBO> members;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<MemberBO> getMembers() {
        return members;
    }

    public void setMembers(Set<MemberBO> memberBOS) {
        this.members = memberBOS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasMemberWithNameContainsPattern(String pattern) {
        return members.stream()
                .anyMatch(member -> member.getName() != null
                        && member.getName().toLowerCase().contains(pattern.toLowerCase())); // The readme didn't specify or not to be case sensitive so I decided to make it not
    }
}
