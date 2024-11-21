package com.juechen.springbootinit.model.dto.postthumb;

import java.io.Serializable;
import lombok.Data;

/**
 * 帖子点赞请求
 *
 * @author <a href="https://github.com/miahemu">玦尘</a>
 * @from <a href="https://blog.csdn.net/weixin_74199893?spm=1000.2115.3001.5343">CSDN</a>
 */
@Data
public class PostThumbAddRequest implements Serializable {

    /**
     * 帖子 id
     */
    private Long postId;

    private static final long serialVersionUID = 1L;
}