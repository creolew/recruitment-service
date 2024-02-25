package vn.unigap.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.unigap.api.entity.Province;
import vn.unigap.api.service.ProvinceService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/provinces")
public class ProvinceController {
    ProvinceService provinceService;

    @GetMapping("/{id}")
    public String getNameById(@PathVariable(name = "id") int id){
        return provinceService.getNameById(id);
    }
}
