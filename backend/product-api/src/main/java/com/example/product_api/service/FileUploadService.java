package com.example.product_api.service;
import com.example.product_api.entity.Product;
import com.example.product_api.repository.ProductRepo;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.NoSuchElementException;

@Service
public class FileUploadService {
    @Autowired
    private  ProductRepo productRepo;
    private Path fileStorageLocation;

    //	@Value("${file.upload.base-path}")
    private final String basePath = "D:\\user-api\\user-api\\src\\main\\resources\\";

    public FileUploadService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public String storeFile(File file, int id, String pathType) {

        // create uploaded path
        this.fileStorageLocation = Paths.get(basePath + pathType).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.",
                    ex);
        }

        String fileName = StringUtils.cleanPath(id + "-" + file.getName());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            InputStream targetStream = new FileInputStream(file);
            Files.copy(targetStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);

            updateImagePath(id, pathType, pathType + "/" + fileName);

            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    private void updateImagePath(long id, String pathType, String imagePath) {

        if (pathType.contains("users")) {
            // update author image path
            Product product = productRepo.findById(id).orElseThrow(() -> new NoSuchElementException("product not found"));
            product.setImage_url(imagePath);
            productRepo.save(product);
        }

    }


    public File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            System.out.print("Error converting the multi-part file to file= "+ ex.getMessage());
        }
        return file;
    }

}
