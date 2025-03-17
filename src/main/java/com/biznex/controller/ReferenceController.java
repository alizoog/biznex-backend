package com.biznex.controller;

import com.biznex.common.constant.TranslationType;
import com.biznex.model.file.File;
import com.biznex.model.file.FileService;
import com.biznex.model.translation.TranslationService;
import com.biznex.utils.WebConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(WebConstants.BASE_URL_WITH_VERSION + "/references")
public class ReferenceController {

    private final FileService fileService;
    private final TranslationService translationService;


    @GetMapping("/download/{uuid}")
    public ResponseEntity<Resource> download(@PathVariable UUID uuid) {
        File file = fileService.getByUuid(uuid);
        return fileService.download(file);
    }

    @GetMapping("/translations/{type}/{lang}")
    public Map<String, String> getTranslations(@PathVariable TranslationType type, @PathVariable String lang) {
        HashMap<String, String> response = new HashMap<>();
        translationService.getAllByType(type).forEach(translation -> response.put(translation.getTag(), translation.getName().getByLang(lang)));
        return response;
    }
}
