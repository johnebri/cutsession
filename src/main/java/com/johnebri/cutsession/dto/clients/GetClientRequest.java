package com.johnebri.cutsession.dto.clients;

import com.johnebri.cutsession.model.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author John on 12/2/22
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetClientRequest {
    private int limit;
    private int offset;
    private UserTypeEnum type;
    private String city;
    private String name;
}
