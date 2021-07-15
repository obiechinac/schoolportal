package com.springstatesman.devapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by HP on 4/30/2021.
 */
@Setter
@Getter
public class EditPictureModel {

    private long id;
    private String picturePath;
    private MultipartFile fileBody;
}
