package com.dji.sdk.mqtt.state;

import com.dji.sdk.cloudapi.device.*;
import com.dji.sdk.cloudapi.livestream.DockLivestreamAbilityUpdate;
import com.dji.sdk.exception.CloudSDKException;
import com.google.common.collect.ImmutableSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

/**
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/18
 */
public enum DockStateDataKeyEnum {

    FIRMWARE_VERSION(ImmutableSet.of("firmware_version"), DockFirmwareVersion.class),

    LIVE_CAPACITY(ImmutableSet.of("live_capacity"), DockLivestreamAbilityUpdate.class),

    CONTROL_SOURCE(ImmutableSet.of("control_source"), DockDroneControlSource.class),

    LIVE_STATUS(ImmutableSet.of("live_status"), DockLiveStatus.class),

    WPMZ_VERSION(ImmutableSet.of("wpmz_version"), DockWpmzVersion.class),

    PAYLOAD(PayloadModelEnum.getAllIndexWithPosition(), DockPayload.class);

    private final Set<String> keys;

    private final Class classType;


    DockStateDataKeyEnum(Set<String> keys, Class classType) {
        this.keys = keys;
        this.classType = classType;
    }

    public Class getClassType() {
        return classType;
    }

    public Set<String> getKeys() {
        return keys;
    }

    public static DockStateDataKeyEnum find(Set<String> keys) {
        return Arrays.stream(values()).filter(keyEnum -> !Collections.disjoint(keys, keyEnum.keys)).findAny()
                .orElseThrow(() -> new CloudSDKException(DockStateDataKeyEnum.class, keys));
    }

}
