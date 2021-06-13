package com.zavrsnirad.CodeFlow.dto.mappers;

import com.zavrsnirad.CodeFlow.domain.Follower;
import com.zavrsnirad.CodeFlow.dto.json.FollowerDtoJson;

public class MapperFollower {

    public static FollowerDtoJson FollowerToJson(Follower follower) {
        return new FollowerDtoJson(
                follower.getFollowerId(),
                true,
                follower.isPending()
        );
    }
}
