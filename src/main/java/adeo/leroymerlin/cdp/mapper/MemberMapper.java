package adeo.leroymerlin.cdp.mapper;

// Condition pure JAVA sinon utilisation de MapStruct

import adeo.leroymerlin.cdp.business.MemberBO;
import adeo.leroymerlin.cdp.dto.MemberDTO;
import adeo.leroymerlin.cdp.entity.Member;

import java.util.Set;

public class MemberMapper {

    public static MemberDTO mapToDTO(MemberBO member) {
        if (member == null) return null;

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setName(member.getName());
        return memberDTO;
    }

    public static MemberBO mapEntityToBO(Member member) {
        if (member == null) return null;

        MemberBO memberBO = new MemberBO();
        memberBO.setId(member.getId());
        memberBO.setName(member.getName());
        return memberBO;
    }

    public static MemberBO mapDTOToBO(MemberDTO member) {
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
        return GenericCollectionMapper.mapSet(members, MemberMapper::mapToDTO);
    }

    public static Set<MemberBO> mapEntitiesToBOs(Set<Member> members) {
        return GenericCollectionMapper.mapSet(members, MemberMapper::mapEntityToBO);
    }

    public static Set<MemberBO> mapDTOsToBOs(Set<MemberDTO> members) {
        return GenericCollectionMapper.mapSet(members, MemberMapper::mapDTOToBO);
    }

    public static Set<Member> mapToEntities(Set<MemberBO> members) {
        return GenericCollectionMapper.mapSet(members, MemberMapper::mapToEntity);
    }
}
