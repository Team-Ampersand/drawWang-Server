package server.drawwang.domain.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import server.drawwang.global.exception.CustomErrorCode;
import server.drawwang.global.exception.CustomException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileStore {

    public String getFullPath(String fileName) {
        if(fileName.isEmpty()){
            return null;
        }

        return System.getProperty("user.dir") + File.separator + "/src/main/resources/images/" + fileName;
    }

    public String getPartialImagesPath(String fileName) {
        if(fileName.isEmpty()) {
            return "";
        }

        return "/images/" + fileName;
    }

    public String storeFile(MultipartFile multipartFile)
    {
        if (multipartFile.isEmpty()) {
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);

        try {
            multipartFile.transferTo(new File(getFullPath(storeFileName)));
        } catch (IOException e) {
            throw new CustomException(CustomErrorCode.FILE_PROCESSING_ERROR);
        }
        return storeFileName;
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
