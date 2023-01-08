package ex.s3Test.Controller;

import ex.s3Test.Dto.FileDto;
import ex.s3Test.Service.FileService;
import ex.s3Test.Service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;
    private final FileService fileService;

    @PostMapping("/api/upload")
    public ResponseEntity<FileDto> uploadFile(@RequestPart(value="file",required = false) MultipartFile multipartFile)
            throws IOException {
        FileDto fileDto = new FileDto();
        fileDto.setFile(multipartFile);
        String url = s3Service.uploadFile(fileDto.getFile());
        fileDto.setUrl(url);
        fileService.save(fileDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/delete")
    public ResponseEntity<Void> deleteImage(@RequestParam String filename){
        s3Service.deleteImage(filename);
        return ResponseEntity.ok().build();
    }

}
