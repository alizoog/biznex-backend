package com.biznex.config;

import com.biznex.common.constant.FileType;
import com.biznex.model.file.File;
import com.biznex.model.file.FileRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Scheduler {

    private final FileRepository fileRepository;

    public Scheduler(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Scheduled(cron = "0 52 3 * * ?")
    public void deleteDraftFiles() {
        List<File> files = fileRepository.findAllByType(FileType.DRAFT);
        fileRepository.deleteAll(files);
    }
}
