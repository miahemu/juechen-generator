package com.juechen.springbootinit.model.dto.postfavour;

import com.juechen.springbootinit.common.PageRequest;
import com.juechen.springbootinit.model.dto.post.PostQueryRequest;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 帖子收藏查询请求
 *
 * @author <a href="https://github.com/miahemu">玦尘</a>
 * @from <a href="https://blog.csdn.net/weixin_74199893?spm=1000.2115.3001.5343">CSDN</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PostFavourQueryRequest extends PageRequest implements Serializable {

    /**
     * 帖子查询请求
     */
    private PostQueryRequest postQueryRequest;

    /**
     * 用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}