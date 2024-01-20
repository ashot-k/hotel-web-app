package com.hotel.utils;

import jakarta.annotation.PostConstruct;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

@Component
public class ImageUtils {

    public static String roomImageDirectory;

    @Value("${room.image.dir}")
    private String roomImageDirectoryCreation;

    @PostConstruct
    public void createFolderIfNotExists() {
        File folder = new File(roomImageDirectoryCreation);
        roomImageDirectory = roomImageDirectoryCreation;
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
        }
    }
    public static byte[] decodeBase64Image(String data){
        String base64Image = data.split(",")[1];
        return Base64.getDecoder().decode(base64Image);
    }

    public static String saveImageToFileSystem(byte[] imageFile, String imageDirectory, String fileName) {
        Path path = null;
        String finalPath = null;
        try {
            path = Paths.get( imageDirectory + "/" + fileName);
            if (path.toFile().exists())
                    Files.write(path, imageFile, StandardOpenOption.TRUNCATE_EXISTING);
                else
                    Files.write(path, imageFile);
        } catch (IOException e) {
            System.out.println(e);
        }
        finalPath = path.toAbsolutePath().toString();
        return finalPath;
    }

    public static boolean rename(String oldFile, String newFile) {
        File file = new File(oldFile);
        File file2 = new File(newFile);
        try {
            if (file2.exists())
                throw new java.io.IOException("file exists");
        }catch (IOException e ){
            System.out.println(e);
        }
        return file.renameTo(file2);
    }

    public static boolean exists(String fileFolder){
        return false;
    }
    public static boolean deleteImage(String directory, String fileName) {
        File f = new File( directory + "/" + fileName);
        if(f.exists())
            return f.delete();
        else
            return false;
    }
}
