package UMC.campusNote.page;

import UMC.campusNote.common.s3.S3Provider;
import UMC.campusNote.common.s3.dto.S3UploadRequest;
import UMC.campusNote.mapping.UserLessonNoteRepository;
import UMC.campusNote.note.Note;
import UMC.campusNote.note.NoteRepository;
import UMC.campusNote.user.User;
import UMC.campusNote.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PageService {
   private final PageRepository pageRepository;
   private final NoteRepository noteRepository;
   private final UserLessonNoteRepository userLessonNoteRepository;
   private final UserRepository userRepository;
   private final S3Provider s3Provider;

   Page writePage(PageRequestDTO.PageDto request, MultipartFile file) throws IOException {
      Long userId = userLessonNoteRepository.findUserIdByNoteId(request.getNoteId());
      User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException());
      Note note = noteRepository.findById(request.getNoteId()).orElseThrow(()-> new RuntimeException());
      Page newPage = PageConverter.toPage(request, note);

      String fileDir = "pages/" + request.getNoteId() + request.getPageNum();

      S3UploadRequest s3UploadRequest = new S3UploadRequest(user.getId(), fileDir);
      String imgUrl = s3Provider.fileUpload(file, s3UploadRequest);
      newPage.setHandWritingSVG(imgUrl);
      pageRepository.save(newPage);
      return newPage;
   }

}
