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

    /* 상품 등록 */
    @Operation(summary = "상품 등록 요청", description = "상품 등록이 진행됩니다.", tags = { "ProductController" })
    @PostMapping(value = "/products")
    public ResponseEntity<ResponseDTO> insertProduct(@ModelAttribute ProductDTO productDTO, MultipartFile productImage) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 등록 성공",  productService.insertProduct(productDTO, productImage)));
    }

    /* 상품 리스트 전체 조회 (페이징) */
    @Operation(summary = "상품 리스트 전체 조회 (페이징)", description = "상품 조회 및 페이징 처리 진행", tags = { "ProductController" })
    @GetMapping("/products")
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

    /* 식품, 음료 리스트 전체 조회 (페이징) */
    @Operation(summary = "식품과 음료 리스트 조회 요청", description = "식품과 음료 카테고리에 해당하는 상품 리스트 조회 및 페이징 처리가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/food")
    public ResponseEntity<ResponseDTO> selectProductListAboutFood(
            @RequestParam(name = "offset", defaultValue = "1") String offset) {

        log.info("[ProductController] selectProductList 식품과 음 리스트 전체 조회(페이징) : " + offset);

        int foodTotal = productService.selectProductPageFood();

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProductFoodListPaging(cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, foodTotal));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 건강, 뷰티 리스트 전체 조회 (페이징) */
    @Operation(summary = "건강과 뷰티 리스트 조회 요청", description = "건강과 뷰티 카테고리에 해당하는 상품 리스트 조회 및 페이징 처리가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/health")
    public ResponseEntity<ResponseDTO> selectProductListAboutHealth(
            @RequestParam(name = "offset", defaultValue = "1") String offset) {

        log.info("[ProductController] selectProductList 건강과 뷰티 리스트 전체 조회(페이징) : " + offset);

        int healthTotal = productService.selectProductPageHealth();

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProductHealthListPaging(cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, healthTotal));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 의류 리스트 전체 조회 (페이징) */
    @Operation(summary = "의류 리스트 조회 요청", description = "의류 카테고리에 해당하는 상품 리스트 조회 및 페이징 처리가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/apparel ")
    public ResponseEntity<ResponseDTO> selectProductListAboutApparel (
            @RequestParam(name = "offset", defaultValue = "1") String offset) {

        log.info("[ProductController] selectProductList 의류 리스트 전체 조회(페이징) : " + offset);

        int apparelTotal = productService.selectProductPageApparel();

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProductApparelListPaging(cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, apparelTotal));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

}
