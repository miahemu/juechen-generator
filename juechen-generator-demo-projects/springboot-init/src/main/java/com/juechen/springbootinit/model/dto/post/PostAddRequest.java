package com.juechen.springbootinit.model.dto.post;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 创建请求
 *
 * @author <a href="https://github.com/miahemu">玦尘</a>
 * @from <a href="https://blog.csdn.net/weixin_74199893?spm=1000.2115.3001.5343">CSDN</a>
 */
@Data
public class PostAddRequest implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    private static final long serialVersionUID = 1L;
}