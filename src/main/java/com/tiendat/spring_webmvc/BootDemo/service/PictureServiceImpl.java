package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.Picture;
import com.tiendat.spring_webmvc.BootDemo.respository.PictureRespository;

@Service("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRespository pictureRespository;

    @Override
    public List<Picture> findAllPicture() {
        return this.pictureRespository.findAll();
    }

    @Override
    public List<Picture> findPictureByEventId(int eventId) {
        return this.pictureRespository.findByEventId(eventId);
    }

    @Override
    public Picture uploadPicture(Picture newPicture) {
        return this.pictureRespository.save(newPicture);
    }

    @Override
    public int deletePicture(int pictureId) {
        return this.pictureRespository.deleteByPictureId(pictureId);
    }

    @Override
    public Picture updatePicture(Picture picture) {
        return this.pictureRespository.save(picture);
    }

    @Override
    public List<Picture> findPublicPicture() {
        return this.pictureRespository.findByNotPublicIsFalse();
    }

}
