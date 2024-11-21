package naero.naeroserver.product.service;

import naero.naeroserver.common.Criteria;
import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.entity.product.TblCategoryMedium;
import naero.naeroserver.entity.product.TblCategorySmall;
import naero.naeroserver.entity.product.TblOption;
import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.product.dto.*;
import naero.naeroserver.product.repository.*;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ProductSearchRepository productSearchRepository;
    private final CategoryMediumRepository categoryMediumRepository;
    private final CategorySmallRepository categorySmallRepository;
    private final OptionRepository optionRepository;

    @Autowired
    public ProductService(ModelMapper modelMapper, ProductRepository productRepository, ProductSearchRepository productSearchRepository, CategoryMediumRepository categoryMediumRepository, CategorySmallRepository categorySmallRepository, OptionRepository optionRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.productSearchRepository = productSearchRepository;
        this.categoryMediumRepository = categoryMediumRepository;
        this.categorySmallRepository = categorySmallRepository;
        this.optionRepository = optionRepository;
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
        Pageable paging = PageRequest.of(index, count, Sort.by("id").descending());

        Page<TblProduct> result = productRepository.findPagedByProductCheck(paging);
        List<TblProduct> productList = (List<TblProduct>)result.getContent();

        for(int i = 0; i<productList.size(); i++){
            productList.get(i).setProductThumbnail(IMAGE_URL + productList.get(i).getProductThumbnail());
        }

        log.info("[ProductService] selectProductListPaging() 종료");

        return productList.stream().map(tblProduct -> modelMapper.map(tblProduct, ProductDTO.class)).collect(Collectors.toList());
    }

    public int selectProductCategoryList(int mediumId) {
        log.info("[ProductService] selectProductCategoryList 시작");
        List<TblProduct> productList = productRepository.findByProductCheckAndSmallCategory(mediumId);

        log.info("[ProductService] selectProductCategoryList 종료");

        return productList.size();
    }

    public Object selectProductCategoryListPaging(int mediumId, Criteria cri) {
        log.info("[ProductService] selectProductCategoryListPaging() 시작");

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productId").descending());

        Page<TblProduct> result = productRepository.findPagedProductCheckAndSmallCategory(mediumId, paging);
        List<TblProduct> productList = (List<TblProduct>)result.getContent();

        for(int i = 0; i<productList.size(); i++){
            productList.get(i).setProductThumbnail(IMAGE_URL + productList.get(i).getProductThumbnail());
        }

        log.info("[ProductService] selectProductCategoryListPaging() 종료");

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
        List<TblProduct> productListPreview = productRepository.findByFoodProductWithLimit(3, pageable);

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

    /* 브랜드 전체 페이지 상품 조회 (미리보기) */
    public List<TblProduct> selectProducerProductListPreview() {
        log.info("[ProductService] selectProducerProductListPreview() 시작");

        Pageable pageable = PageRequest.of(0, 4);
        List<TblProduct> previewList = productRepository.findByProducerIdWithLimit(2, pageable);

        log.info("[ProductService] selectProducerProductListPreview() 종료");

        return previewList;
    }

    /* 브랜드별 페이지 전체 상품 조회 (페이징) */
    public int selectProducerProductListPage(int producerId) {
        log.info("[ProductService] selectProducerProductPage 시작");

        List<TblProduct> productList = productRepository.findByProductCheckAndId(producerId);

        log.info("[ProductService] selectProducerProductPage 종료");

        return productList.size();
    }

    public Object selectProductListByBrandPaging(int producerId, Criteria cri) {
        log.info("[ProductService] selectProductListByBrandPaging() 시작");

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productId").descending());

        Page<TblProduct> result = productRepository.findByPageProductCheckAndId(producerId, paging);
        List<TblProduct> productList = (List<TblProduct>)result.getContent();

        for(int i = 0; i<productList.size(); i++){
            productList.get(i).setProductThumbnail(IMAGE_URL + productList.get(i).getProductThumbnail());
        }

        log.info("[ProductService] selectProducerProductListPaging() 종료");

        return productList.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    /* 판매자 페이지 전체 상품 조회 (페이징) */
    public Object selectProducerProductListPaging(int producerId, Criteria cri) {
        log.info("[ProductService] selectProducerProductListPaging() 시작");

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productId").descending());

        Page<ProductOptionDTO> result = productRepository.findProductListByProducer(producerId, paging);
        List<ProductOptionDTO> productList = result.getContent();

        for(int i = 0; i<productList.size(); i++){
            productList.get(i).getProduct().setProductThumbnail(IMAGE_URL + productList.get(i).getProduct().getProductThumbnail());
        }

        log.info(productList.toString());
        log.info("[ProductService] selectProducerProductListPaging() 종료");

        return productList;
    }

    /* 브랜드별 페이지 카테고리 상품 조회 (페이징) */
    public int selectProducerProductCategoryListPage(int producerId, int mediumId) {
        log.info("[ProductService] selectProducerProductCategoryListPage 시작");

        List<TblProduct> productList = productRepository.findByProductCheckAndSmallCategoryIdAndProducerId(producerId, mediumId);

        log.info("[ProductService] selectProducerProductCategoryListPage 종료");

        return productList.size();
    }

    public Object selectProducerProductCategoryListPaging(int producerId, int mediumId, Criteria cri) {
        log.info("[ProductService] selectProducerProductCategoryListPaging() 시작");

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productId").descending());

        Page<TblProduct> result = productRepository.findByProductCheckAndSmallCategoryIdAndProducerId(producerId, mediumId, paging);
        List<TblProduct> productList = (List<TblProduct>)result.getContent();

        for(int i = 0 ; i < productList.size() ; i++) {
            productList.get(i).setProductThumbnail(IMAGE_URL + productList.get(i).getProductThumbnail());
        }

        log.info("[ProductService] selectProducerProductCategoryListPaging() 종료");

        return productList.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    /* 상품별 상세 조회 */
    public Object selectProductDetail(int productId) {
        log.info("[ProductService] selectProductDetail() 시작");

        ProductOptionDTO product = productRepository.findByIdAndOption(productId);

        log.info("[ProductService] selectProductDetail() 종료");

        return product;
    }

    @Transactional
    public Object insertProduct(ProductDTO productDTO, MultipartFile productImage) {
        log.info("[ProductService] insertProduct() 시작");
        log.info("[ProductService] productDTO : {}", productDTO);

        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        String replaceThumbnailFileName = null;

        try {
            // 이미지 저장
            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, productImage);
            replaceThumbnailFileName = FileUploadUtils.saveThumbnailFile(IMAGE_DIR, replaceFileName);

            productDTO.setProductImg(replaceFileName);
            productDTO.setProductThumbnail(replaceThumbnailFileName);

            log.info("[ProductService] 등록 이미지명 : {}", replaceFileName);

            // Product 저장
            TblProduct insertProduct = modelMapper.map(productDTO, TblProduct.class);
            TblProduct savedProduct = productRepository.save(insertProduct); // 저장 후 ID 생성

            // Option 저장
            List<OptionDTO> options = productDTO.getOptions();
            if (options != null && !options.isEmpty()) { // 옵션 리스트가 null이 아니고 비어있지 않을 경우
                List<TblOption> optionModel = new ArrayList<>();
                for (OptionDTO option : options) {
                    log.info("옵션 정보: {}", option);
                    TblOption tblOption = new TblOption();
                    tblOption.setProductId(savedProduct.getProductId()); // 연관된 productId 설정
                    tblOption.setOptionDesc(option.getOptionDesc());
                    tblOption.setOptionQuantity(option.getOptionQuantity());
                    tblOption.setAddPrice(option.getAddPrice());
                    optionModel.add(tblOption);
                }

                optionRepository.saveAll(optionModel);
            }

            return "상품 입력 성공";
        } catch (Exception e) {
            log.error("[ProductService] 상품 등록 실패", e);
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName); // 이미지 삭제
            throw new RuntimeException(e);
        }
    }

    /* 판매자 상품 수정 */
    @Transactional
    public Object updateProduct(ProductDTO productDTO, MultipartFile productImage) {
        log.info("[ProductService] updateProduct() Start");
        log.info("[ProductService] productDTO : {}", productDTO);

        String replaceFileName = null;
        int result = 0;

        try {

            /* 설명. update 할 엔티티 조회 */
            TblProduct product = productRepository.findById(productDTO.getProductId()).get();
            String oriImage = product.getProductThumbnail();
            log.info("[updateProduct] oriImage : {}", oriImage);

            /* 설명. update를 위한 엔티티 값 수정 */
            product.setProductName(productDTO.getProductName());
            product.setProductPrice(productDTO.getProductPrice());
            product.setProductDesc(productDTO.getProductDesc());
            product.setProductCheck(productDTO.getProductCheck());
            product.setProductThumbnail(productDTO.getProductThumbnail());

            if(productImage != null){
                String imageName = UUID.randomUUID().toString().replace("-", "");
                replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, productImage);
                log.info("[updateProduct] InsertFileName : {}", replaceFileName);

                product.setProductThumbnail(replaceFileName);	// 새로운 파일 이름으로 update
                log.info("[updateProduct] deleteImage : {}", oriImage);

                boolean isDelete = FileUploadUtils.deleteFile(IMAGE_DIR, oriImage);
                log.info("[update] isDelete : {}", isDelete);
            } else {

                /* 설명. 이미지 변경 없을 경우 */
                product.setProductThumbnail(oriImage);
            }

            result = 1;
        } catch (IOException e) {
            log.info("[updateProduct] Exception!!");
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        log.info("[ProductService] updateProduct End ===================================");
        return (result > 0) ? "상품 업데이트 성공" : "상품 업데이트 실패";
    }

    public Object deleteProduct(ProductDTO productDTO) {
        log.info("[ProductService] deleteProduct() 시작");

        productRepository.deleteById(productDTO.getProductId());

        log.info("[ProductService] deleteProduct() 종료");

        return ResponseEntity.noContent().build();
    }

    /* 상품 검색 기능 */
    public Object selectSearchProductList(String search) {
        log.info("[ProductService] selectSearchProductList() Start");
        log.info("[ProductService] searchValue : {}", search);

        List<TblProduct> productListWithSearchValue = productRepository.findByProductNameContaining(search);

        log.info("[ProductService] productListWithSearchValue : {}", productListWithSearchValue);

        for(int i = 0 ; i < productListWithSearchValue.size() ; i++) {
            productListWithSearchValue.get(i).setProductThumbnail(IMAGE_URL + productListWithSearchValue.get(i).getProductThumbnail());
        }

        log.info("[ProductService] selectSearchProductList() End");

        return productListWithSearchValue.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    public String searchProductPage(Integer page, int size, ProductSearchDTO crit) {
        // 전체 페이지 수 계산 < 동시 반환이 안됨. 따로 호출
        int totalCount = productSearchRepository.getTotalCount(crit);
        int totalPages = (int) Math.ceil((double) totalCount / size);

        return  (page+1)+"/"+totalPages;
    }

    public Object searchProduct(Integer page, int size, ProductSearchDTO crit) {
        List<ProductDTO> result = productSearchRepository.searchProduct(crit, page, size);


        return result;
    }

    /* 상품 카테고리와 연관된 상품 조회 */
    public Object selectProductCategory(int smallId) {
        log.info("[ProductService] selectProductCategory() 시작");

        List<TblProduct> productListPreview = productRepository.findByProductIdAndSmallCategory(smallId);

        for (TblProduct tblProduct : productListPreview) {
            tblProduct.setProductImg(IMAGE_URL + tblProduct.getProductImg());
        }

        log.info("[ProductService] selectProductCategory() 종료");

        return productListPreview.stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    // 선택된 대분류에 해당되는 중분류 조회(판매자 상품 등록에서 필요)
    public Object selectMediumListByLarge(int largeCategory) {

        List<TblCategoryMedium> categoryMedium = categoryMediumRepository.findTblCategoryMediumByLargeCategoryId(largeCategory);

        return categoryMedium.stream().map(medium -> modelMapper.map(medium, CategoryMediumDTO.class))
                .collect(Collectors.toList());
    }

    // 선택된 중분류에 해당되는 소분류 조회(판매자 상품 등록에서 필요)
    public Object selectSmallListByMedium(int mediumCategory) {

        List<TblCategorySmall> categoryMedium = categorySmallRepository.findTblCategorySmallByMediumCategoryId(mediumCategory);

        return categoryMedium.stream().map(small -> modelMapper.map(small, CategorySmallDTO.class))
                .collect(Collectors.toList());
    }
}
