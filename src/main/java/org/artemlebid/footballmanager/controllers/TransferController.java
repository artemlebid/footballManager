package org.artemlebid.footballmanager.controllers;

import jakarta.validation.Valid;
import org.artemlebid.footballmanager.constants.Messages;
import org.artemlebid.footballmanager.dtos.responses.SuccessResponseDto;
import org.artemlebid.footballmanager.dtos.transfer.TransferRequestDto;
import org.artemlebid.footballmanager.services.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.artemlebid.footballmanager.controllers.TransferController.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class TransferController {
    public static final String BASE_URL = "/api/transfers";
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponseDto> transfer(@RequestBody @Valid TransferRequestDto transferRequestDto) {
        transferService.transfer(transferRequestDto);

        SuccessResponseDto responseDto = new SuccessResponseDto();
        responseDto.setMessage(Messages.TRANSFER_SUCCESS);
        responseDto.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
