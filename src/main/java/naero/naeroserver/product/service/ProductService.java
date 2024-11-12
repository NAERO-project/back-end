package naero.naeroserver.product.service;

import naero.naeroserver.common.Criteria;
import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.product.dto.ProductDTO;
import naero.naeroserver.product.repository.ProductRepository;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Value("/springboot-app/naeroimgs")
    private String IMAGE_DIR;
    @Value("http://localhost:8080/naeroimgs")
    private String IMAGE_URL;

    @Autowired
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper, String IMAGE_DIR, String IMAGE_URL) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.IMAGE_DIR = IMAGE_DIR;
        this.IMAGE_URL = IMAGE_URL;
    }

    public int selectProductPage() {
        log.info("[ProductService] selectProductPage 시작");

        List<TblProduct> productList = productRepository.findByProductOrderable("Y");

        log.info("[ProductService] selectProductPage 종료");

        return productList.size();
    }

    public Object selectProductListPaging(Criteria cri) {
        log.info("[ProductService] selectProductListPaging() 시작");

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productId").descending());

        Page<TblProduct> result = productRepository.findByProductOrderablePage("Y", paging);
        List<TblProduct> productList = (List<TblProduct>)result.getContent();

        for(int i = 0; i<productList.size(); i++){
            productList.get(i).setProductThumbnail(IMAGE_URL + productList.get(i).getProductThumbnail());
        }

        log.info("[ProductService] selectProductListPaging() 종료");

        return productList.stream().map(tblProduct -> modelMapper.map(tblProduct, ProductDTO.class)).collect(Collectors.toList());
    }
}
