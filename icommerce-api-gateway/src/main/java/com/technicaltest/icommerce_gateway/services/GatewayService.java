package com.technicaltest.icommerce_gateway.services;

//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.ArraySchema;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
import com.technicaltest.icommerce_gateway.bean.GatewayBean;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/gateway-service")
@RequiredArgsConstructor
//@Tag(name = "user")
public class GatewayService {

    @Autowired
    private GatewayBean gatewayBean;
//    @Operation(description = "Xem danh sách User", responses = {
//            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class))), responseCode = "200") })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode  = "200", description = "Thành công"),
//            @ApiResponse(responseCode  = "401", description = "Chưa xác thực"),
//            @ApiResponse(responseCode  = "403", description = "Truy cập bị cấm"),
//            @ApiResponse(responseCode  = "404", description = "Không tìm thấy")
//    })
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to gateway service";
    }

    @GetMapping("/call-customer-service")
    public Object callCustomerService() {
        return gatewayBean.callCustomerServiceWelcome();
    }

    @GetMapping("/call-order-service")
    public Object callOrderService() {
        return gatewayBean.callOrderServiceWelcome();
    }

    @GetMapping("/order-detail/{id}")
    public Object orderDetail(@PathVariable(name = "id") Long id) {
        return gatewayBean.orderDetailByID(id);
    }


}
