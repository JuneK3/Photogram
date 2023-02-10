package com.rootlab.photogram.dto.user;

import com.rootlab.photogram.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private boolean pageOwnerState;
    private Integer imageCount;
    private User user;
    private boolean subscribeState;
    private Long subscribeCount; // 팔로잉 수
}
