package vn.unigap.api.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import vn.unigap.api.entity.Province;
import vn.unigap.api.exception.ResourceNotFoundException;
import vn.unigap.api.repository.ProvinceRepository;
import vn.unigap.api.service.ProvinceService;

@Service
@AllArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    ProvinceRepository provinceRepository;
    @Override
    public String getNameById(int provinceId) {

        Province province = provinceRepository.findById(provinceId).orElseThrow(
                () -> new ResourceNotFoundException("Province", "id", provinceId)
        );

        return province.getName();
    }

    @Override
    public Boolean checkExistProvince(int id) {
        if(!provinceRepository.existsById(id) ){
            return false;
        }
        return true;
    }
}
