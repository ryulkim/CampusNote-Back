package UMC.campusNote.audio.service;


import UMC.campusNote.audio.dto.AudioResDto;
import UMC.campusNote.audio.entity.Audio;
import UMC.campusNote.audio.repository.AudioRepository;
import UMC.campusNote.common.exception.GeneralException;
import UMC.campusNote.common.s3.S3Provider;
import UMC.campusNote.common.s3.dto.S3UploadRequest;
import UMC.campusNote.note.NoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static UMC.campusNote.common.code.status.ErrorStatus.NOTE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AudioServiceImpl implements AudioService {

    private final NoteRepository noteRepository;
    private final AudioRepository audioRepository;
    private final S3Provider s3Provider;

    @Override
    public AudioResDto saveAudio(S3UploadRequest request, Long noteId, MultipartFile audioFile) {
        String url = s3Provider.multipartFileUpload(audioFile, request);
        Audio audio = Audio.builder()
                .audioFile(url)
                .build();
        audio.setNote(noteRepository.findById(noteId).orElseThrow(() -> new GeneralException(NOTE_NOT_FOUND)));
        return AudioResDto.fromEntity(audioRepository.save(audio).getId()) ;
    }
}
