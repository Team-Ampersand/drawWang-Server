package server.drawwang.domain.file.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import server.drawwang.domain.board.entity.BoardEntity;
import server.drawwang.domain.board.repository.BoardRepository;
import server.drawwang.domain.file.FileStore;
import server.drawwang.domain.file.entity.ToFileResponse;
import server.drawwang.domain.file.service.FileService;
import server.drawwang.global.exception.CustomErrorCode;
import server.drawwang.global.exception.CustomException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final BoardRepository boardRepository;
    private final FileStore fileStore;

    @Override
    public List<ToFileResponse> listImage(long[] paramBoardId) {
        try {
            List<ToFileResponse> responses = new ArrayList<>();
            for (long boardId : paramBoardId) {
                BoardEntity boardEntity = boardRepository.findById(boardId)
                        .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_FOUND_ERROR));
                Resource resource = new UrlResource("file:" + fileStore.getFullPath(boardEntity.getImageId()));

                if (resource.exists() && resource.isReadable()) {
                    byte[] imageByte = resource.getInputStream().readAllBytes();
                    responses.add(new ToFileResponse(boardId, imageByte));

                } else {
                    throw new CustomException(CustomErrorCode.IMAGE_NOT_FOUND_ERROR);
                }
            }
            return responses;

        } catch (MalformedURLException e) {
            throw new CustomException(CustomErrorCode.IMAGE_NOT_FOUND_ERROR);
        } catch (IOException e) {
            throw new CustomException(CustomErrorCode.FILE_PROCESSING_ERROR);
        }
    }
}
