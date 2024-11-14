package naero.naeroserver.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.Criteria;
import naero.naeroserver.common.PageDTO;
import naero.naeroserver.common.PagingResponseDTO;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.product.dto.ProductDTO;
import naero.naeroserver.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/*")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /* 상품 리스트 전체 조회 (페이징) */
    @Operation(summary = "상품 리스트 전체 조회 (페이징)", description = "상품 조회 및 페이징 처리 진행", tags = { "ProductController" })
    @GetMapping("/products/more")
    public ResponseEntity<ResponseDTO> selectProductList(
            @RequestParam(name = "offset", defaultValue = "1") String offset){

        log.info("[ProductController] selectProductList 상품 리스트 전체 조회(페이징) : " + offset);

        int total = productService.selectProductPage();

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProductListPaging(cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    @Operation(summary = "식품과 음료 리스트 조회 요청", description = "식품과 음료 카테고리에 해당하는 상품 리스트 조회 및 페이징 처리가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/more/food")
    public ResponseEntity<ResponseDTO> selectProductListAboutFood(
            @RequestParam(name = "offset", defaultValue = "1") String offset) {

        log.info("[ProductController] selectProductList 식품과 음식 리스트 전체 조회(페이징) : " + offset);

        int foodTotal = productService.selectProductPageFood();

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProductFoodListPaging(cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, foodTotal));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    @Operation(summary = "건강과 뷰티 리스트 조회 요청", description = "건강과 뷰티 카테고리에 해당하는 상품 리스트 조회 및 페이징 처리가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/more/beauty")
    public ResponseEntity<ResponseDTO> selectProductListAboutBeauty(
            @RequestParam(name = "offset", defaultValue = "1") String offset) {

        log.info("[ProductController] selectProductListAboutBeauty 건강과 뷰티 리스트 전체 조회(페이징) : " + offset);

        int healthTotal = productService.selectProductPageBeauty();

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProductBeautyListPaging(cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, healthTotal));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    @Operation(summary = "의류 리스트 조회 요청", description = "의류 카테고리에 해당하는 상품 리스트 조회 및 페이징 처리가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/more/fashion")
    public ResponseEntity<ResponseDTO> selectProductListAboutFashion (
            @RequestParam(name = "offset", defaultValue = "1") String offset) {

        log.info("[ProductController] selectProductListAboutFashion 의류 리스트 전체 조회(페이징) : " + offset);

        int apparelTotal = productService.selectProductPageFashion();

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProductFashionListPaging(cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, apparelTotal));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 상품 리스트 미리보기 조회 */
    @Operation(summary = "전체 상품 리스트 미리보기 조회 요청", description = "전체 카테고리에 해당하는 상품 리스트 미리보기 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/preview")
    public ResponseEntity<ResponseDTO> selectProductListPreview() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListPreview()));
    }

    @Operation(summary = "식품 상품 리스트 미리보기 조회 요청", description = "식품 카테고리에 해당하는 상품 리스트 미리보기 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/preview/food")
    public ResponseEntity<ResponseDTO> selectProductListAboutFoodPreview() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutFoodPreview()));
    }

    @Operation(summary = "건강&뷰티 상품 리스트 미리보기 조회 요청", description = "건강&뷰티 카테고리에 해당하는 상품 리스트 미리보기 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/preview/beauty")
    public ResponseEntity<ResponseDTO> selectProductListAboutCosmeticsPreview() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutCosmeticsPreview()));
    }

    @Operation(summary = "의류 상품 리스트 미리보기 조회 요청", description = "의류 카테고리에 해당하는 상품 리스트 미리보기 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/preview/fashion")
    public ResponseEntity<ResponseDTO> selectProductListAboutFashionPreview() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutFashionPreview()));
    }

    /* 전체 브랜드 페이지 상품 조회 */
    @Operation(summary = "전체 브랜드 상품 리스트 미리보기 조회 요청", description = "브랜드별 상품 리스트 미리보기 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/producer/preview")
    public ResponseEntity<ResponseDTO> selectProducerProductListPreview() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProducerProductListPreview()));
    }

    /* 브랜드별 페이지 전체 상품 조회 (페이징) */
    @Operation(summary = "브랜드별 페이지 상품 리스트 전체 조회 (페이징)", description = "브랜드별 상품 조회 및 페이징 처리 진행", tags = { "ProductController" })
    @GetMapping("/products/producer")
    public ResponseEntity<ResponseDTO> selectProducerProductListPage(
            @RequestParam(name = "offset", defaultValue = "1") String offset){

        log.info("[ProductController] selectProducerProductListPage 상품 리스트 전체 조회(페이징) : " + offset);

        int total = productService.selectProducerProductListPage();

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProducerProductListPaging(cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    @Operation(summary = "브랜드별 페이지 식품 상품 리스트 전체 조회 (페이징)", description = "브랜드별 식품 상품 조회 및 페이징 처리 진행", tags = { "ProductController" })
    @GetMapping("/products/producer/food")
    public ResponseEntity<ResponseDTO> selectProducerProductFoodList(
            @RequestParam(name = "offset", defaultValue = "1") String offset){

        log.info("[ProductController] selectProducerProductFoodList 상품 리스트 전체 조회(페이징) : " + offset);

        int total = productService.selectProducerProductFoodList();

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProducerProductFoodListPaging(cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    @Operation(summary = "브랜드별 페이지 건강&뷰티 상품 리스트 전체 조회 (페이징)", description = "브랜드별 건강&뷰티 상품 조회 및 페이징 처리 진행", tags = { "ProductController" })
    @GetMapping("/products/producer/beauty")
    public ResponseEntity<ResponseDTO> selectProducerProductBeautyList(
            @RequestParam(name = "offset", defaultValue = "1") String offset){

        log.info("[ProductController] selectProducerProductBeautyList 상품 리스트 전체 조회(페이징) : " + offset);

        int total = productService.selectProducerProductBeautyList();

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProducerProductBeautyListPaging(cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    @Operation(summary = "브랜드별 페이지 의류 상품 리스트 전체 조회 (페이징)", description = "브랜드별 의류 상품 조회 및 페이징 처리 진행", tags = { "ProductController" })
    @GetMapping("/products/producer/fashion")
    public ResponseEntity<ResponseDTO> selectProducerProductFashionList(
            @RequestParam(name = "offset", defaultValue = "1") String offset){

        log.info("[ProductController] selectProducerProductFashionList 상품 리스트 전체 조회(페이징) : " + offset);

        int total = productService.selectProducerProductFashionList();

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProducerProductFashionListPaging(cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 상품별 상세 조회 */
    @Operation(summary = "상품별 상세 조회 요청", description = "상품별로 상세 내용이 담긴 페이지 처리가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/{productId}")
    public ResponseEntity<ResponseDTO> selectProductDetail(@PathVariable int productId){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 상세정보 조회 성공", productService.selectProductDetail(productId)));
    }

    /* 판매자 상품 등록 */
    @Operation(summary = "판매자 상품 등록 요청", description = "상품 등록이 진행됩니다.", tags = { "ProductController" })
    @PostMapping(value = "/products")
    public ResponseEntity<ResponseDTO> insertProduct(@ModelAttribute ProductDTO productDTO, MultipartFile productImage) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 등록 성공",  productService.insertProduct(productDTO, productImage)));
    }
}