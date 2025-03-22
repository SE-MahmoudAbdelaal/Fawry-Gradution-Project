package com.e_commerce.notification_api.model;

import lombok.*;

import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDetailDTO {
    private String to;
    private String subject;
    private Map<String, Object> dynamicValue;
    private String templateName;
}
