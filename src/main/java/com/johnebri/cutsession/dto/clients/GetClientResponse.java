package com.johnebri.cutsession.dto.clients;

import com.johnebri.cutsession.model.User;
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
public class GetClientResponse {
    private int count;
    private String next;
    private String previous;
}
