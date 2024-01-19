package com.hotel.utils;

import com.hotel.entity.room.Room;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ImageUtils {

    public static final String uploadDirectory = "/var/www/photos/";

    public static String saveRoomImageToFileSystem(MultipartFile imageFile, String roomName) {
        Path path = null;
        String finalPath = null;
        try {
            String fileName = imageFile.getOriginalFilename();
            String directory = uploadDirectory;
            path = Paths.get(directory + roomName + "/" + fileName);
            File imageFolder = new File(directory + roomName);
            imageFolder.mkdir();
            byte[] bytes = imageFile.getBytes();
            if (path.toFile().exists()) {
                Files.write(path, bytes, StandardOpenOption.TRUNCATE_EXISTING);
            } else
                Files.write(path, bytes);
        } catch (IOException e) {
            System.out.println(e);
        }
        finalPath = path.toAbsolutePath().toString();
        return finalPath;
    }
    public static void deleteImage(String directory) {
        try {
            File f = new File(directory);
            FileUtils.deleteDirectory(f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
