package ex.s3Test.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class FileDto {
    private String title;
    private String url;
    private MultipartFile file;
}