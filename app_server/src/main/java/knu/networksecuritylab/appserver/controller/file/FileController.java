package knu.networksecuritylab.appserver.controller.file;

import knu.networksecuritylab.appserver.controller.book.dto.BookRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    @PostMapping("")
    public void fileUpload(@RequestPart("bookImages") final List<MultipartFile> bookImages,
                           @RequestParam("book") final BookRegisterRequestDto bookRegisterRequestDto) {
        log.info("bookRegisterRequestDto = {}", bookRegisterRequestDto);
    }
}
