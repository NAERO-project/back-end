package naero.naeroserver.product.service;

import naero.naeroserver.common.Criteria;
import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.product.dto.ProductDTO;
import naero.naeroserver.product.repository.ProductRepository;
import naero.naeroserver.util.FileUploadUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    /* 설명. 이미지 파일 저장 경로와 응답용 URL (WebConfig 설정파일 참고) */
    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;

    /* 상품 등록 */
    @Transactional
    public Object insertProduct(ProductDTO productDTO, MultipartFile productImage) {
        log.info("[ProductService] insertProduct() 시작");
        log.info("[ProductService] productDTO : {}", productDTO);

        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        String replaceThumbnailFileName = null;
        int result = 0;

        try {
            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, productImage);
            replaceThumbnailFileName = FileUploadUtils.saveThumbnailFile(IMAGE_DIR, replaceFileName);

            productDTO.setProductImg(replaceFileName);
            productDTO.setProductThumbnail(replaceThumbnailFileName);

            log.info("[ProductService] 등록 이미지명 : {}", replaceFileName);

            TblProduct insertProduct = modelMapper.map(productDTO, TblProduct.class);

            productRepository.save(insertProduct);

            result = 1;
        } catch (Exception e) {
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }

        return "상품 입력 성공";
    }

    /* 상품 리스트 전체 조회 (페이징) */
    public int selectProductPage() {
        log.info("[ProductService] selectProductPage 시작");

        List<TblProduct> productList = productRepository.findByProductCheck("Y");

        log.info("[ProductService] selectProductPage 종료");

        return productList.size();
    }

    /* 상품 리스트 전체 조회 (페이징) */
    public Object selectProductListPaging(Criteria cri) {
        log.info("[ProductService] selectProductListPaging() 시작");

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productId").descending());

        Page<TblProduct> result = productRepository.findByProductCheck("Y", paging);
        List<TblProduct> productList = (List<TblProduct>)result.getContent();

        for(int i = 0; i<productList.size(); i++){
            productList.get(i).setProductThumbnail(IMAGE_URL + productList.get(i).getProductThumbnail());
        }

        log.info("[ProductService] selectProductListPaging() 종료");

        return productList.stream().map(tblProduct -> modelMapper.map(tblProduct, ProductDTO.class)).collect(Collectors.toList());
    }

    /* 식품, 음료 리스트 전체 조회 (페이징) */
    public int selectProductPageFood() {
        log.info("[ProductService] selectProductPageFood 시작");

        List<TblProduct> productList = productRepository.findByProductCheckAndSmallCategoryIdOrSmallCategoryId("Y", 2, 3);

        log.info("[ProductService] selectProductPageFood 종료");

        return productList.size();
    }

    /* 식품, 음료 리스트 전체 조회 (페이징) */
    public Object selectProductFoodListPaging(Criteria cri) {
        log.info("[ProductService] selectProductFoodListPaging() 시작");

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productId").descending());

        Page<TblProduct> result = productRepository.findByProductCheckAndSmallCategoryIdOrSmallCategoryId("Y", 1,2, paging);
        List<TblProduct> productList = (List<TblProduct>)result.getContent();

        for(int i = 0; i<productList.size(); i++){
            productList.get(i).setProductThumbnail(IMAGE_URL + productList.get(i).getProductThumbnail());
        }

        log.info("[ProductService] selectProductFoodListPaging() 종료");

        return productList.stream().map(tblProduct -> modelMapper.map(tblProduct, ProductDTO.class)).collect(Collectors.toList());
    }

    /* 건강, 뷰티 리스트 전체 조회 (페이징) */
    public int selectProductPageHealth() {
        log.info("[ProductService] selectProductPageHealth 시작");

        List<TblProduct> productList = productRepository.findByProductCheckAndSmallCategoryIdOrSmallCategoryId("Y", 3, 4);

        log.info("[ProductService] selectProductPageHealth 종료");

        return productList.size();
    }

    /* 건강, 뷰티 리스트 전체 조회 (페이징) */
    public Object selectProductHealthListPaging(Criteria cri) {
        log.info("[ProductService] selectProductHealthListPaging() 시작");

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productId").descending());

        Page<TblProduct> result = productRepository.findByProductCheckAndSmallCategoryIdOrSmallCategoryId("Y", 3,4, paging);
        List<TblProduct> productList = (List<TblProduct>)result.getContent();

        for(int i = 0; i<productList.size(); i++){
            productList.get(i).setProductThumbnail(IMAGE_URL + productList.get(i).getProductThumbnail());
        }

        log.info("[ProductService] selectProductHealthListPaging() 종료");

        return productList.stream().map(tblProduct -> modelMapper.map(tblProduct, ProductDTO.class)).collect(Collectors.toList());
    }

    /* 의류 리스트 전체 조회 (페이징) */
    public int selectProductPageApparel() {
        log.info("[ProductService] selectProductPageApparel 시작");

        List<TblProduct> productList = productRepository.findByProductCheckAndSmallCategoryId("Y", 5);

        log.info("[ProductService] selectProductPageApparel 종료");

        return productList.size();
    }

    /* 의류 리스트 전체 조회 (페이징) */
    public Object selectProductApparelListPaging(Criteria cri) {
        log.info("[ProductService] selectProductApparelListPaging() 시작");

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productId").descending());

        Page<TblProduct> result = productRepository.findByProductCheckAndSmallCategoryId("Y", 5, paging);
        List<TblProduct> productList = (List<TblProduct>)result.getContent();

        for(int i = 0; i<productList.size(); i++){
            productList.get(i).setProductThumbnail(IMAGE_URL + productList.get(i).getProductThumbnail());
        }

        log.info("[ProductService] selectProductApparelListPaging() 종료");

        return productList.stream().map(tblProduct -> modelMapper.map(tblProduct, ProductDTO.class)).collect(Collectors.toList());
    }
}
