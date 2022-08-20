package bg.softuni.cloudinary.model.binding;

import org.springframework.web.multipart.MultipartFile;

public class PictureBindingModel {
    private String title;
    private MultipartFile picture; //for uploading files - from Spring framework

    public PictureBindingModel() {
    }

    public String getTitle() {
        return title;
    }

    public PictureBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public PictureBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
