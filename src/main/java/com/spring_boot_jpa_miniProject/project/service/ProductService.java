package com.spring_boot_jpa_miniProject.project.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring_boot_jpa_miniProject.project.dto.ProductDTO;
import com.spring_boot_jpa_miniProject.project.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository prdRepo;
	
	public List<ProductDTO> listAllProducts() {
		return prdRepo.findAll();
	}
	
	public ProductDTO getProductDetail(Long prdNo) {
		// findById는 Optional<T>을 반환하므로, 
		// orElse(null)로 해당 객체가 없으면 null을 반환하도록 처리
		return prdRepo.findById(prdNo).orElse(null);
	}
	
    public List<ProductDTO> listProductsByCategory(String prdCategory) {
        return prdRepo.findByPrdCategory(prdCategory);
    }
	
	// 상품 정보 및 파일 업로드
	public void addProduct(ProductDTO product, MultipartFile file) throws IOException{
		// 1. 이미지 파일 저장
		if (file != null && !file.isEmpty()) {
			// 저장할 경로 설정
			String savePath = "C:/Users/Web-dev/workspaceBoot/prd_images/";
			
			// 파일 이름이 중복되지 않도록 UUID를 사용하여 고유한 파일명 생성
			String originalFileName = file.getOriginalFilename();
			String uuid = UUID.randomUUID().toString();
			String savedFileName = uuid + "_" +originalFileName;
			
			// 파일 객체 생성 및 서버에 저장
			File dest = new File(savePath, savedFileName);
			file.transferTo(dest);
			
			// 2. DB에 저장할 파일명을 DTO에 설정
			product.setPrdImg(savedFileName);
		}	
		// 3. 상품 정보 DB에 저장
		prdRepo.save(product);
	}
	
	// 상품 정보 수정
	public void updateProduct(ProductDTO product, MultipartFile file) throws IOException {
		// 새 이미지 파일이 업로드되었는지 확인
		if (file != null && !file.isEmpty()) {
			// 이미지 파일 저장 로직은 addProduct와 동일
			String savePath = "C:/Users/Web-dev/workspaceBoot/prd_images/";
			
            String originalFileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String savedFileName = uuid + "_" + originalFileName;
            
            File dest = new File(savePath, savedFileName);
            file.transferTo(dest);
            
            product.setPrdImg(savedFileName);
		} else {
			// 새 파일이 없으면 기존 이미지 정보 그대로 사용
			// 폼에서 전성되지 않은 prdImg 값을 DB에서 다시 조회하여 설정
			ProductDTO originalProduct = getProductDetail(product.getPrdNo());
			product.setPrdImg(originalProduct.getPrdImg());
		}
		
		// JPA의 save는 동일한 PK가 있으면 UPDATE, 없으면 INSERT를 자동으로 수행
		prdRepo.save(product);
	}
	
	// 상품 정보 삭제
	public void deleteProduct(Long prdNo) {
		prdRepo.deleteById(prdNo);
	}
	
	// 카테고리별 상품 조회
	public List<ProductDTO> listProductByCategory(String prdCategory) {
		return prdRepo.findByPrdCategory(prdCategory);
	}
	
	// 상품 검색 기능
    public List<ProductDTO> productSearch(String keyword) {
        return prdRepo.findByPrdNameContaining(keyword);
    }
}
