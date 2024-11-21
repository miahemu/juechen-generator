package com.juechen.springbootinit.model.dto.file;

import java.io.Serializable;
import lombok.Data;

/**
 * 文件上传请求
 *
 * @author <a href="https://github.com/miahemu">玦尘</a>
 * @from <a href="https://blog.csdn.net/weixin_74199893?spm=1000.2115.3001.5343">CSDN</a>
 */
@Data
public class UploadFileRequest implements Serializable {

    /**
     * 业务
     */
    private String biz;

    private static final long serialVersionUID = 1L;
}