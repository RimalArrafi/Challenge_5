package com.challenge_5.challenge_5.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.challenge_5.challenge_5.dto.OrderDto;
import com.challenge_5.challenge_5.service.JasperReportService;
import com.challenge_5.challenge_5.service.OrderService;
import com.challenge_5.challenge_5.utils.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/v1/report")
@Slf4j
public class ReportController {
    @Autowired
    OrderService orderService;

    @Autowired
    JasperReportService jasperReportService;

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getItemReport(@PathVariable UUID orderId) {
        try {
            OrderDto order = orderService.getOrderById(orderId);
            byte[] reportContent = jasperReportService.getItemReport(order, "pdf");

            ByteArrayResource resource = new ByteArrayResource(reportContent);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(resource.contentLength())
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ContentDisposition.attachment()
                                    .filename("item-report." + "pdf")
                                    .build().toString())
                    .body(resource);
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }
}