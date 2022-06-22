package com.example.week4.service;
import com.example.week4.dao.AdvertisementRepository;
import com.example.week4.entity.Advertisement;
import com.example.week4.rabbit.producer.AdvertisementProducer;
import com.example.week4.rabbit.producer.UserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class AdvertisementServiceImp implements AdvertisementService{

    @Autowired
    private AdvertisementProducer advertisementProducer;
    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Override
    public boolean createAdvertisement() {
        try {
            Advertisement advertisement = new Advertisement();
            for(int i = 0; i < 50; i++){
                advertisementProducer.sendToQueue(advertisement);
            }
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public List<Advertisement> getAll() {
        return advertisementRepository.findAll();
    }

    @Override
    public List<Advertisement> findByCreatedAt(Date date) {
        return advertisementRepository.findByCreatedAt(date);
    }

    @Override
    public List<Advertisement> findByPrice(Long min, Long max) {
        return advertisementRepository.findByPrice(min, max);
    }

    @Override
    public List<Advertisement> findByTitleOrDetailedMessage(String text) {
        return advertisementRepository.findByTitleOrDetailedMessage(text);
    }
}
