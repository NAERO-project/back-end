package naero.naeroserver.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.Criteria;
import naero.naeroserver.common.PageDTO;
import naero.naeroserver.common.PagingResponseDTO;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.member.service.UserService;
import naero.naeroserver.product.dto.ProductDTO;
import naero.naeroserver.product.dto.ProductSearchDTO;
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
    private final int SIZE = 1;
    private final UserService userService;

    @Autowired
    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    /* 상품 리스트 전체 조회 (페이징) */
    @Operation(summary = "상품 리스트 전체 조회 (페이징)", description = "상품 조회 및 페이징 처리 진행", tags = { "ProductController" })
    @GetMapping("/products/more")
    public ResponseEntity<ResponseDTO> selectProductList(@RequestParam(name = "offset", defaultValue = "1") String offset){

        log.info("[ProductController] selectProductList 상품 리스트 전체 조회(페이징) : " + offset);

        int total = productService.selectProductPage();

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProductListPaging(cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    @Operation(summary = "카테고리별 리스트 전체 조회 (페이징)", description = "카테고리별 상품 조회 및 페이징 처리 진행", tags = { "ProductController" })
    @GetMapping("/products/more/{mediumId}")
    public ResponseEntity<ResponseDTO> selectProductCategoryList(
            @RequestParam(name = "offset", defaultValue = "1") String offset,
            @PathVariable int mediumId){

        log.info("[ProductController] selectProductCategoryList 상품 리스트 전체 조회(페이징) : " + offset);

        int total = productService.selectProductCategoryList(mediumId);

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProductCategoryListPaging(mediumId, cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

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

    /* 전체 브랜드 페이지 상품 조회 (미리보기) */
    @Operation(summary = "전체 브랜드 상품 리스트 미리보기 조회 요청", description = "브랜드별 상품 리스트 미리보기 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/producer/preview")
    public ResponseEntity<ResponseDTO> selectProducerProductListPreview() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProducerProductListPreview()));
    }

    /* 브랜드별 페이지 전체 상품 조회 (페이징) */
    @Operation(summary = "브랜드별 페이지 상품 리스트 전체 조회 (페이징)", description = "브랜드별 상품 조회 및 페이징 처리 진행", tags = { "ProductController" })
    @GetMapping("/products/producer/{producerId}")
    public ResponseEntity<ResponseDTO> selectProductListPageByBrand(
            @RequestParam(name = "offset", defaultValue = "1") String offset,
            @PathVariable int producerId){

        log.info("[ProductController] selectProducerProductListPage 상품 리스트 전체 조회(페이징) : " + offset);

        int total = productService.selectProducerProductListPage(producerId);

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProductListByBrandPaging(producerId, cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 판매자별 페이지 카테고리 상품 조회 (페이징) */
    @Operation(summary = "판매자별 카테고리 상품 리스트 전체 조회 (페이징)", description = "판매자별 카테고리 리스트 상품 조회 및 페이징 처리 진행", tags = { "ProductController" })
    @GetMapping("/products/producer/{producerId}/{mediumId}")
    public ResponseEntity<ResponseDTO> selectProducerProductCategoryListPage(
            @RequestParam(name = "offset", defaultValue = "1") String offset,
            @PathVariable int producerId,
            @PathVariable int mediumId)  {

        log.info("[ProductController] selectProducerProductCategoryListPage 상품 리스트 전체 조회(페이징) : " + offset);

        int total = productService.selectProducerProductCategoryListPage(producerId, mediumId);

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProducerProductCategoryListPaging(producerId, mediumId, cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 상품별 상세 조회 */
    // smallCategortId로 바꿔서 같은 API사용하면 연관 검색 가능할거 같다.
    @Operation(summary = "상품별 상세 조회 요청", description = "상품별로 상세 내용이 담긴 페이지 처리가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/{productId}")
    public ResponseEntity<ResponseDTO> selectProductDetail(@PathVariable int productId){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 상세정보 조회 성공", productService.selectProductDetail(productId)));
    }

    /* 판매자 페이지 전체 상품 조회 (페이징) */
    @Operation(summary = "판매자 페이지 상품 리스트 전체 조회 (페이징)", description = "판매자 상품 조회 및 페이징 처리 진행", tags = { "ProductController" })
    @GetMapping("/products/producer-page/{producerUsername}")
    public ResponseEntity<ResponseDTO> selectProducerProductListPage(
            @RequestParam(name = "offset", defaultValue = "1") String offset,
            @PathVariable String producerUsername){

        log.info("[ProductController] selectProducerProductListPage 상품 리스트 전체 조회(페이징) : " + offset);

        int producerId = userService.getProducerIdFromUserName(producerUsername);

        int total = productService.selectProducerProductListPage(producerId);

        Criteria cri = new Criteria(Integer.valueOf(offset), 12);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(productService.selectProducerProductListPaging(producerId, cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 판매자 상품 등록 */
    @Operation(summary = "판매자 상품 등록 요청", description = "상품 등록이 진행됩니다.", tags = { "ProductController" })
    @PostMapping(value = "/products/insert")
    public ResponseEntity<ResponseDTO> insertProduct(@ModelAttribute ProductDTO productDTO,
                                                     @RequestPart(value = "productImage", required = false) MultipartFile productImage,
                                                     @RequestParam("producerUsername") String producerUsername) {
        if (productDTO.getSmallCategory().getMediumCategoryId() == null ||
                productDTO.getSmallCategory().getSmallCategoryId() == null) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST, "중분류 또는 소분류 누락", null));
        }

        System.out.println("여기여기여기!!!" + productDTO.getOptions());
        int producerId = userService.getProducerIdFromUserName(producerUsername);
        productDTO.setProducerId(producerId);
        System.out.println("여기여기여기!!!" + productDTO.getProducerId());

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 등록 성공",  productService.insertProduct(productDTO, productImage)));
    }

    /* 판매자 상품 수정 */
    @Operation(summary = "판매자 상품 수정 요청", description = "상품 수정이 진행됩니다.", tags = { "ProductController" })
    @PutMapping(value = "/products")
    public ResponseEntity<ResponseDTO> updateProduct(@ModelAttribute ProductDTO productDTO, MultipartFile productImage) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 수정 성공",  productService.updateProduct(productDTO, productImage)));
    }

    /* 판매자 상품 삭제 */
    @Operation(summary = "판매자 상품 삭제 요청", description = "상품 삭제가 진행됩니다.", tags = { "ProductController" })
    @DeleteMapping(value = "/products")
    public ResponseEntity<ResponseDTO> deleteProduct(@ModelAttribute ProductDTO productDTO){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 삭제 성공",  productService.deleteProduct(productDTO)));
    }

    /* 상품 전체 검색, 필터링 기능 */
    @GetMapping("/products/search/{page}")
    public ResponseEntity<ResponseDTO> userSearch(@PathVariable Integer page, @RequestBody ProductSearchDTO crit){
        System.out.println("crit 확인"+ crit);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,
                productService.searchProductPage(page, SIZE, crit),
                productService.searchProduct(page, SIZE, crit)
        ));
    }

    /* 상품 카테고리와 연관된 상품 조회 */
    @Operation(summary = "카테고리와 연관된 상품 조회 요청",
            description = "카테고리와 연관된 상품 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/productList/{smallId}")
    public ResponseEntity<ResponseDTO> selectProductCategory(@PathVariable("smallId") int smallId){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "연관된 상품 정보 조회 성공", productService.selectProductCategory(smallId)));
    }

    @Operation(summary = "대분류 카테고리와 연관된 중분류 카테고리 조회 요청",
            description = "대분류 카테고리와 연관된 중분류 카테고리 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/medium-categories")
    public ResponseEntity<ResponseDTO> selectMediumCategoryListByLargeCategory(@RequestParam("largeCategory") int largeCategory){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "연관된 중분류 조회 성공", productService.selectMediumListByLarge(largeCategory)));
    }

    @Operation(summary = "중분류 카테고리와 연관된 소분류 카테고리 조회 요청",
            description = "중분류 카테고리와 연관된 소분류 카테고리 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/small-categories")
    public ResponseEntity<ResponseDTO> selectSmallCategoryListByMediumCategory(@RequestParam("mediumCategory") int mediumCategory){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "연관된 소분류 조회 성공", productService.selectSmallListByMedium((mediumCategory))));
    }
}
