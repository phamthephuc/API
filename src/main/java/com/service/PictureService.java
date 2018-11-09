package com.service;


import com.entity.Picture;
import com.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Service
public class PictureService {

    @Autowired
    PictureRepository pictureRepository;
//
//    public List<Picture> findAllPicture(){
//        return (List<Picture>) pictureRepository.findAll();
//    }
//
//    public Optional<Picture> findById(Long id){
//        return pictureRepository.findById(id);
//    }
//
//    public  void createPicture(Picture picture){
//        pictureRepository.save(picture);
//    }
//
//    public void updatePicture(Picture picture){
//        pictureRepository.save(picture);
//    }
//
//    public void deletePicture(Long id){
//        pictureRepository.deleteById(id);
//    }

    private  static String UPLOAD_ROOT = "src/main/resources/static";
    private  final  PictureRepository repository;
    private final ResourceLoader resourceLoader;


    private String locationStorage = "image";

    @Autowired
    private ServletContext servletContext;

    public boolean storeFile(MultipartFile file, Long idLocation) {
        try {
            String realLocation = servletContext.getRealPath(locationStorage);
            File locationStore = new File(realLocation);
            if (!locationStore.exists()) {
                locationStore.mkdirs();
            }
            String fileName = file.getOriginalFilename();
            if (!fileName.contains(".png") && !fileName.contains(".jpg") && !fileName.contains(".gif")
                    && !fileName.contains(".jpeg")) {
                return false;
            }
            File img = new File(realLocation + File.separator + file.getOriginalFilename());
            file.transferTo(img);
            Picture picture = new Picture();
            picture.setImage(file.getOriginalFilename());
            picture.setName(file.getOriginalFilename());
            picture.setIdLocation(idLocation);
            pictureRepository.save(picture);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
//		InputStream imageFolder = UserProfileApiController.class.getResoceAsSturream("static/images/");
//		ClassLoader classLoader = getClass().getClassLoader();
//	    File file = new File(classLoader.getResource("static/images/").getFile(),user.getFile().getOriginalFilename());
//	    System.out.println(file.getAbsolutePath());
//	    user.getFile().transferTo(file);
    }


    @Autowired
    public PictureService(PictureRepository pictureRepository, ResourceLoader resourceLoader){
        this.repository = pictureRepository;
        this.resourceLoader = resourceLoader;
    }

    public Resource findOnePicture(String filename){
        return resourceLoader.getResource("file:" +UPLOAD_ROOT+"/"+filename);
    }

    public void createPicture (MultipartFile file , Long idLocation) throws IOException {
        if (!file.isEmpty()){
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));
            repository.save(new Picture(100, file.getOriginalFilename(), file.getOriginalFilename(), idLocation));
        }
    }

    public  Picture findByFileName(String filename){
        return  repository.findByName(filename);
    }

    public void deletePicture (Long  id) throws IOException {
        final  Picture byName = repository.findById(id).orElse(new Picture(100,"Null","Null", (long) 100));
        repository.delete(byName);
        Files.deleteIfExists(Paths.get(UPLOAD_ROOT, byName.getName()));
    }

    public boolean storeFile1(MultipartFile file) {
        try {
            String realLocation = "/src/resources/images";
            File locationStore = new File(realLocation);
            if (!locationStore.exists()) {
                locationStore.mkdirs();
            }

            String fileName = file.getOriginalFilename();
            if (!fileName.contains(".png") && !fileName.contains(".jpg") && !fileName.contains(".gif")
                    && !fileName.contains(".jpeg")) {
                return false;
            }
            File img = new File(realLocation + File.separator + file.getOriginalFilename());
            file.transferTo(img);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
//		InputStream imageFolder = UserProfileApiController.class.getResourceAsStream("static/images/");
//		ClassLoader classLoader = getClass().getClassLoader();
//	    File file = new File(classLoader.getResource("static/images/").getFile(),user.getFile().getOriginalFilename());
//	    System.out.println(file.getAbsolutePath());
//	    user.getFile().transferTo(file);
    }

    public List<Picture> listPictureOfLocation(Long idLocation){
        return pictureRepository.findByIdLocation(idLocation);
    }



}
