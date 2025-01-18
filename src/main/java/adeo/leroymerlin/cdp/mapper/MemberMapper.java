package adeo.leroymerlin.cdp.mapper;

// Condition pure JAVA sinon utilisation de MapStruct

import adeo.leroymerlin.cdp.business.MemberBO;
import adeo.leroymerlin.cdp.dto.MemberDTO;
import adeo.leroymerlin.cdp.entity.Member;

import java.util.Set;
import java.util.stream.Collectors;

public class MemberMapper {

    public static MemberDTO mapToDTO(MemberBO member) {
        if (member == null) return null;

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setName(member.getName());
        return memberDTO;
    }

    public static MemberBO mapToBO(Member member) {
        if (member == null) return null;

        MemberBO memberBO = new MemberBO();
        memberBO.setId(member.getId());
        memberBO.setName(member.getName());
        return memberBO;
    }

    public static Member mapToEntity(MemberBO memberBO) {
        if (memberBO == null) return null;

        Member member = new Member();
        member.setId(member.getId());
        member.setName(member.getName());
        return member;
    }

    public static Set<MemberDTO> mapToDTOs(Set<MemberBO> members) {
        if (members == null || members.isEmpty()) {
            return Set.of();
        }
        return members.stream()
                .map(MemberMapper::mapToDTO)
                .collect(Collectors.toSet());
    }

    public static Set<MemberBO> mapToBOs(Set<Member> members) {
        if (members == null || members.isEmpty()) {
            return Set.of();
        }
        return members.stream()
                .map(MemberMapper::mapToBO)
                .collect(Collectors.toSet());
    }

    public static Set<Member> mapToEntities(Set<MemberBO> members) {
        if (members == null || members.isEmpty()) {
            return Set.of();
        }
        return members.stream()
                .map(MemberMapper::mapToEntity)
                .collect(Collectors.toSet());
    }
}
