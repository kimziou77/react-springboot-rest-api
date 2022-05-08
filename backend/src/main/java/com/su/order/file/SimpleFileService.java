package com.su.order.file;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.su.order.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SimpleFileService implements FileService {
    private final Storage storage;
    @Value("${bucket}")
    String BUCKET_NAME;
    @Value("${spring.servlet.multipart.location}")
    String LOCAL_SAVE_DIR;

    @Override
    public String saveProduct(ProductDto.Create dto) throws IOException {
        var fileName = saveFileToLocal(dto.getImageMultipart());
        saveFileToGCS(fileName);
        return fileName;
    }


    public String saveFileToLocal(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }
        String originFilename = multipartFile.getOriginalFilename();
        String saveFileName = createSavedFileName(originFilename);
        multipartFile.transferTo(new File(saveFileName));
        return saveFileName;
    }

    public BlobInfo saveFileToGCS(String UPLOAD_NAME) throws IOException {
        BlobInfo blobInfo =storage.create(
                BlobInfo.newBuilder(BUCKET_NAME, UPLOAD_NAME)
                        .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.READER))))
                        .build(),
                new FileInputStream(LOCAL_SAVE_DIR +"\\" + UPLOAD_NAME));
        return blobInfo;
    }

    private String createSavedFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
