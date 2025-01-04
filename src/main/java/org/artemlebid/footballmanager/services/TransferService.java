package org.artemlebid.footballmanager.services;

import org.artemlebid.footballmanager.dtos.transfer.TransferRequestDto;

public interface TransferService {
    void transfer(TransferRequestDto transferRequestDto);
}
