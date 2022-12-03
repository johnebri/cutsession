package com.johnebri.cutsession.dto.clients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author John on 12/1/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MerchantListResponse extends GetClientResponse {
    private List<MerchantResponse> data;
}
