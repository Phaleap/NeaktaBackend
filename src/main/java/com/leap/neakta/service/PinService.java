package com.leap.neakta.service;


import com.leap.neakta.dto.PinRequest;
import com.leap.neakta.dto.PinResponse;
import com.leap.neakta.entity.Category;
import com.leap.neakta.entity.Pin;
import com.leap.neakta.entity.Province;
import com.leap.neakta.entity.User;
import com.leap.neakta.repository.CategoryRepository;
import com.leap.neakta.repository.PinRepository;
import com.leap.neakta.repository.ProvinceRepository;
import com.leap.neakta.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PinService {
    private final PinRepository pinRepository;
    private final ProvinceRepository provinceRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public PinResponse createPin(PinRequest request){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Province province = provinceRepository.findById(request.getProvinceId()).orElseThrow(() -> new RuntimeException("Province not found"));
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

        Pin pin = new Pin();
        pin.setUser(user);
        pin.setProvince(province);
        pin.setCategory(category);
        pin.setTitle(request.getTitle());
        pin.setStory(request.getStory());
        pin.setAddress(request.getAddress());
        pin.setLat(request.getLat());
        pin.setLng(request.getLng());

        return toResponse(pinRepository.save(pin));
    }
    public List<PinResponse> getAllPins(){
        return pinRepository.findTopPins().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public PinResponse getPinById(UUID id){
        Pin pin = pinRepository.findById(id).orElseThrow(() -> new RuntimeException("Pin not found"));
        return toResponse(pin);
    }
    public List<PinResponse> getPinsByProvince(Integer provinceId){
        return pinRepository.findByProvincedId(provinceId).stream().map(this::toResponse).collect(Collectors.toList());
    }
    public PinResponse updatePin(UUID id, PinRequest request){
        Pin pin = pinRepository.findById(id).orElseThrow(() -> new RuntimeException("Pin not found"));

        pin.setTitle(request.getTitle());
        pin.setStory(request.getStory());
        pin.setAddress(request.getAddress());
        pin.setLat(request.getLat());
        pin.setLng(request.getLng());

        return toResponse(pinRepository.save(pin));
    }
    public void deletePin(UUID id){
        pinRepository.deleteById(id);
    }

    private PinResponse toResponse(Pin pin){
        PinResponse res = new PinResponse();
        res.setId(pin.getId());
        res.setTitle(pin.getTitle());
        res.setStory(pin.getStory());
        res.setAddress(pin.getAddress());
        res.setLat(pin.getLat());
        res.setLng(pin.getLng());
        res.setScore(pin.getScore());
        res.setUpvoteCount(pin.getUpvoteCount());
        res.setStatus(pin.getStatus());
        res.setVerified(pin.isVerified());
        res.setCreatedAt(pin.getCreatedAt());
        res.setAuthorUsername(pin.getUser().getUsername());
        res.setProvinceName(pin.getProvince().getNameEn());
        res.setCategoryName(pin.getCategory().getNameEn());
        res.setCategoryIcon(pin.getCategory().getIcon());
        res.setCategoryColor(pin.getCategory().getColorHex());
        return res;
    }

}
