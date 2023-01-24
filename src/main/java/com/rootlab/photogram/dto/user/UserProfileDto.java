package com.rootlab.photogram.dto.user;

import com.rootlab.photogram.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private boolean pageOwner;
    private Integer imageCount;
    private User user;
}
