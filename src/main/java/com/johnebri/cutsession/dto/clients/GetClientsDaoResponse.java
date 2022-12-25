package com.johnebri.cutsession.dto.clients;

import com.johnebri.cutsession.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author John on 12/25/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetClientsDaoResponse {
    private List<User> filterdResponse;
    private List<User> totalResponse;
}
