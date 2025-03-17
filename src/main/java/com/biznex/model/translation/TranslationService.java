package com.biznex.model.translation;

import com.biznex.common.constant.TranslationType;
import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.PageableRequestUtil;
import com.biznex.common.request.SearchSpecification;
import com.biznex.common.response.status.TranslationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TranslationService {

    private final TranslationRepository translationRepository;
    private final JdbcTemplate jdbcTemplate;

    public Optional<Translation> findById(Long id) {
        if (id == null) return Optional.empty();
        return translationRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Translation> getList(PageableRequest pageableRequest, Pageable pageable) {
        return translationRepository.findAll(new SearchSpecification<>(pageableRequest.getSearch()), pageable);
    }

    @Transactional(readOnly = true)
    public Page<Translation> getList(PageableRequest pageable) {
        return translationRepository.findAll(new SearchSpecification<>(pageable.getSearch()), PageableRequestUtil.toPageable(pageable));
    }


    public List<TranslationMin> getAllByType(TranslationType type) {
        String sql = """
                SELECT t.id as id,
                       t.name ->> 'uz' as name_uz,
                       t.name ->> 'en' as name_en,
                       t.name ->> 'ru' as name_ru,
                       t.tag as tag_name
                FROM translations t WHERE (t.types)::jsonb ? '#TYPE' AND t.status='ACTIVE'
                """;
        sql = sql.replace("#TYPE", type.name());
        return jdbcTemplate.query(sql, (rs, rowNum) -> new TranslationMin(rs));
    }

    public Translation getById(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(TranslationStatus.NOT_FOUND));
    }

    public Translation save(Translation translation, TranslationPayload payload) {
        translation.setName(payload.getName());
        translation.setTag(payload.getTag());
        translation.setTypes(payload.getTypes());
        translation.setStatus(payload.getStatus());
        return translationRepository.saveAndFlush(translation);
    }

    public Translation create(TranslationPayload payload) {
        if (translationRepository.existsByTag(payload.getTag()))
            throw new ApiException(TranslationStatus.TAG_ALREADY_EXISTS);
        Translation translation = new Translation();
        return save(translation, payload);
    }

    public Translation update(Long id, TranslationPayload payload) {
        if (translationRepository.existsByIdNotAndTag(id, payload.getTag()))
            throw new ApiException(TranslationStatus.TAG_ALREADY_EXISTS);
        Translation translation = getById(id);
        return save(translation, payload);
    }

    public void delete(Long id) {
        Translation translation = getById(id);
        translationRepository.delete(translation);
    }
}
