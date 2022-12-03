package com.johnebri.cutsession.dto.clients;

import com.johnebri.cutsession.dto.clients.GetClientResponse;
import com.johnebri.cutsession.dto.clients.UserResponse;
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
public class UserListResponse extends GetClientResponse {
    private List<UserResponse> data;
}
