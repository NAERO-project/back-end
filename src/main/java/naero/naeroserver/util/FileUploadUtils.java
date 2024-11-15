package naero.naeroserver.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtils {

    private static final Logger log = LoggerFactory.getLogger(FileUploadUtils.class);
    public static String saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String replaceFileName = fileName + "." + FilenameUtils.getExtension(multipartFile.getResource().getFilename());

        try(InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(replaceFileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ex){
            throw new IOException("Could not save file: " + fileName, ex);
        }

        return replaceFileName;
    }

    /**
     * Thumbnail 생성 method
     * @param uploadDir
     * @param replaceFileName
     * @return replaceThumbnailFileName
     * @throws IOException
     */
    public static String saveThumbnailFile(String uploadDir, String replaceFileName) throws IOException {
        Path originalFilePath = Paths.get(uploadDir).resolve(replaceFileName);
        String replaceThumbnailFileName = FilenameUtils.getBaseName(replaceFileName)
                + "_thumbnail." + FilenameUtils.getExtension(replaceFileName);
        Path thumbnailFilePath = Paths.get(uploadDir).resolve(replaceThumbnailFileName);

        try {
            Thumbnails.of(originalFilePath.toFile())
                    .size(160, 90)
                    .toFile(thumbnailFilePath.toFile());
        } catch (IOException e) {
            throw new IOException("Could not save thumbnail: " + replaceThumbnailFileName, e);
        }

        return replaceThumbnailFileName;
    }

    public static boolean deleteFile(String uploadDir, String fileName) {

        boolean result = false;
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)) {
            result = true;
        }
        try {
            Path filePath = uploadPath.resolve(fileName);
            Files.delete(filePath);
            result = true;
        }catch (IOException ex){

            log.error("Could not delete file: {}", fileName, ex);
        }

        return result;
    }

}