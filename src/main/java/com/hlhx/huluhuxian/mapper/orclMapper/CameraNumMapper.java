package com.hlhx.huluhuxian.mapper.orclMapper;

import com.hlhx.huluhuxian.model.CameraInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author think
 * @create 2020/4/1 17:37
 */
public interface CameraNumMapper {
    int insertCameraNum(@Param("list") List<CameraInfo> list);
    List<CameraInfo> getCameraNum(@Param("page") Integer page, @Param("size") Integer size, @Param("cameraInfo") CameraInfo cameraInfo, @Param("beginDateScope") Date[] beginDateScope);
    Long getCameraTotal(@Param("cameraInfo") CameraInfo cameraInfo, @Param("beginDateScope") Date[] beginDateScope);
}
