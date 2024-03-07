package server.drawwang.domain.file;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import server.drawwang.global.exception.CustomErrorCode;
import server.drawwang.global.exception.CustomException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileStore {

    public String getFullPath(String fileName) {
        if(fileName.isEmpty()){
            return null;
        }

        return Paths.get(getFilePath(), fileName).toString();
    }

    public String getPartialImagesPath(String fileName) {
        if(fileName.isEmpty()) {
            return "";
        }

        return File.separator + Paths.get("static", "images", fileName);
    }

    public String storeFile(MultipartFile multipartFile)
    {
        if (multipartFile.isEmpty()) {
            return null;
        }

        makeDir();

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        try {
            multipartFile.transferTo(new File(getFullPath(storeFileName)));
        } catch (IOException e) {
            throw new CustomException(CustomErrorCode.FILE_PROCESSING_ERROR);
        }

        return storeFileName;
    }

    private String getFilePath() {
        return Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static", "images").toString();
    }

    private void makeDir() {
        File file = new File(getFilePath());
        if (!file.exists() && !file.mkdirs()) {
            throw new CustomException(CustomErrorCode.FILE_CREATE_ERROR);
        }
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
