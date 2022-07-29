package bg.softuni.cloudinary.service;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;
    private static final String TEMP_file = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public CloudinaryImage upload(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile(TEMP_file, multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);

        try {
            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = cloudinary
                    .uploader()
                    .upload(tempFile, Map.of());
//                    .upload(tempFile, Map.of("use_filename"));

            //The long string is a funny photo for Error
            String url = uploadResult.getOrDefault(URL, "https://thumbs.dreamstime.com/b/illustration-internet-connection-problem-concept-error-page-not-found-isolated-white-background-funny-blue-dinosaur-230224212.jpg");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

            var result = new CloudinaryImage()
                    .setPublicId(publicId)
                    .setUrl(url);

            return result;
        } finally {
            tempFile.delete();
        }

    }

    public boolean delete(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId, Map.of());
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
