package br.com.tv.domain.services.impl;

import br.com.base.authentication.api.controllers.user.v1.models.enums.ProfileType;
import br.com.base.authentication.domain.models.entities.UserEntity;
import br.com.base.authentication.domain.repositories.UserRepository;
import br.com.base.shared.exceptions.BusinessException;
import br.com.base.shared.exceptions.EntityNotFoundException;
import br.com.base.shared.utils.StringUtil;
import br.com.tv.controllers.presentation.v1.models.DTOs.GetPresentationResponseDTO;
import br.com.tv.controllers.tv.v1.models.DTOs.*;
import br.com.tv.domain.models.entities.PresentationEntity;
import br.com.tv.domain.models.entities.TvEntity;
import br.com.tv.domain.repositories.PresentationLinkTvRepository;
import br.com.tv.domain.repositories.PresentationRepository;
import br.com.tv.domain.repositories.TvRepository;
import br.com.tv.domain.services.PresentationService;
import br.com.tv.domain.services.TvService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TvServiceImpl implements TvService {

    private TvRepository tvRepository;
    private UserRepository userRepository;
    private PresentationLinkTvRepository presentationLinkTvRepository;
    private PresentationService presentationService;

    @Override
    public GetTvResponseDTO search(GetTvRequestDTO request) {
        var pageable = request.buildPageable();
        var page = tvRepository.search(StringUtil.like(request.getSearch()), pageable);

        return parseToTvsPageableResultDTO(page);
    }

    @Override
    @Transactional
    public GetPresentationByTvResponseDTO getPresentationByTv() {
        UserEntity user = userRepository.findById(getLoggedUser())
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado!"));
        user.getGroups().forEach(g -> {
            if (!g.getGroup().getName().equals(ProfileType.TV.name()))
                throw new BusinessException("Usuario sem o perfil necessario!");
        });

        TvEntity tv = tvRepository.findByUserId(user.getId());
        UUID presentationId = presentationLinkTvRepository.findPresentationIdByTvId(tv.getId());

        GetPresentationResponseDTO response = presentationService.getById(presentationId);

        return GetPresentationByTvResponseDTO.builder()
                .id(response.id())
                .type(response.type())
                .createdAt(response.createdAt())
                .name(response.name())
                .files(response.files())
                .build();
    }

    @Override
    @Transactional
    public List<GetTvRecordsDTO> getTvWithoutPresentation() {
        List<TvEntity> tvs = tvRepository.findAllTvsWithoutPresentations();

        return tvs.stream().map(tv -> {
            return GetTvRecordsDTO.builder()
                    .id(tv.getId())
                    .campus(tv.getCampus())
                    .name(tv.getName())
                    .createdAt(tv.getCreatedAt())
                    .user(UserAssociatedByTvRecordDTO.builder()
                            .id(tv.getUser().getId())
                            .name(tv.getUser().getName())
                            .build())
                    .build();
        }).toList();
    }

    @Transactional
    private GetTvResponseDTO parseToTvsPageableResultDTO(Page<TvEntity> result) {
        List<GetTvRecordsDTO> content = result.getContent().stream()
                .map(tv -> {
                        return GetTvRecordsDTO.builder()
                                .id(tv.getId())
                                .user(UserAssociatedByTvRecordDTO.builder()
                                        .id(tv.getUser().getId())
                                        .name(tv.getUser().getName())
                                        .build()
                                )
                                .createdAt(tv.getCreatedAt())
                                .name(tv.getName())
                                .campus(tv.getCampus())
                                .build();
                }).toList();
        var page = new PageImpl<>(content, result.getPageable(), result.getTotalElements());
        return new GetTvResponseDTO(page);
    }

    @SuppressWarnings("unchecked")
    private UUID getLoggedUser() {
        return ((Optional<UserEntity>) SecurityContextHolder.getContext().getAuthentication().getDetails()).get().getId();
    }
}
