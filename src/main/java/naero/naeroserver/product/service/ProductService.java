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
import org.springframework.data.repository.query.Param;
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

    /* 상품 리스트 전체 조회 (페이징) */
    public int selectProductPage() {
        log.info("[ProductService] selectProductPage 시작");
        List<TblProduct> productList = productRepository.findByProductCheck();

        log.info("[ProductService] selectProductPage 종료");

        return productList.size();
    }

    public Object selectProductListPaging(Criteria cri) {
        log.info("[ProductService] selectProductListPaging() 시작");

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productId").descending());

        Page<TblProduct> result = productRepository.findByProductCheck(paging);
        List<TblProduct> productList = (List<TblProduct>)result.getContent();

        for(int i = 0; i<productList.size(); i++){
            productList.get(i).setProductThumbnail(IMAGE_URL + productList.get(i).getProductThumbnail());
        }

        log.info("[ProductService] selectProductListPaging() 종료");

        return productList.stream().map(tblProduct -> modelMapper.map(tblProduct, ProductDTO.class)).collect(Collectors.toList());
    }

    public int selectProductPageFood() {
        log.info("[ProductService] selectProductPageFood() 시작");

        List<TblProduct> productList = productRepository.findByProductCheckAndSmallCategoryId(2);

        log.info("[ProductService] selectProductPageFood() 종료");

        return productList.size();
    }

    public Object selectProductFoodListPaging(Criteria cri) {
        log.info("[ProductService] selectProductFoodListPaging() 시작");

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productId").descending());

        Page<TblProduct> result = productRepository.findByProductCheckAndSmallCategoryId(2, paging);
        List<TblProduct> productList = (List<TblProduct>)result.getContent();

        for(int i = 0; i<productList.size(); i++){
            productList.get(i).setProductThumbnail(IMAGE_URL + productList.get(i).getProductThumbnail());
        }

        log.info("[ProductService] selectProductFoodListPaging() 종료");

        return productList.stream().map(tblProduct -> modelMapper.map(tblProduct, ProductDTO.class)).collect(Collectors.toList());
    }

    public int selectProductPageBeauty() {
        log.info("[ProductService] selectProductPageBeauty() 시작");

        List<TblProduct> productList = productRepository.findByProductCheckAndSmallCategoryId(3);

        log.info("[ProductService] selectProductPageBeauty() 종료");

        return productList.size();
    }

    public Object selectProductBeautyListPaging(Criteria cri) {
        log.info("[ProductService] selectProductBeautyListPaging() 시작");

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productId").descending());

        Page<TblProduct> result = productRepository.findByProductCheckAndSmallCategoryId(3, paging);
        List<TblProduct> productList = (List<TblProduct>)result.getContent();

        for(int i = 0; i<productList.size(); i++){
            productList.get(i).setProductThumbnail(IMAGE_URL + productList.get(i).getProductThumbnail());
        }

        log.info("[ProductService] selectProductBeautyListPaging() 종료");

        return productList.stream().map(tblProduct -> modelMapper.map(tblProduct, ProductDTO.class)).collect(Collectors.toList());
    }

    public int selectProductPageFashion() {
        log.info("[ProductService] selectProductPageFashion 시작");

        List<TblProduct> productList = productRepository.findByProductCheckAndSmallCategoryId(4);

        log.info("[ProductService] selectProductPageFashion 종료");

        return productList.size();
    }

    public Object selectProductFashionListPaging(Criteria cri) {
        log.info("[ProductService] selectProductFashionListPaging() 시작");

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productId").descending());

        Page<TblProduct> result = productRepository.findByProductCheckAndSmallCategoryId(4, paging);
        List<TblProduct> productList = (List<TblProduct>)result.getContent();

        for(int i = 0; i<productList.size(); i++){
            productList.get(i).setProductThumbnail(IMAGE_URL + productList.get(i).getProductThumbnail());
        }

        log.info("[ProductService] selectProductFashionListPaging() 종료");

        return productList.stream().map(tblProduct -> modelMapper.map(tblProduct, ProductDTO.class)).collect(Collectors.toList());
    }

    /* 상품 리스트 미리보기 조회 */
    public Object selectProductListPreview() {
        log.info("[ProductService] selectProductListPreview() 시작");

        Pageable pageable = PageRequest.of(0, 8); // 최신 상품 순으로 8개만 조회
        List<TblProduct> productListPreview = productRepository.findAllProductWithLimit(pageable);

        for (TblProduct tblProduct : productListPreview) {
            tblProduct.setProductImg(IMAGE_URL + tblProduct.getProductImg());
        }

        log.info("[ProductService] selectProductListPreview() 종료");

        return productListPreview.stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public Object selectProductListAboutFoodPreview() {
        log.info("[ProductService] selectProductListAboutFoodPreview() 시작");

        Pageable pageable = PageRequest.of(0, 8);
        List<TblProduct> productListPreview = productRepository.findByFoodProductWithLimit(2, pageable);

        for (TblProduct tblProduct : productListPreview) {
            tblProduct.setProductImg(IMAGE_URL + tblProduct.getProductImg());
        }

        log.info("[ProductService] selectProductListAboutFoodPreview() 종료");

        return productListPreview.stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public Object selectProductListAboutCosmeticsPreview() {
        log.info("[ProductService] selectProductListAboutCosmeticsPreview() 시작");

        Pageable pageable = PageRequest.of(0, 8);
        List<TblProduct> productListPreview = productRepository.findByFoodProductWithLimit(4, pageable);

        for (TblProduct tblProduct : productListPreview) {
            tblProduct.setProductImg(IMAGE_URL + tblProduct.getProductImg());
        }

        log.info("[ProductService] selectProductListAboutCosmeticsPreview() 종료");

        return productListPreview.stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public Object selectProductListAboutFashionPreview() {
        log.info("[ProductService] selectProductListAboutFashionPreview() 시작");

        Pageable pageable = PageRequest.of(0, 8);
        List<TblProduct> productListPreview = productRepository.findByFoodProductWithLimit(3, pageable);

        for (TblProduct tblProduct : productListPreview) {
            tblProduct.setProductImg(IMAGE_URL + tblProduct.getProductImg());
        }

        log.info("[ProductService] selectProductListAboutFashionPreview() 종료");

        return productListPreview.stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    /* 전체 브랜드 페이지 상품 조회 */
    public Object selectProducerProductListPreview() {
        log.info("[ProductService] selectProducerProductListPreview() 시작");

        Pageable pageable = PageRequest.of(0, 4);
        List<TblProduct> producerProductListPreview = productRepository.findByProductIdWithLimit(1, pageable);

        for (TblProduct tblProduct : producerProductListPreview) {
            tblProduct.setProductImg(IMAGE_URL + tblProduct.getProductImg());
        }

        log.info("[ProductService] selectProducerProductListPreview() 종료");

        return producerProductListPreview.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    /* 판매자 상품 등록 */
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

}
