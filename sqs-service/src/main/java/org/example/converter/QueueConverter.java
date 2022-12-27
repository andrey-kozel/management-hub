package org.example.converter;

import org.example.dto.QueueMessageDto;
import org.mapstruct.Mapper;

@Mapper
public interface QueueConverter {
    QueueMessageDto toDto(Long orgId, String accessToken);
}
