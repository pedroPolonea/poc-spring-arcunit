package com.psd.filter.resources;


import com.psd.filter.map.dto.ProductDTO;
import com.psd.filter.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import javax.validation.Valid;

@Api(
        tags = "products",
        value="/v1"
)
@RestController
@RequestMapping(value = "products", produces = MediaType.APPLICATION_JSON_VALUE, headers ="version=V1" )
public class ProductResources {

    @Autowired
    private ProductService productService;

    @ApiOperation(
            value = "Get all products.",
            response = ProductDTO.class)
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.save(productDTO), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get all products.",
            response = ProductDTO.class)
    @PutMapping
    public  ResponseEntity<?> update(@Valid @RequestBody ProductDTO productDTO) throws Exception {
        return new ResponseEntity<>(productService.update(Optional.ofNullable(productDTO)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws NotFoundException {
        productService.delete(Optional.ofNullable(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public  ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(productService.getId(id), HttpStatus.OK);
    }
}
