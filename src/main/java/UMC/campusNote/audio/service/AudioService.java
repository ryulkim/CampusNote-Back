package UMC.campusNote.audio.service;

import UMC.campusNote.audio.dto.AudioResDto;
import UMC.campusNote.common.s3.dto.S3UploadRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AudioService {
    AudioResDto saveAudio(S3UploadRequest request, Long noteId, MultipartFile audioFile);
}
