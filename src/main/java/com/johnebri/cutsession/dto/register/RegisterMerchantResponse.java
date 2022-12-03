package com.johnebri.cutsession.dto.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author John on 12/1/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterMerchantResponse {
    private String merchantId;
}
