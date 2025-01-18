package adeo.leroymerlin.cdp.mapper;

// Condition pure JAVA sinon utilisation de MapStruct

import adeo.leroymerlin.cdp.business.BandBO;
import adeo.leroymerlin.cdp.dto.BandDTO;
import adeo.leroymerlin.cdp.entity.Band;

import java.util.Set;
import java.util.stream.Collectors;

public class BandMapper {

    public static BandDTO mapToDTO(BandBO band) {
        if (band == null) return null;

        BandDTO bandDTO = new BandDTO();
        bandDTO.setId(band.getId());
        bandDTO.setName(band.getName());
        bandDTO.setMembers(MemberMapper.mapToDTOs(band.getMembers()));
        return bandDTO;
    }

    public static BandBO mapEntityToBO(Band band) {
        if (band == null) return null;

        BandBO bandBO = new BandBO();
        bandBO.setId(band.getId());
        bandBO.setName(band.getName());
        bandBO.setMembers(MemberMapper.mapEntitiesToBOs(band.getMembers()));
        return bandBO;
    }

    public static BandBO mapDTOToBO(BandDTO band) {
        if (band == null) return null;

        BandBO bandBO = new BandBO();
        bandBO.setId(band.getId());
        bandBO.setName(band.getName());
        bandBO.setMembers(MemberMapper.mapDTOsToBOs(band.getMembers()));
        return bandBO;
    }

    public static Band mapToEntity(BandBO bandBO) {
        if (bandBO == null) return null;

        Band band = new Band();
        band.setId(bandBO.getId());
        band.setName(bandBO.getName());
        band.setMembers(MemberMapper.mapToEntities(bandBO.getMembers()));
        return band;
    }

    public static Set<BandDTO> mapToDTOs(Set<BandBO> bands) {
        if (bands == null || bands.isEmpty()) {
            return Set.of();
        }
        return bands.stream()
                .map(BandMapper::mapToDTO)
                .collect(Collectors.toSet());
    }

    public static Set<BandBO> mapEntitiesToBOs(Set<Band> bands) {
        if (bands == null || bands.isEmpty()) {
            return Set.of();
        }
        return bands.stream()
                .map(BandMapper::mapEntityToBO)
                .collect(Collectors.toSet());
    }

    public static Set<BandBO> mapDTOsToBOs(Set<BandDTO> bands) {
        if (bands == null || bands.isEmpty()) {
            return Set.of();
        }
        return bands.stream()
                .map(BandMapper::mapDTOToBO)
                .collect(Collectors.toSet());
    }

    public static Set<Band> mapToEntities(Set<BandBO> bands) {
        if (bands == null || bands.isEmpty()) {
            return Set.of();
        }
        return bands.stream()
                .map(BandMapper::mapToEntity)
                .collect(Collectors.toSet());
    }
}
