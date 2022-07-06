package ace.ucv.ro.orientationandorganizationapp.service;

import ace.ucv.ro.orientationandorganizationapp.entity.Image;
import ace.ucv.ro.orientationandorganizationapp.repository.ImageRepository;
import ace.ucv.ro.orientationandorganizationapp.utils.ImageUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public void saveFile(MultipartFile file)  {
        try {
            imageRepository.save(Image.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .image(ImageUtils.compressImage(file.getBytes())).build());
        } catch (IOException e) {
            //TODO: arunca o exceptie corespunzatoare ....
            e.printStackTrace();
        }
    }
}
