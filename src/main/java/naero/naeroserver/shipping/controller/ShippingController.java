package naero.naeroserver.shipping.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.shipping.dto.ShippingDTO;
import naero.naeroserver.shipping.service.ShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ShippingController {

    private static final Logger log = LoggerFactory.getLogger(ShippingController.class);

    private final ShippingService shippingService;

    @Autowired
    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    /* @Operation annotation:
     *  Definition:
     *      The @Operation annotation is part of the OpenAPI Specification and is commonly used in Spring Boot
     *      applications to document API endpoints for tools like Swagger. It is provided by the springdoc-openapi
     *      library, which integrates OpenAPI 3.0 into Spring Boot.
     *  Purpose of the @Operation Annotation:
     *      Describe API endpoints:
     *          It provides metadata such as the summary, description, and response details of an API operation.
     *      Generate comprehensive API documentation:
     *          It helps generate a user-friendly API documentation page (e.g., Swagger UI) that describes each endpoint in detail.
     *  Attributes of the @Operation Annotation:
     *      summary: A brief description of the purpose of the API endpoint.
     *      description: A more detailed explanation of what the endpoint does.
     *      tags: Used to categorize the endpoint, making it easier to organize in the documentation.
     *      parameters: Defines the parameters accepted by the endpoint, including their descriptions and whether they are required.
     *      responses: Specifies the possible responses from the API, including status codes and descriptions.
     *      deprecated: Marks the endpoint as deprecated if set to true.
     *      operationId: A unique identifier for the operation, useful for clients that auto-generate code based on the API.
     *  Summary:
     *      - @Operation: An annotation used to describe API endpoints in a Spring Boot application, enhancing the auto-generated API documentation.
     *      - Attributes: summary, description, tags, parameters, and responses are commonly used to provide detailed metadata about each endpoint.
     *      - Use Case: It's especially useful for generating interactive and easy-to-understand documentation for RESTful APIs using tools like Swagger UI.
     *  @ApiResponses:
     *      The @ApiResponses annotation in Spring Boot using springdoc-openapi (or similar tools like Swagger) is used
     *      purely for documentation purposes. It does not control the actual behavior of your API. Instead, it provides
     *      details about the potential HTTP responses that yor API might return.
     *  */
    @Operation(summary = "배송 조회 요청", description = "해당 배송건에 대한 정보 조회가 진행됩니다.", tags = {"ShippingController"})
//    @GetMapping("/shipping/{trackingNumber}")
    @GetMapping("/shipping/{shippingId}")
//    public ResponseEntity<ResponseDTO> getShipping(@PathVariable String trackingNumber) {
    public ResponseEntity<ResponseDTO> getShipping(@PathVariable Integer shippingId) {

        return ResponseEntity
                .ok()
//                .body(new ResponseDTO(HttpStatus.OK, "배송 조회 성공", shippingService.selectShipping(trackingNumber)));
                .body(new ResponseDTO(HttpStatus.OK, "배송 조회 성공", shippingService.selectShipping(shippingId)));
    }

    @Operation(summary = "배송회사 조회 요청", description = "배송회사 정보 조회가 진행됩니다.", tags = {"ShippingController"})
    @GetMapping("/shipping/company")
    public ResponseEntity<ResponseDTO> getShippingCompany() {

        return  ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "배송회사 조회 성공", shippingService.selectShippingCompany()));
    }

    @Operation(summary = "배송 상태 조회 요청", description = "해당 배송건에 대한 상태 조회가 진행됩니다.", tags = {"ShippingController"})
    @GetMapping("/shipping/track-shipment")
    public ResponseEntity<?> getTrackingInfo(
            @RequestParam String trackingNumber,
            @RequestParam String shipComCode) {

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "배송 상태 조회 성공", shippingService.getTrackingInformation(trackingNumber, shipComCode)));
    }

    /* @ModelAttribute:
     * Brief:
     *      The @ModelAttribute annotation in Spring MVC is used to bind a method parameter or return value to a named
     *      model attribute and expose it to a web view. It can be used in two main ways: to prepopulate a model before
     *      a request is handled, or to bind form data to an object in a controller.
     * Usage Scenarios for @ModelAttribute:
     *      Binding Request Parameters to a Model Attribute:
     *      - When used as a method parameter, @ModelAttribute binds request parameters to a model object.
     *      Adding Attributes to the Model:
     *      - When used on a method, @ModelAttribute adds the returned value to the model, making it available for the view.
     * Key Points About @ModelAttribute
     *      Model Binding: @ModelAttribute binds request parameters to a model object, making it useful for form handling
     *      Adding to Model: When used on a method, it adds the returned object to the model, which is useful for providing common attributes to views.
     *      Data Prepopulation: You can use @ModelAttribute to prepopulate forms with existing data or provide dropdown lists, radio button options, etc.
     *  */
    @Operation(summary = "배송 수정 요청", description = "해당 배송 수정이 진행됩니다.", tags = {"ShippingController"})
    @PutMapping(value = "/shipping")
    public ResponseEntity<ResponseDTO> updateShipping(@RequestBody ShippingDTO tblShippingDTO) {

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "배송 리스트 수정 성공", shippingService.updateShipping(tblShippingDTO)));
    }

}
