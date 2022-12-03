package com.johnebri.cutsession.dto.clients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author John on 12/1/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantResponse {
    private String merchantId;
    private String name;
    private String email;
    private String cityOfOperation;
    private String phoneNumber;
}
