package ru.job4j.cinema.service.fileservice;

import ru.job4j.cinema.dto.FileDto;

import java.util.Optional;

public interface FileService {

    Optional<FileDto> getFileById(int id);
}
